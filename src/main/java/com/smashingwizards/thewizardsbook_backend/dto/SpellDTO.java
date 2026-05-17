package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellDTO {

    private Long id;
    private String name;
    private String level;
    private String castingTime;
    private String rangeArea;
    private boolean componentVisual;
    private boolean componentSemantic;
    private boolean componentMaterial;
    private String componentMaterials;
    private String duration;
    private boolean concentration;
    private boolean ritual;
    private String school;
    private String description;
    private Long sourceId;

    // CONs
    public SpellDTO() {
    }
    public SpellDTO(String name, String level, String castingTime, String rangeArea, boolean componentVisual, boolean componentSemantic, boolean componentMaterial, String componentMaterials, String duration, boolean concentration, boolean ritual, String school, String description, Long sourceId) {
        this.name = name;
        this.level = level;
        this.castingTime = castingTime;
        this.rangeArea = rangeArea;
        this.componentVisual = componentVisual;
        this.componentSemantic = componentSemantic;
        this.componentMaterial = componentMaterial;
        this.componentMaterials = componentMaterials;
        this.duration = duration;
        this.concentration = concentration;
        this.ritual = ritual;
        this.school = school;
        this.description = description;
        this.sourceId = sourceId;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellClassDTO{" +
                "id=" + id +
                ", name=" + name +
                ", level=" + level +
                ", castingTime=" + castingTime +
                ", rangeArea=" + rangeArea +
                ", componentVisual=" + componentVisual +
                ", componentSemantic=" + componentSemantic +
                ", componentMaterial=" + componentMaterial +
                ", duration=" + duration +
                ", concentration=" + concentration +
                ", ritual=" + ritual +
                ", school=" + school +
                ", description=" + description +
                ", sourceId=" + sourceId +
                '}';
    }
}
