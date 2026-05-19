package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "spells")
public class Spell {

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

    // CONs
    public Spell() {}
    public Spell(String name,
                 String level,
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
                 String description) {
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
                '}';
    }
}
