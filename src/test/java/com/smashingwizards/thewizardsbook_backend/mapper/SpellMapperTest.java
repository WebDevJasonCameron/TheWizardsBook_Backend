package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpellMapperTest {

    @Autowired
    private SpellMapper spellMapper;

    @Test
    void spellToSpellDTO_shouldMapFieldCorrectly() {
        Spell spell = new Spell();
        spell.setId(1L);
        spell.setName("Test Name");
        spell.setLevel("Test Level");
        spell.setCastingTime("Test CastingTime");
        spell.setRangeArea("Test Range");
        spell.setComponentVisual(false);
        spell.setComponentSemantic(false);
        spell.setComponentMaterial(false);
        spell.setComponentMaterials("Test Materials");
        spell.setDuration("Test Duration");
        spell.setConcentration(false);
        spell.setRitual(false);
        spell.setSchool("Test School");
        spell.setDescription("Test Description");

        SpellDTO dto = spellMapper.spellToSpellDTO(spell);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Name", dto.getName());
        assertEquals("Test Level", dto.getLevel());
        assertEquals("Test CastingTime", dto.getCastingTime());
        assertEquals("Test Range", dto.getRangeArea());
        assertFalse(dto.getComponentVisual());
        assertFalse(dto.getComponentSemantic());
        assertFalse( dto.getComponentMaterial());
        assertEquals("Test Materials", dto.getComponentMaterials());
        assertEquals("Test Duration", dto.getDuration());
        assertFalse(dto.getConcentration());
        assertFalse(dto.getRitual());
        assertEquals("Test School", dto.getSchool());
        assertEquals("Test Description", dto.getDescription());
    }

    @Test
    void spellDTOToSpell_shouldMapFieldCorrectly() {
        SpellDTO dto = new SpellDTO();
        dto.setId(2L);
        dto.setName("Test Name");
        dto.setLevel("Test Level");
        dto.setCastingTime("Test CastingTime");
        dto.setRangeArea("Test Range");
        dto.setComponentVisual(false);
        dto.setComponentSemantic(false);
        dto.setComponentMaterial(false);
        dto.setComponentMaterials("Test Materials");
        dto.setDuration("Test Duration");
        dto.setConcentration(false);
        dto.setRitual(false);
        dto.setSchool("Test School");
        dto.setDescription("Test Description");

        Spell spell = spellMapper.spellDTOToSpell(dto);

        assertNotNull(spell);
        assertEquals(2L, spell.getId());
        assertEquals("Test Name", spell.getName());
        assertEquals("Test Level", spell.getLevel());
        assertFalse(spell.getComponentVisual ());
        assertFalse(spell.getComponentSemantic());
        assertFalse(spell.getComponentMaterial());
        assertEquals("Test Materials", spell.getComponentMaterials());
        assertEquals("Test Duration", spell.getDuration());
        assertFalse(spell.getConcentration());
        assertFalse(spell.getRitual());
        assertEquals("Test School", spell.getSchool());
        assertEquals("Test Description", spell.getDescription());
    }
}
