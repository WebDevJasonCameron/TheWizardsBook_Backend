package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "damagetypes")
public class Damagetype {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "damagetype_name")
    private String name;

    // CONs
    public Damagetype() {
    }

    public Damagetype(String name) {
        this.name = name;
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

    // METHs
    @Override
    public String toString() {
        return "DamageType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
