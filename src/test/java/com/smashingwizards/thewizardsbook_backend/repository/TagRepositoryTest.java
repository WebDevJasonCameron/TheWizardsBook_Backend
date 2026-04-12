package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    private TagRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void testRepositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a tag")
    void save_shouldPersistTag() {
        Tag tag = new Tag("tag name", "tag type");
        Tag saved = underTest.save(tag);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("tag name", saved.getName());
        assertEquals("tag type", saved.getType());
    }

    @Test
    @DisplayName("findById() should return saved tag")
    void findById_shouldReturnSavedTag() {
        Tag tag = new Tag("tag name", "tag type");
        Tag saved = underTest.save(tag);

        Optional<Tag> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("tag name", result.get().getName());
        assertEquals("tag type", result.get().getType());
    }

    @Test
    @DisplayName("findAll() should return all saved tags")
    void findAll_shouldReturnAllSavedTags() {
        underTest.save(new Tag("tag1", "tag type1"));
        underTest.save(new Tag("tag2", "tag type2"));

        List<Tag> result = underTest.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("deleteById() should remove tag")
    void deleteById_shouldRemoveTag() {
        Tag tag = new Tag("tag name", "tag type");
        Tag saved = underTest.save(tag);

        underTest.deleteById(saved.getId());

        Optional<Tag> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
