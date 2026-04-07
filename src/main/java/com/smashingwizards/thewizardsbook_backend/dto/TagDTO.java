package com.smashingwizards.thewizardsbook_backend.dto;

public class TagDTO {

    // ATTs
    private Long id;
    private String name;
    private String type;

    // CONs
    public TagDTO() {
    }
    public TagDTO(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
