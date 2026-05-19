package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "rpg_classes")
public class RpgClass {

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
    public RpgClass() {}
    public RpgClass(String name, String subClassName, String description) {
        this.name = name;
        this.subClassName = subClassName;
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
