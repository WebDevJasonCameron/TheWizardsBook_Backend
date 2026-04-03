package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spell_classes")
public class SpellClass {

    // ATTs
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
    public SpellClass() {
    }
    public SpellClass(Spell spell, RpgClass rpgClass) {
        this.spell = spell;
        this.rpgClass = rpgClass;
    }

    // GETs & SETs
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Spell getSpell() {
        return spell;
    }
    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public RpgClass getRpgClass() {
        return rpgClass;
    }
    public void setRpgClass(RpgClass rpgClass) {
        this.rpgClass = rpgClass;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellClass{" +
                "id=" + id +
                ", spell=" + spell +
                ", rpgClass=" + rpgClass +
                '}';
    }
}
