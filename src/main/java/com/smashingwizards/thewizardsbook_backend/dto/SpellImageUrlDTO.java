package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellImageUrlDTO {

    private Long id;
    private Long spellId;
    private Long imageUrlId;

    // CONs
    public SpellImageUrlDTO() {}
    public SpellImageUrlDTO(Long spellId, Long imageUrlId) {
        this.spellId = spellId;
        this.imageUrlId = imageUrlId;
    }
    public SpellImageUrlDTO(Long id, Long spellId, Long imageUrlId) {
        this.id = id;
        this.spellId = spellId;
        this.imageUrlId = imageUrlId;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellImageUrlDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", imageUrlId=" + imageUrlId +
                '}';
    }
}
