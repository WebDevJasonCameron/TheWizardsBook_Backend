package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellDamagetypeDTO {

    private Long id;
    private Long spellId;
    private Long damagetypeId;

    // CONs
    public SpellDamagetypeDTO() {}
    public SpellDamagetypeDTO(Long spellId, Long damagetypeId) {
        this.spellId = spellId;
        this.damagetypeId = damagetypeId;
    }
    public SpellDamagetypeDTO(Long id, Long spellId, Long damagetypeId) {
        this.id = id;
        this.spellId = spellId;
        this.damagetypeId = damagetypeId;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellDamagetypeDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", damagetypeId=" + damagetypeId +
                '}';
    }
}
