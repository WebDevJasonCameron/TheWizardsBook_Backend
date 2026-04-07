package com.smashingwizards.thewizardsbook_backend.dto;

public class RpgClassDTO {

    // ATTs
    private Long id;
    private String name;
    private String subClassName;
    private String description;

    // CONs
    public RpgClassDTO() {
    }
    public RpgClassDTO(String name, String subClassName, String description) {
        this.name = name;
        this.subClassName = subClassName;
        this.description = description;
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

    public String getSubClassName() {
        return subClassName;
    }
    public void setSubClassName(String subClassName) {
        this.subClassName = subClassName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
