package com.smashingwizards.thewizardsbook_backend.dto;

public class ConditionDTO {

    // ATTs
    private Long id;
    private String name;
    private String description;

    // CONs
    public ConditionDTO() {
    }
    public ConditionDTO(String description, String name) {
        this.description = description;
        this.name = name;
    }

    // GETs & SETs
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
