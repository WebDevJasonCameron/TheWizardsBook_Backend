package com.smashingwizards.thewizardsbook_backend.dto;

public class SpellDamagetypeDTO {

    // ATTs
    private Long id;
    private Long spellId;
    private Long damagetypeId;

    // CONs
    public SpellDamagetypeDTO() {
    }
    public SpellDamagetypeDTO(Long spellId, Long damagetypeId) {
        this.spellId = spellId;
        this.damagetypeId = damagetypeId;
    }

    // GETs & SETs
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpellId() {
        return spellId;
    }
    public void setSpellId(Long spellId) {
        this.spellId = spellId;
    }

    public Long getDamagetypeId() {
        return damagetypeId;
    }
    public void setDamagetypeId(Long damagetypeId) {
        this.damagetypeId = damagetypeId;
    }
}
