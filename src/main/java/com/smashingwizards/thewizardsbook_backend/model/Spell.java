package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spells")
public class Spell {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "spell_name")
    private String name;
    @Column(name = "spell_level")
    private String level;
    @Column(name = "spell_casting_time")
    private String castingTime;
    @Column(name = "spell_range_area")
    private String rangeArea;
    @Column(name = "spell_component_visual", nullable = false)
    private Boolean componentVisual;
    @Column(name = "spell_component_semantic", nullable = false)
    private Boolean componentSemantic;
    @Column(name = "spell_component_material", nullable = false)
    private Boolean componentMaterial;
    @Column(name = "spell_component_materials")
    private String componentMaterials;
    @Column(name = "spell_duration", columnDefinition = "TEXT")
    private String duration;
    @Column(name = "spell_concentration", nullable = false)
    private Boolean concentration;
    @Column(name = "spell_ritual", nullable = false)
    private Boolean ritual;
    @Column(name = "spell_school")
    private String school;
    @Column(name = "spell_description")
    private String description;
    @Column(name = "spell_source_id")
    private Long sourceId;

    @OneToMany(mappedBy = "spell", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SpellTag> spellTags = new ArrayList<>();
    @OneToMany(mappedBy = "spell", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SpellCondition> spellConditions = new ArrayList<>();
    @OneToMany(mappedBy = "spell", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SpellClass> spellClasses = new ArrayList<>();
    @OneToMany(mappedBy = "spell", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SpellDamagetype> spellDamagetypes = new ArrayList<>();

    // CONs
    public Spell() {
    }
    public Spell(String name, String level, String castingTime, String rangeArea, Boolean componentVisual, Boolean componentSemantic, Boolean componentMaterial, String componentMaterials, String duration, Boolean concentration, Boolean ritual, String school, String description, Long sourceId, Ttrpg ttrpg) {
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
        this.ttrpg = ttrpg;
    }
    public Spell(String name, String level, String castingTime, String rangeArea, Boolean componentVisual, Boolean componentSemantic, Boolean componentMaterial, String componentMaterials, String duration, Boolean concentration, Boolean ritual, String school, String description, Long sourceId, Ttrpg ttrpg,  List<SpellTag> spellTags, List<SpellCondition> spellConditions, List<SpellClass> spellClasses, List<SpellDamagetype> spellDamagetypes) {
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
        this.ttrpg = ttrpg;
        this.spellTags = spellTags;
        this.spellConditions = spellConditions;
        this.spellClasses = spellClasses;
        this.spellDamagetypes = spellDamagetypes;
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

    public Ttrpg getTtrpg() {
        return ttrpg;
    }
    public void setTtrpg(Ttrpg ttrpg) {
        this.ttrpg = ttrpg;
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

    public Boolean getComponentVisual() {
        return componentVisual;
    }
    public void setComponentVisual(Boolean componentVisual) {
        this.componentVisual = componentVisual;
    }

    public Boolean getComponentSemantic() {
        return componentSemantic;
    }
    public void setComponentSemantic(Boolean componentSemantic) {
        this.componentSemantic = componentSemantic;
    }

    public Boolean getComponentMaterial() {
        return componentMaterial;
    }
    public void setComponentMaterial(Boolean componentMaterial) {
        this.componentMaterial = componentMaterial;
    }

    public Boolean getConcentration() {
        return concentration;
    }
    public void setConcentration(Boolean concentration) {
        this.concentration = concentration;
    }

    public Boolean getRitual() {
        return ritual;
    }
    public void setRitual(Boolean ritual) {
        this.ritual = ritual;
    }

    // GETs & SETs LISTs
    public List<SpellTag> getSpellTags() {
        return spellTags;
    }
    public void setSpellTags(List<SpellTag> spellTags) {
        this.spellTags = spellTags;
    }

    public List<SpellCondition> getSpellConditions() {
        return spellConditions;
    }
    public void setSpellConditions(List<SpellCondition> spellConditions) {
        this.spellConditions = spellConditions;
    }

    public List<SpellClass> getSpellClasses() {
        return spellClasses;
    }
    public void setSpellClasses(List<SpellClass> spellClasses) {
        this.spellClasses = spellClasses;
    }

    public List<SpellDamagetype> getSpellDamagetypes() {
        return spellDamagetypes;
    }
    public void setSpellDamagetypes(List<SpellDamagetype> spellDamagetypes) {
        this.spellDamagetypes = spellDamagetypes;
    }

    // TRNs
    @Transient
    public List<Tag> getTags(){
        return spellTags.stream().map(SpellTag::getTag).toList();
    }
    @Transient
    public List<Condition> getConditions(){
        return spellConditions.stream().map(SpellCondition::getCondition).toList();
    }
    @Transient
    public List<RpgClass> getRpgClasses(){
        return spellClasses.stream().map(SpellClass::getRpgClass).toList();
    }
    @Transient
    public List<Damagetype> getDamagetypes(){
        return spellDamagetypes.stream().map(SpellDamagetype::getDamagetype).toList();
    }

    // OVRs
    @Override
    public String toString() {
        return "Spell{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", castingTime='" + castingTime + '\'' +
                ", rangeArea='" + rangeArea + '\'' +
                ", componentVisual=" + componentVisual +
                ", componentSemantic=" + componentSemantic +
                ", componentMaterial=" + componentMaterial +
                ", componentMaterials='" + componentMaterials + '\'' +
                ", duration='" + duration + '\'' +
                ", concentration=" + concentration +
                ", ritual=" + ritual +
                ", school='" + school + '\'' +
                ", description='" + description + '\'' +
                ", sourceId=" + sourceId +
                ", ttrpg=" + ttrpg +
                ", spellTags=" + spellTags +
                ", spellConditions=" + spellConditions +
                ", spellClasses=" + spellClasses +
                ", spellDamagetypes=" + spellDamagetypes +
                '}';
    }
}
