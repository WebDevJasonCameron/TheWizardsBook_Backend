package com.smashingwizards.thewizardsbook_backend.dto;

public class DamagetypeDTO {

    // ATTs
    private Long id;
    private String name;

    // CONs
    public DamagetypeDTO() {
    }
    public DamagetypeDTO(String name) {
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
}
