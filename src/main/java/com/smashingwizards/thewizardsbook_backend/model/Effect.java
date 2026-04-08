package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "effects")
public class Effect {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "effect_name")
    private String name;

    @Getter @Setter
    @Column(name = "effect_sub_effect")
    private String subEffect;

    // CONs
    public Effect() {}
    public Effect(String name, String subEffect) {
        this.name = name;
        this.subEffect = subEffect;
    }

    // OVRs
    @Override
    public String toString() {
        return "Effect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subEffect='" + subEffect + '\'' +
                '}';
    }
}
