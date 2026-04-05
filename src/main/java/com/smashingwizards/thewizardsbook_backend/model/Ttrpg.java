package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ttrpgs")
public class Ttrpg {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ttrpg_name", nullable = false, unique = true)
    private String name;

    @Column(name = "ttrpg_version", columnDefinition = "TEXT")
    private String version;

    // CONs
    public Ttrpg() {
    }
    public Ttrpg(String name, String version) {
        this.name = name;
        this.version = version;
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

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    // OVRs
    @Override
    public String toString() {
        return "Ttrpg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
