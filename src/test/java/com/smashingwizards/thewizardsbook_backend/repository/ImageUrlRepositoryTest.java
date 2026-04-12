package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ImageUrlRepositoryTest {

    @Autowired
    private ImageUrlRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a ImageUrl")
    void save_shouldPersistDamageUrl() {
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);

        ImageUrl saved = underTest.save(imageUrl);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("url", saved.getUrl());
        assertEquals("type", saved.getType());
        assertEquals("1234hash", saved.getHash());
        assertNull(saved.getCreatedAt());
    }

    @Test
    @DisplayName("findById() should return saved ImageUrl")
    void findById_shouldReturnSavedImageUrl() {
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);
        ImageUrl saved = underTest.save(imageUrl);

        Optional<ImageUrl> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("url", result.get().getUrl());
        assertEquals("type", result.get().getType());
        assertEquals("1234hash", result.get().getHash());
        assertNull(result.get().getCreatedAt());
    }

    @Test
    @DisplayName("findAll() should return all saved ImageUrls")
    void findAll_shouldReturnAllSavedImageUrl() {
        underTest.save(new ImageUrl("url1", "type1", "1234hash1", null));
        underTest.save(new ImageUrl("url2", "type2", "1234hash2", null));

        List<ImageUrl> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove effect")
    void deleteById_shouldRemoveImageUrl() {
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);
        ImageUrl saved = underTest.save(imageUrl);

        underTest.deleteById(saved.getId());

        Optional<ImageUrl> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
