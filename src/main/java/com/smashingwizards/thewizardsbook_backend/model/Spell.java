package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spells")
public class Spell {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "spell_name")
    private String name;

    @Getter @Setter
    @Column(name = "spell_level")
    private Integer level;

    @Getter @Setter
    @Column(name = "spell_casting_time")
    private String castingTime;

    @Getter @Setter
    @Column(name = "spell_range_area")
    private String rangeArea;

    @Getter @Setter
    @Column(name = "spell_component_visual", nullable = false)
    private Boolean componentVisual;

    @Getter @Setter
    @Column(name = "spell_component_semantic", nullable = false)
    private Boolean componentSemantic;

    @Getter @Setter
    @Column(name = "spell_component_material", nullable = false)
    private Boolean componentMaterial;

    @Getter @Setter
    @Column(name = "spell_component_materials")
    private String componentMaterials;

    @Getter @Setter
    @Column(name = "spell_duration", columnDefinition = "TEXT")
    private String duration;

    @Getter @Setter
    @Column(name = "spell_concentration", nullable = false)
    private Boolean concentration;

    @Getter @Setter
    @Column(name = "spell_ritual", nullable = false)
    private Boolean ritual;

    @Getter @Setter
    @Column(name = "spell_school")
    private String school;

    @Getter @Setter
    @Column(name = "spell_description")
    private String description;

    @Getter @Setter
    @Column(name = "spell_source_id")
    private Long sourceId;

    // CONs
    public Spell() {}
    public Spell(String name,
                 Integer level,
                 String castingTime,
                 String rangeArea,
                 Boolean componentVisual,
                 Boolean componentSemantic,
                 Boolean componentMaterial,
                 String componentMaterials,
                 String duration,
                 Boolean concentration,
                 Boolean ritual,
                 String school,
                 String description,
                 Long sourceId) {
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
                '}';
    }
}
