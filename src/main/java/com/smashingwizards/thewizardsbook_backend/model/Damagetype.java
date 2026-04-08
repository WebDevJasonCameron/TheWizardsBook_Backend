package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "damagetypes")
public class Damagetype {

    @Setter @Getter @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "damagetype_name")
    private String name;

    // CONs
    public Damagetype() {}
    public Damagetype(String name) {
        this.name = name;
    }

    // OVRs
    @Override
    public String toString() {
        return "Damagetype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
