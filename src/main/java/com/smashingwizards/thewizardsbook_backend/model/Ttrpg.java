package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ttrpgs")
public class Ttrpg {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "ttrpg_name", nullable = false, unique = true)
    private String name;

    @Getter @Setter
    @Column(name = "ttrpg_version", columnDefinition = "TEXT")
    private String version;

    // CONs
    public Ttrpg() {}
    public Ttrpg(String name, String version) {
        this.name = name;
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
