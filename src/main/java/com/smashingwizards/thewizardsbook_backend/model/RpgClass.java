package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rpg_classes")
public class RpgClass {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "class_name")
    private String name;
    @Column(name = "class_subclass_name")
    private String subClassName;
    @Column(name = "class_description", columnDefinition = "TEXT")
    private String description;

    // CONs
    public RpgClass() {
    }

    public RpgClass(String name, String subClassName, String description) {
        this.name = name;
        this.subClassName = subClassName;
        this.description = description;
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

    public String getSubClassName() {
        return subClassName;
    }
    public void setSubClassName(String subClassName) {
        this.subClassName = subClassName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // OVRs
    @Override
    public String toString() {
        return "RpgClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subClassName='" + subClassName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
