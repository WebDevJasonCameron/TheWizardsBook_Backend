package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbook_backend.model.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TypeMapperTest {

    @Autowired
    private TypeMapper typeMapper;

    @Test
    void typeToTypeDTO_shouldMapFieldCorrectly() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Test Type");
        type.setSubType("Test SubType");

        TypeDTO typeDTO = typeMapper.typeToTypeDTO(type);

        assertNotNull(typeDTO);
        assertEquals(1L, typeDTO.getId());
        assertEquals("Test Type", typeDTO.getName());
        assertEquals("Test SubType", typeDTO.getSubType());
    }

    @Test
    void typeDTOToType_shouldMapFieldCorrectly() {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setId(2L);
        typeDTO.setName("Test Type");
        typeDTO.setSubType("Test SubType");

        Type type = typeMapper.typeDTOToType(typeDTO);

        assertNotNull(type);
        assertEquals(2L, type.getId());
        assertEquals("Test Type", type.getName());
        assertEquals("Test SubType", type.getSubType());
    }

}
