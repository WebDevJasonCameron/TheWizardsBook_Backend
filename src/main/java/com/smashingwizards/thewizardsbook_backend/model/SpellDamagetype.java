package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spell_damagetypes")
public class SpellDamagetype {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damagetypes_damagetype_id", nullable = false)
    private Damagetype damagetype;

    // CONs
    public SpellDamagetype() {}
    public SpellDamagetype(Spell spell, Damagetype damagetype) {
        this.spell = spell;
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
