package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.ImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ImageUrlMapperTest {

    @Autowired
    private ImageUrlMapper ImageUrlMapper;

    @Test
    void imageUrlToImageUrlDTO_shouldMapFieldCorrectly() {
        Instant instant = Instant.now();

        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setId(1L);
        imageUrl.setUrl("Test URL");
        imageUrl.setType("Test Type");
        imageUrl.setHash("Test Hash");
        imageUrl.setCreatedAt(instant);

        ImageUrlDTO dto = ImageUrlMapper.imageUrlToImageUrlDTO(imageUrl);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test URL", dto.getUrl());
        assertEquals("Test Type", dto.getType());
        assertEquals("Test Hash", dto.getHash());
        assertEquals(instant, dto.getCreatedAt());
    }

    @Test
    void imageUrlDTOToImageUrl_shouldMapFieldCorrectly() {
        ImageUrlDTO dto = new ImageUrlDTO();
        dto.setId(2L);
        dto.setUrl("Test URL");
        dto.setType("Test Type");
        dto.setHash("Test Hash");
        dto.setCreatedAt(Instant.now());

        ImageUrl imageUrl = ImageUrlMapper.imageUrlDTOToImageUrl(dto);

        assertNotNull(imageUrl);
        assertEquals(2L, imageUrl.getId());
        assertEquals("Test URL", imageUrl.getUrl());
        assertEquals("Test Type", imageUrl.getType());
        assertEquals("Test Hash", imageUrl.getHash());
        assertEquals(dto.getCreatedAt(), imageUrl.getCreatedAt());
    }
}
