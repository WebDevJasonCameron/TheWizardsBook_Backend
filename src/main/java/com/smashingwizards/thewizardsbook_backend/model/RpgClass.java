package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rpg_classes")
public class RpgClass {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "class_name")
    private String name;

    @Getter @Setter
    @Column(name = "class_subclass_name")
    private String subClassName;

    @Getter @Setter
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
