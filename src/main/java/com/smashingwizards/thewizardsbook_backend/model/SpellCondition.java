package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spell_conditions")
public class SpellCondition {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conditions_condition_id", nullable = false)
    private Condition condition;

    // CONs
    public SpellCondition() {
    }
    public SpellCondition(Spell spell, Condition condition) {
        this.spell = spell;
        this.condition = condition;
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

    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellCondition{" +
                "id=" + id +
                ", spell=" + spell +
                ", condition=" + condition +
                '}';
    }
}
