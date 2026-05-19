package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "spell_image_urls")
public class SpellImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spells_spell_id", nullable = false)
    private Spell spell;

    @ManyToOne
    @JoinColumn(name = "image_urls_image_url_id", nullable = false)
    private ImageUrl imageUrl;

    // CONs
    public SpellImageUrl() {}
    public SpellImageUrl(Spell spell, ImageUrl imageUrl) {
        this.spell = spell;
        this.imageUrl = imageUrl;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellImageUrl{" +
                "id=" + id +
                ", spell=" + spell +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
