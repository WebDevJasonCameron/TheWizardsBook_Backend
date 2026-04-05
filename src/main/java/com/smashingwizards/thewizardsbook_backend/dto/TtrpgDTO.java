package com.smashingwizards.thewizardsbook_backend.dto;

public class TtrpgDTO {

    // ATTs
    private Long id;
    private String name;
    private String version;

    // CONs
    public TtrpgDTO() {
    }
    public TtrpgDTO(String name, String version) {
        this.name = name;
        this.version = version;
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

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}

