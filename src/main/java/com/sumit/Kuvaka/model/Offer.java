package com.sumit.Kuvaka.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // or GenerationType.AUTO for numeric IDs
    private String id;

    private String name;

    @ElementCollection
    private List<String> valueProps = new ArrayList<>();

    @ElementCollection
    private List<String> idealUseCases = new ArrayList<>();

    @ElementCollection
    private List<String> icpIndustries = new ArrayList<>();

    @ElementCollection
    private List<String> adjacentIndustries = new ArrayList<>();

    // Getters & Setters
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