package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "spell_ttrpgs")
public class SpellTtrpg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ttrpgs_ttrpg_id", nullable = false)
    private Ttrpg ttrpg;

    // CONs
    public SpellTtrpg() {}
    public SpellTtrpg(Spell spell, Ttrpg ttrpg) {
        this.spell = spell;
        this.ttrpg = ttrpg;
    }
    public SpellTtrpg(Long id, Spell spell, Ttrpg ttrpg) {
        this.id = id;
        this.spell = spell;
        this.ttrpg = ttrpg;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellTtrpg{" +
                "id=" + id +
                ", spell=" + spell +
                ", ttrpg=" + ttrpg +
                '}';
    }
}
