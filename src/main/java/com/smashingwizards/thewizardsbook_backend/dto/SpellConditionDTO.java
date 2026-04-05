package com.smashingwizards.thewizardsbook_backend.dto;

public class SpellConditionDTO {

    // ATTs
    private Long id;
    private Long spellId;
    private Long conditionId;

    // CONs
    public SpellConditionDTO() {
    }
    public SpellConditionDTO(Long spellId, Long conditionId) {
        this.spellId = spellId;
        this.conditionId = conditionId;
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

    public Long getConditionId() {
        return conditionId;
    }
    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }
}
