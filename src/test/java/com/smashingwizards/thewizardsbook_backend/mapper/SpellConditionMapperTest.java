package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import com.smashingwizards.thewizardsbook_backend.model.Condition;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellCondition;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class SpellConditionMapperTest {

    private final SpellConditionMapper spellConditionMapper = Mappers.getMapper( SpellConditionMapper.class);

    @Test
    void spellConditionToSpellConditionDTO_shouldMapFieldCorrectly() {
        Spell spell = new Spell();
        spell.setId(10L);

        Condition condition = new Condition();
        condition.setId(20L);

        SpellCondition spellCondition = new SpellCondition();
        spellCondition.setId(1L);
        spellCondition.setSpell(spell);
        spellCondition.setCondition(condition);

        SpellConditionDTO dto = spellConditionMapper.spellConditionToSpellConditionDTO(spellCondition);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getSpellId());
        assertEquals(20L, dto.getConditionId());
    }
}
