package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellTagDTO {

    private Long id;
    private Long spellId;
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
