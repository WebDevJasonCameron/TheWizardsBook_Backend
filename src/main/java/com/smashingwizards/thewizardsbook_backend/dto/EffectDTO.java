package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class EffectDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String subEffect;

    // CONs
    public EffectDTO() {}
    public EffectDTO(String name, String subEffect) {
        this.name = name;
        this.subEffect = subEffect;
    }
    public EffectDTO(Long id, String name, String subEffect) {
        this.id = id;
        this.name = name;
        this.subEffect = subEffect;
    }

    // OVRs
    @Override
    public String toString() {
        return "EffectDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subEffect='" + subEffect + '\'' +
                '}';
    }
}
