package com.smashingwizards.thewizardsbook_backend.dto;

public class TypeDTO {

    // ATTs
    private Long id;
    private String name;
    private String subType;

    // CONs
    public TypeDTO() {
    }
    public TypeDTO(String name, String subType) {
        this.name = name;
        this.subType = subType;
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

    public String getSubType() {
        return subType;
    }
    public void setSubType(String subType) {
        this.subType = subType;
    }
}
