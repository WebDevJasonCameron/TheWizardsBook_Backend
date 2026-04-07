package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spell_tags")
public class SpellTag {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags_tag_id", nullable = false)
    private Tag tag;

    // CONs
    public SpellTag() {
    }
    public SpellTag(Spell spell, Tag tag) {
        this.spell = spell;
        this.tag = tag;
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

    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
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
