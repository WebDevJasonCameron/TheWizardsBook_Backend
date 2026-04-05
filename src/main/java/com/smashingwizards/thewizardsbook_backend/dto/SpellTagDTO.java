package com.smashingwizards.thewizardsbook_backend.dto;

public class SpellTagDTO {

    // ATTs
    private Long id;
    private Long spellId;
    private Long tagId;

    // CONs
    public SpellTagDTO() {
    }
    public SpellTagDTO(Long spellId, Long tagId) {
        this.spellId = spellId;
        this.tagId = tagId;
    }

    // GETS & SETS
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

    public Long getTagId() {
        return tagId;
    }
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
