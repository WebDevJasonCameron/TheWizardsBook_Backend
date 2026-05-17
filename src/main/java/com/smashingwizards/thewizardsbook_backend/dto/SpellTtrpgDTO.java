package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellTtrpgDTO {

    private Long id;
    private Long spellId;
    private Long ttrpgId;

    // CONs
    public SpellTtrpgDTO() {}
    public SpellTtrpgDTO(Long spellId, Long ttrpgId) {
        this.spellId = spellId;
        this.ttrpgId = ttrpgId;
    }
    public SpellTtrpgDTO(Long id, Long spellId, Long ttrpgId) {
        this.id = id;
        this.spellId = spellId;
        this.ttrpgId = ttrpgId;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellTtrpgDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", ttrpgId=" + ttrpgId +
                '}';
    }
}
