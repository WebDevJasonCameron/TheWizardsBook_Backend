package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellSourceDTO {

    private Long id;
    private Long spellId;
    private Long sourceId;
    private String page;

    // CONs
    public SpellSourceDTO() {}
    public SpellSourceDTO(Long spellId, Long sourceId, String page) {
        this.spellId = spellId;
        this.sourceId = sourceId;
        this.page = page;
    }
    public SpellSourceDTO(Long id, Long spellId, Long sourceId, String page) {
        this.id = id;
        this.spellId = spellId;
        this.sourceId = sourceId;
        this.page = page;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellSourceDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", sourceId=" + sourceId +
                ", page='" + page + '\'' +
                '}';
    }
}
