package com.sumit.Kuvaka.model;

public class ScoredLead extends Lead {
	private int ruleScore;
    private String aiIntent;
    private int aiPoints;
    private int finalScore;
    private String intentLabel;
    private String reasoning;

    public int getRuleScore() { return ruleScore; }
    public void setRuleScore(int ruleScore) { this.ruleScore = ruleScore; }
    public String getAiIntent() { return aiIntent; }
    public void setAiIntent(String aiIntent) { this.aiIntent = aiIntent; }
    public int getAiPoints() { return aiPoints; }
    public void setAiPoints(int aiPoints) { this.aiPoints = aiPoints; }
    public int getFinalScore() { return finalScore; }
    public void setFinalScore(int finalScore) { this.finalScore = finalScore; }
    public String getIntentLabel() { return intentLabel; }
    public void setIntentLabel(String intentLabel) { this.intentLabel = intentLabel; }
    public String getReasoning() { return reasoning; }
    public void setReasoning(String reasoning) { this.reasoning = reasoning; }

}
