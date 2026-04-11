package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spell_classes")
public class SpellClass {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classes_class_id", nullable = false)
    private RpgClass rpgClass;

    // CONs
    public SpellClass() {}
    public SpellClass(Spell spell, RpgClass rpgClass) {
        this.spell = spell;
        this.rpgClass = rpgClass;
    }

    // OVRs
    public String toString() {
        return "SpellClass{" +
                "id=" + id +
                ", spell=" + spell +
                ", rpgClass=" + rpgClass +
                '}';
    }
}
