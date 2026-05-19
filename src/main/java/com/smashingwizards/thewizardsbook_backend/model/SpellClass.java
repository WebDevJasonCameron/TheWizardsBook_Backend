package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "spell_classes")
public class SpellClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

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
