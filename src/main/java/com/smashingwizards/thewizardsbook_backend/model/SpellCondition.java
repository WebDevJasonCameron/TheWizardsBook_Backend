package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spell_conditions")
public class SpellCondition {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conditions_condition_id", nullable = false)
    private Condition condition;

    // CONs
    public SpellCondition() {}
    public SpellCondition(Spell spell, Condition condition) {
        this.spell = spell;
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
