package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellDamagetype;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpellDamagetypeMapperTest {

    private final SpellDamagetypeMapper spellDamagetypeMapper = Mappers.getMapper( SpellDamagetypeMapper.class);

    @Test
    void spellDamagetypeToSpellDamagetypeDTO_shouldMapFieldCorrectly() {
        Spell spell = new Spell();
        spell.setId(10L);

        Damagetype damagetype = new Damagetype();
        damagetype.setId(20L);

        SpellDamagetype spellDamagetype = new SpellDamagetype();
        spellDamagetype.setId(1L);
        spellDamagetype.setSpell(spell);
        spellDamagetype.setDamagetype(damagetype);

        SpellDamagetypeDTO dto = spellDamagetypeMapper.spellDamagetypeToSpellDamagetypeDTO(spellDamagetype);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getSpellId());
        assertEquals(20L, dto.getDamagetypeId());
    }
}
