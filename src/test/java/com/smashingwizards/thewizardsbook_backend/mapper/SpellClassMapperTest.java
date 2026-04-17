package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SpellClassMapperTest {

    private final SpellClassMapper spellClassMapper = Mappers.getMapper(SpellClassMapper.class);

    @Test
    void spellClassToSpellClassDTO_shouldMapNestedIdsCorrectly() {
        Spell spell = new Spell();
        spell.setId(10L);

        RpgClass rpgClass = new RpgClass();
        rpgClass.setId(20L);

        SpellClass spellClass = new SpellClass();
        spellClass.setId(1L);
        spellClass.setSpell(spell);
        spellClass.setRpgClass(rpgClass);

        SpellClassDTO dto = spellClassMapper.spellClassToSpellClassDTO(spellClass);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getSpellId());
        assertEquals(20L, dto.getRpgClassId());
    }
}