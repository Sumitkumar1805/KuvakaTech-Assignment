package com.sumit.Kuvaka.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.Kuvaka.model.Lead;
import com.sumit.Kuvaka.service.LeadService;
import com.sumit.Kuvaka.util.CsvUtil;

@RestController
@RequestMapping("/leads")
public class LeadController {
    private final LeadService leadService;
    public LeadController(LeadService leadService) { this.leadService = leadService; }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
                                    @RequestParam(value = "offerId", required = false) String offerId) {
        try {
            List<Lead> leads = CsvUtil.parse(file.getInputStream());
            leadService.saveLeads(offerId, leads);
            return ResponseEntity.ok(Map.of("message","Uploaded", "count", leads.size()));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }
}