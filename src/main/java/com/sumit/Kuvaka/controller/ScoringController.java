package com.sumit.Kuvaka.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.Kuvaka.client.AiClient;
import com.sumit.Kuvaka.model.Lead;
import com.sumit.Kuvaka.model.Offer;
import com.sumit.Kuvaka.model.ScoredLead;
import com.sumit.Kuvaka.service.LeadService;
import com.sumit.Kuvaka.service.OfferService;
import com.sumit.Kuvaka.service.ScoringService;

@RestController
public class ScoringController {
    private final LeadService leadService;
    private final OfferService offerService;
    private final ScoringService scoringService;
    private final AiClient aiClient;

    public ScoringController(LeadService leadService, OfferService offerService, ScoringService scoringService, AiClient aiClient) {
        this.leadService = leadService; this.offerService = offerService; this.scoringService = scoringService; this.aiClient = aiClient;
    }

    @PostMapping("/score")
    public ResponseEntity<?> score(@RequestBody Map<String,String> body) {
        String offerId = body.get("offerId");
        if (offerId == null) return ResponseEntity.badRequest().body(Map.of("error","offerId required"));
        Offer offer = offerService.findById(offerId);
        if (offer == null) return ResponseEntity.status(404).body(Map.of("error","offer not found"));
        List<Lead> leads = leadService.getLeads(offerId);
        List<ScoredLead> out = new ArrayList<>();
        for (Lead l : leads) {
            ScoredLead s = new ScoredLead();
            // copy fields
            s.setId(l.getId()); s.setName(l.getName()); s.setRole(l.getRole()); s.setCompany(l.getCompany());
            s.setIndustry(l.getIndustry()); s.setLocation(l.getLocation()); s.setLinkedinBio(l.getLinkedinBio());
            int ruleScore = scoringService.computeRuleScore(l, offer);
            s.setRuleScore(ruleScore);
            AiClient.AiResult ai = aiClient.classify(l, offer);
            String aiIntent = ai.intent == null ? "Low" : ai.intent;
            s.setAiIntent(aiIntent);
            int aiPoints = "High".equalsIgnoreCase(aiIntent) ? 50 : ("Medium".equalsIgnoreCase(aiIntent) ? 30 : 10);
            s.setAiPoints(aiPoints);
            int finalScore = ruleScore + aiPoints;
            s.setFinalScore(finalScore);
            s.setIntentLabel(aiIntent);
            s.setReasoning("rule_score=" + ruleScore + " | AI: " + ai.explanation);
            out.add(s);
        }
        leadService.saveScored(offerId, out);
        Map<String, Long> counts = out.stream().collect(Collectors.groupingBy(ScoredLead::getIntentLabel, Collectors.counting()));
        return ResponseEntity.ok(Map.of("total", out.size(), "counts", counts));
    }

    @GetMapping("/results")
    public ResponseEntity<List<Map<String, Object>>> results(@RequestParam String offerId) {
        List<ScoredLead> list = leadService.getScored(offerId);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resp = list.stream()
            .map(s -> (Map<String, Object>) (Map<?, ?>) Map.of(
                    "name", s.getName(),
                    "role", s.getRole(),
                    "company", s.getCompany(),
                    "intent", s.getIntentLabel(),
                    "score", s.getFinalScore(),
                    "reasoning", s.getReasoning()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/results/export")
    public ResponseEntity<String> export(@RequestParam String offerId) {
        List<ScoredLead> list = leadService.getScored(offerId);
        StringBuilder sb = new StringBuilder();
        sb.append("name,role,company,industry,intent,score,reasoning\n");
        for (ScoredLead s : list) {
            sb.append(String.join(",", safe(s.getName()), safe(s.getRole()), safe(s.getCompany()), safe(s.getIndustry()),
                    safe(s.getIntentLabel()), String.valueOf(s.getFinalScore()), "\"" + safe(s.getReasoning()).replace("\"","\"\"") + "\""))
              .append("\n");
        }
        return ResponseEntity.ok().header("Content-Type","text/csv").body(sb.toString());
    }

    private String safe(String s) { return s == null ? "" : s.replaceAll(",", " "); }
}
