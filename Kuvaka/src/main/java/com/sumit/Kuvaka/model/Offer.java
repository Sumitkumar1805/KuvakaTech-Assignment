package com.sumit.Kuvaka.model;

import java.util.ArrayList;
import java.util.List;

public class Offer {
	private String id;
    private String name;
    private List<String> valueProps = new ArrayList<>();
    private List<String> idealUseCases = new ArrayList<>();
    private List<String> icpIndustries = new ArrayList<>();
    private List<String> adjacentIndustries = new ArrayList<>();

   
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getValueProps() { return valueProps; }
    public void setValueProps(List<String> valueProps) { this.valueProps = valueProps; }
    public List<String> getIdealUseCases() { return idealUseCases; }
    public void setIdealUseCases(List<String> idealUseCases) { this.idealUseCases = idealUseCases; }
    public List<String> getIcpIndustries() { return icpIndustries; }
    public void setIcpIndustries(List<String> icpIndustries) { this.icpIndustries = icpIndustries; }
    public List<String> getAdjacentIndustries() { return adjacentIndustries; }
    public void setAdjacentIndustries(List<String> adjacentIndustries) { this.adjacentIndustries = adjacentIndustries; }

}
