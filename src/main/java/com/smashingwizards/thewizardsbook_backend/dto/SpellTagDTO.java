package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class SpellTagDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long spellId;

    @Getter @Setter
    private Long tagId;

    // CONs
    public SpellTagDTO() {}
    public SpellTagDTO(Long spellId, Long tagId) {
        this.spellId = spellId;
        this.tagId = tagId;
    }
    public SpellTagDTO(Long id, Long spellId, Long tagId) {
        this.id = id;
        this.spellId = spellId;
        this.tagId = tagId;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellTagDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", tagId=" + tagId +
                '}';
    }
}
