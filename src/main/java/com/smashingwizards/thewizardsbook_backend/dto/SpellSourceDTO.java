package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class SpellSourceDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long spellId;

    @Getter @Setter
    private Long sourceId;

    @Getter @Setter
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
