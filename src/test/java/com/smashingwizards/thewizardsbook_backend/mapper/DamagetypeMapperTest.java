package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DamagetypeMapperTest {

    @Autowired
    private DamagetypeMapper damagetypeMapper;

    @Test
    void damagetypeToDamagetypeDTO_shouldMapFieldCorrectly() {
        Damagetype damagetype = new Damagetype();
        damagetype.setId(1L);
        damagetype.setName("damage type name");

        DamagetypeDTO dto = damagetypeMapper.damagetypeToDamagetypeDTO(damagetype);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("damage type name", dto.getName());
    }

    @Test
    void damagetypeDTOToDamagetype_shouldMapFieldCorrectly() {
        DamagetypeDTO dto = new DamagetypeDTO();
        dto.setId(2L);
        dto.setName("damage type name");

        Damagetype damagetype = damagetypeMapper.damagetypeDTOToDamagetype(dto);

        assertNotNull(damagetype);
        assertEquals(2L, damagetype.getId());
        assertEquals("damage type name", damagetype.getName());
    }
}
