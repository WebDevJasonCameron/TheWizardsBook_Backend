package com.smashingwizards.thewizardsbook_backend.dto;

public class SpellClassDTO {

    // ATTs
    private Long id;
    private Long spellId;
    private Long rpgClassId;

    // CONs
    public SpellClassDTO() {
    }
    public SpellClassDTO(Long spellId, Long rpgClassId) {
        this.spellId = spellId;
        this.rpgClassId = rpgClassId;
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

    public Long getRpgClassId() {
        return rpgClassId;
    }
    public void setRpgClassId(Long rpgClassId) {
        this.rpgClassId = rpgClassId;
    }
}
