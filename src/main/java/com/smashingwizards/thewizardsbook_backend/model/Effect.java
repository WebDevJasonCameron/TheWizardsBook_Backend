package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "effects")
public class Effect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "effect_name")
    private String name;

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
        return "EffectRepository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subEffect='" + subEffect + '\'' +
                '}';
    }
}
