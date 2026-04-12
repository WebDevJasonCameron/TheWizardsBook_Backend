package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spell_sources")
public class SpellSource {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sources_source_id", nullable = false)
    private Source source;

    @Getter @Setter
    @Column(name = "spell_source_page")
    private String page;

    // CONs
    public SpellSource() {}
    public SpellSource(Spell spell, Source source) {
        this.spell = spell;
        this.source = source;
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
