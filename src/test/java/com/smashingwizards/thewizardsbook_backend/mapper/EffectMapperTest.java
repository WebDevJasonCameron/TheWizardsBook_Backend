package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbook_backend.model.Effect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EffectMapperTest {

    @Autowired
    private EffectMapper effectMapper;

    @Test
    void effectToEffectDTO_shouldMapFieldCorrectly() {
        Effect effect = new Effect();
        effect.setId(1L);
        effect.setName("Test Effect");
        effect.setSubEffect("Test SubEffect");

        EffectDTO dto = effectMapper.effectToEffectDTO(effect);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Effect", dto.getName());
        assertEquals("Test SubEffect", dto.getSubEffect());
    }

    @Test
    void effectDTOToEffect_shouldMapFieldCorrectly() {
        EffectDTO dto = new EffectDTO();
        dto.setId(2L);
        dto.setName("Test Effect");
        dto.setSubEffect("Test SubEffect");

        Effect effect = effectMapper.effectDTOToEffect(dto);

        assertNotNull(effect);
        assertEquals(2L, effect.getId());
        assertEquals("Test Effect", effect.getName());
        assertEquals("Test SubEffect", effect.getSubEffect());
    }


}
