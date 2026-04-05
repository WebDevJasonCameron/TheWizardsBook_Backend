package com.smashingwizards.thewizardsbook_backend.dto;

public class EffectDTO {

    // ATTs
    private Long id;
    private String name;
    private String subEffect;

    // CONs
    public EffectDTO() {
    }
    public EffectDTO(String name, String subEffect) {
        this.name = name;
        this.subEffect = subEffect;
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

    public String getSubEffect() {
        return subEffect;
    }
    public void setSubEffect(String subEffect) {
        this.subEffect = subEffect;
    }
}
