package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class SpellDTO {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String level;
    @Getter @Setter
    private String castingTime;
    @Getter @Setter
    private String rangeArea;
    @Getter @Setter
    private boolean componentVisual;
    @Getter @Setter
    private boolean componentSemantic;
    @Getter @Setter
    private boolean componentMaterial;
    @Getter @Setter
    private String componentMaterials;
    @Getter @Setter
    private String duration;
    @Getter @Setter
    private boolean concentration;
    @Getter @Setter
    private boolean ritual;
    @Getter @Setter
    private String school;
    @Getter @Setter
    private String description;
    @Getter @Setter
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
