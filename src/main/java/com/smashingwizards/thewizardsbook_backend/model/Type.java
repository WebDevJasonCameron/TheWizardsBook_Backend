package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "types")
public class Type {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "type_name")
    private String name;

    @Getter @Setter
    @Column(name = "type_sub_type")
    private String subType;

    // CONs
    public Type() {}
    public Type(String name, String subType) {
        this.name = name;
        this.subType = subType;
    }

    // OVRs
    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subType='" + subType + '\'' +
                '}';
    }

}
