package com.sumit.Kuvaka.service;

import static org.testng.Assert.assertEquals;

import org.junit.Test;

import com.sumit.Kuvaka.model.Lead;
import com.sumit.Kuvaka.model.Offer;

public class ScoringServiceTest {

    @Test
    public void decisionMakerExactIcpComplete_gets50() {
        ScoringService s = new ScoringService();

        Lead l = new Lead();
        l.setName("A");
        l.setRole("Head of Growth");
        l.setCompany("X");
        l.setIndustry("SaaS");
        l.setLocation("India");
        l.setLinkedinBio("bio");

        Offer o = new Offer();
        o.setIcpIndustries(java.util.List.of("SaaS"));
        o.setAdjacentIndustries(java.util.List.of("Tech"));

        int score = s.computeRuleScore(l, o);
        assertEquals(50, score);
    }
}