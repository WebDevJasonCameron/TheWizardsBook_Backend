
package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RpgClassMapperTest {

    @Autowired
    private RpgClassMapper rpgClassMapper;

    @Test
    void rpgClassToRpgClassDTO_shouldMapFieldCorrectly() {
        RpgClass rpgClass = new RpgClass();
        rpgClass.setId(1L);
        rpgClass.setName("Test Class");
        rpgClass.setSubClassName("Test SubClass");
        rpgClass.setDescription("Test Description");

        RpgClassDTO dto = rpgClassMapper.rpgClassToRpgClassDTO(rpgClass);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Class", dto.getName());
        assertEquals("Test SubClass", dto.getSubClassName());
        assertEquals("Test Description", dto.getDescription());
    }

    @Test
    void rpgClassDTOToRpgClass_shouldMapFieldCorrectly() {
        RpgClassDTO dto = new RpgClassDTO();
        dto.setId(2L);
        dto.setName("Test Class");
        dto.setSubClassName("Test SubClass");
        dto.setDescription("Test Description");

        RpgClass rpgClass = rpgClassMapper.rpgClassDTOToRpgClass(dto);

        assertNotNull(rpgClass);
        assertEquals(2L, rpgClass.getId());
        assertEquals("Test Class", rpgClass.getName());
        assertEquals("Test SubClass", rpgClass.getSubClassName());
        assertEquals("Test Description", rpgClass.getDescription());
    }
}
