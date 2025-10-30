package com.sumit.Kuvaka.service;

import org.springframework.stereotype.Service;

import com.sumit.Kuvaka.model.Lead;
import com.sumit.Kuvaka.model.Offer;

@Service
public class ScoringService {
    public int computeRuleScore(Lead lead, Offer offer) {
        int r = rolePoints(lead.getRole());
        int i = industryPoints(lead.getIndustry(), offer);
        int c = completenessPoints(lead);
        return r + i + c;
    }

    int rolePoints(String role) {
        if (role == null) return 0;
        String r = role.toLowerCase();
        String[] decision = {"head","head of","director","vp","vice president","ceo","cxo","cofounder","founder","owner","partner","chief","principal"};
        for(String k: decision) if (r.contains(k)) return 20;
        String[] influencer = {"manager","lead","senior","growth","marketing","sales","product","business development","bd"};
        for(String k: influencer) if (r.contains(k)) return 10;
        return 0;
    }

    int industryPoints(String industry, Offer offer) {
        if (industry == null || offer == null) return 0;
        String il = industry.toLowerCase();
        for (String icp : offer.getIcpIndustries()) {
            if (il.equals(icp.toLowerCase()) || il.contains(icp.toLowerCase()) || icp.toLowerCase().contains(il)) return 20;
        }
        for (String adj : offer.getAdjacentIndustries()) {
            if (il.equals(adj.toLowerCase()) || il.contains(adj.toLowerCase()) || adj.toLowerCase().contains(il)) return 10;
        }
        return 0;
    }

    int completenessPoints(Lead l) {
        int present = 0;
        if (notEmpty(l.getName())) present++;
        if (notEmpty(l.getRole())) present++;
        if (notEmpty(l.getCompany())) present++;
        if (notEmpty(l.getIndustry())) present++;
        if (notEmpty(l.getLocation())) present++;
        if (notEmpty(l.getLinkedinBio())) present++;
        if (present == 6) return 10;
        if (present >= 3) return 5;
        return 0;
    }

    boolean notEmpty(String s) { return s != null && !s.trim().isEmpty(); }
}
