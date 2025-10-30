package com.sumit.Kuvaka.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.sumit.Kuvaka.model.Lead;
import com.sumit.Kuvaka.model.ScoredLead;

@Service
public class LeadService {
    // map offerId -> list of leads (or "pending" bucket if absent)
    private final Map<String, List<Lead>> store = new ConcurrentHashMap<>();
    private final Map<String, List<ScoredLead>> scoredStore = new ConcurrentHashMap<>();

    public void saveLeads(String offerId, List<Lead> leads) {
        String key = (offerId == null ? "pending" : offerId);
        store.computeIfAbsent(key, k -> new ArrayList<>()).addAll(leads);
    }

    public List<Lead> getLeads(String offerId) {
        return store.getOrDefault(offerId, Collections.emptyList());
    }

    public void saveScored(String offerId, List<ScoredLead> scored) {
        scoredStore.put(offerId, scored);
    }

    public List<ScoredLead> getScored(String offerId) {
        return scoredStore.getOrDefault(offerId, Collections.emptyList());
    }
}