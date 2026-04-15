package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.model.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SourceMapperTest {

    @Autowired
    private SourceMapper sourceMapper;

    @Test
    void sourceToSourceDTO_shouldMapFieldCorrectly() {
        Source source = new Source();
        source.setId(1L);
        source.setName("Test Source");
        source.setPublishDate("Test PublishDate");
        source.setPublisher("Test Publisher");

        SourceDTO dto = sourceMapper.sourceToSourceDTO(source);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Source", dto.getName());
        assertEquals("Test PublishDate", dto.getPublishDate());
        assertEquals("Test Publisher", dto.getPublisher());
    }

    @Test
    void sourceDTOToSource_shouldMapFieldCorrectly() {
        SourceDTO dto = new SourceDTO();
        dto.setId(2L);
        dto.setName("Test Source");
        dto.setPublishDate("Test PublishDate");
        dto.setPublisher("Test Publisher");

        Source source = sourceMapper.sourceDTOToSource(dto);

        assertNotNull(source);
        assertEquals(2L, source.getId());
        assertEquals("Test Source", source.getName());
        assertEquals("Test PublishDate", source.getPublishDate());
        assertEquals("Test Publisher", source.getPublisher());
    }
}
