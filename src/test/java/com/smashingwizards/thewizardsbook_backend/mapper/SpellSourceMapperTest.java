package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpellSourceMapperTest {

    private final SpellTtrpgMapper spellTtrpgMapper = Mappers.getMapper( SpellTtrpgMapper.class);

    @Test
    void spellTtrpgToSpellTtrpgDTO_shouldMapFieldCorrectly() {
        Spell spell = new Spell();
        spell.setId(10L);

        Ttrpg source = new Ttrpg();
        source.setId(20L);

        SpellTtrpg spellTtrpg = new SpellTtrpg();
        spellTtrpg.setId(1L);
        spellTtrpg.setSpell(spell);
        spellTtrpg.setTtrpg(source);

        SpellTtrpgDTO dto = spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(spellTtrpg);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getSpellId());
        assertEquals(20L, dto.getTtrpgId());
    }
}
