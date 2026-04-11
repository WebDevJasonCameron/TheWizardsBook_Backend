package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spell_tags")
public class SpellTag {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags_tag_id", nullable = false)
    private Tag tag;

    // CONs
    public SpellTag() {}
    public SpellTag(Spell spell, Tag tag) {
        this.spell = spell;
        this.tag = tag;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellTag{" +
                "id=" + id +
                ", spell=" + spell +
                ", tag=" + tag +
                '}';
    }
}
