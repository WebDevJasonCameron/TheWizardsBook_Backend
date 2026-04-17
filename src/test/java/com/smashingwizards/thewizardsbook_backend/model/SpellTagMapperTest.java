package com.smashingwizards.thewizardsbook_backend.model;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellImageUrlMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpellTagMapperTest {


    private final SpellImageUrlMapper spellImageUrlMapper = Mappers.getMapper( SpellImageUrlMapper.class);

    @Test
    void spellImageUrlToSpellImageUrlDTO_shouldMapFieldCorrectly() {
        Spell spell = new Spell();
        spell.setId(10L);

        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setId(20L);

        SpellImageUrl spellImageUrl = new SpellImageUrl();
        spellImageUrl.setId(1L);
        spellImageUrl.setSpell(spell);
        spellImageUrl.setImageUrl(imageUrl);

        SpellImageUrlDTO dto = spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(spellImageUrl);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getSpellId());
        assertEquals(20L, dto.getImageUrlId());

    }
}
