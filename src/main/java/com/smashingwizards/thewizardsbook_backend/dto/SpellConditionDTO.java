package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpellConditionDTO {

    private Long id;
    private Long spellId;
    private Long conditionId;

    // CONs
    public SpellConditionDTO() {}
    public SpellConditionDTO(Long spellId, Long conditionId) {
        this.spellId = spellId;
        this.conditionId = conditionId;
    }
    public SpellConditionDTO(Long id, Long spellId, Long conditionId) {
        this.id = id;
        this.spellId = spellId;
        this.conditionId = conditionId;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellConditionDTO{" +
                "id=" + id +
                ", spellId=" + spellId +
                ", conditionId=" + conditionId +
                '}';
    }

}
