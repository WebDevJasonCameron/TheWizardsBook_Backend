package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellClassDTO {

    private Long id;
    private Long spellId;
    private Long rpgClassId;

    // CONs
    public SpellClassDTO() {}
    public SpellClassDTO(Long spellId, Long rpgClassId) {
        this.spellId = spellId;
        this.rpgClassId = rpgClassId;
    }
    public SpellClassDTO(Long id, Long spellId, Long rpgClassId) {
        this.id = id;
        this.spellId = spellId;
        this.rpgClassId = rpgClassId;
    }


    // OVRs
    @Override
    public String toString() {
        return "SpellClassDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", rpgClassId=" + rpgClassId +
                '}';
    }
}
