package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;
import com.smashingwizards.thewizardsbook_backend.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    void tagToTagDTO_shouldMapFieldCorrectly() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Test Tag");
        tag.setType("Test Type");

        TagDTO dto = tagMapper.tagToTagDTO(tag);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Tag", dto.getName());
        assertEquals("Test Type", dto.getType());
    }

    @Test
    void tagDTOToTag_shouldMapFieldCorrectly() {
        TagDTO dto = new TagDTO();
        dto.setId(2L);
        dto.setName("Test Tag");
        dto.setType("Test Type");

        Tag tag = tagMapper.tagDTOToTag(dto);

        assertNotNull(tag);
        assertEquals(2L, tag.getId());
        assertEquals("Test Tag", tag.getName());
        assertEquals("Test Type", tag.getType());
    }
}
