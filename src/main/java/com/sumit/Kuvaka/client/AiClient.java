package com.sumit.Kuvaka.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumit.Kuvaka.model.Lead;
import com.sumit.Kuvaka.model.Offer;

@Component
public class AiClient {
    private final WebClient webClient;
    @Value("${openai.key:}") private String openaiKey;
    private final ObjectMapper om = new ObjectMapper();

    public AiClient(WebClient.Builder b) {
        this.webClient = b.baseUrl("https://api.openai.com/v1").build();
    }

    public AiResult classify(Lead lead, Offer offer) {
        if (openaiKey == null || openaiKey.isBlank()) {
            return new AiResult("Low", "No OPENAI key configured; using fallback Low");
        }
        String prompt = buildPrompt(lead, offer);
        Map<String,Object> payload = Map.of(
            "model","gpt-4o-mini",
            "messages", java.util.List.of(
                Map.of("role","system","content","You are a sales intent classifier. Return JSON only with keys intent and explanation."),
                Map.of("role","user","content", prompt)
            ),
            "temperature", 0.0
        );
        try {
            Map resp = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization","Bearer " + openaiKey)
                    .bodyValue(payload)
                    .retrieve().bodyToMono(Map.class).block();
            String text = ((Map)((Map)((java.util.List)resp.get("choices")).get(0)).get("message")).get("content").toString();
            // extract JSON substring
            int start = text.indexOf("{");
            int end = text.lastIndexOf("}");
            if (start>=0 && end>start) {
                String json = text.substring(start, end+1);
                Map m = om.readValue(json, Map.class);
                return new AiResult((String)m.get("intent"), (String)m.get("explanation"));
            } else {
                return new AiResult("Low", "Unparseable AI output");
            }
        } catch (Exception e) {
            return new AiResult("Low", "AI call error: " + e.getMessage());
        }
    }

    private String buildPrompt(Lead l, Offer o) {
        return String.format("Product: %s\nValueProps: %s\nICP: %s\nProspect: %s | %s | %s | %s\nRules: classify High/Medium/Low and return JSON only.",
                o.getName(), String.join(", ", o.getValueProps()==null?java.util.List.of():o.getValueProps()),
                String.join(", ", o.getIcpIndustries()), l.getName(), l.getRole(), l.getCompany(), l.getIndustry());
    }

    public static class AiResult {
        public final String intent;
        public final String explanation;
        public AiResult(String i, String e) { intent = i; explanation = e; }
    }
}