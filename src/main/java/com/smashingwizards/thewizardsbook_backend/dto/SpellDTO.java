package com.smashingwizards.thewizardsbook_backend.dto;

public class SpellDTO {

    // ATTs
    private Long id;
    private String name;
    private Long ttrpgId;
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
    public SpellDTO(String name, Long ttrpgId, String level, String castingTime, String rangeArea, boolean componentVisual, boolean componentSemantic, boolean componentMaterial, String componentMaterials, String duration, boolean concentration, boolean ritual, String school, String description, Long sourceId) {
        this.name = name;
        this.ttrpgId = ttrpgId;
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

    public Long getTtrpgId() {
        return ttrpgId;
    }
    public void setTtrpgId(Long ttrpgId) {
        this.ttrpgId = ttrpgId;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getCastingTime() {
        return castingTime;
    }
    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRangeArea() {
        return rangeArea;
    }
    public void setRangeArea(String rangeArea) {
        this.rangeArea = rangeArea;
    }

    public boolean isComponentVisual() {
        return componentVisual;
    }
    public void setComponentVisual(boolean componentVisual) {
        this.componentVisual = componentVisual;
    }

    public boolean isComponentSemantic() {
        return componentSemantic;
    }
    public void setComponentSemantic(boolean componentSemantic) {
        this.componentSemantic = componentSemantic;
    }

    public boolean isComponentMaterial() {
        return componentMaterial;
    }
    public void setComponentMaterial(boolean componentMaterial) {
        this.componentMaterial = componentMaterial;
    }

    public String getComponentMaterials() {
        return componentMaterials;
    }
    public void setComponentMaterials(String componentMaterials) {
        this.componentMaterials = componentMaterials;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }
    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public boolean isRitual() {
        return ritual;
    }
    public void setRitual(boolean ritual) {
        this.ritual = ritual;
    }

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSourceId() {
        return sourceId;
    }
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }
}
