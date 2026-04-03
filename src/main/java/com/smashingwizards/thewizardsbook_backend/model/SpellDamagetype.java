package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spell_damagetypes")
public class SpellDamagetype {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damagetypes_damagetype_id", nullable = false)
    private Damagetype damagetype;

    // CONs
    public SpellDamagetype() {
    }
    public SpellDamagetype(Damagetype damagetype, Spell spell) {
        this.damagetype = damagetype;
        this.spell = spell;
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

    public Damagetype getDamagetype() {
        return damagetype;
    }
    public void setDamagetype(Damagetype damagetype) {
        this.damagetype = damagetype;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellDamagetype{" +
                "id=" + id +
                ", spell=" + spell +
                ", damagetype=" + damagetype +
                '}';
    }
}
