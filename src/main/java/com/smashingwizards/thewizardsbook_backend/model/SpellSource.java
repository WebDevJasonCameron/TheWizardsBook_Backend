package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "spell_sources")
public class SpellSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sources_source_id", nullable = false)
    private Source source;

    @Column(name = "spell_source_page")
    private String page;

    // CONs
    public SpellSource() {}
    public SpellSource(Spell spell, Source source, String page) {
        this.spell = spell;
        this.source = source;
        this.page = page;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellSource{" +
                "id=" + id +
                ", spell=" + spell +
                ", source=" + source +
                ", page=" + page +
                '}';
    }
}
