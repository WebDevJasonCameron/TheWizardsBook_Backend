package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Source;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SourceRepositoryTest {

    @Autowired
    private SourceRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a condition")
    void save_shouldPersistCondition() {
        Source source = new Source("source name", "publish date", "source publisher", 1L);
        Source saved = underTest.save(source);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("source name", saved.getName());
        assertEquals("publish date", saved.getPublishDate());
    }

    @Test
    @DisplayName("findById() should return saved source")
    void findById_shouldReturnSavedSource() {
        Source source = new Source("source name", "publish date", "source publisher", 1L);
        Source saved = underTest.save(source);

        Optional<Source> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("source name", result.get().getName());
        assertEquals("publish date", result.get().getPublishDate());
        assertEquals("source publisher", result.get().getPublisher());
    }

    @Test
    @DisplayName("findAll() should return all saved sources")
    void findAll_shouldReturnAllSavedSources() {
        underTest.save(new Source("source name", "publish date", "source publisher", 1L));

        List<Source> results = underTest.findAll();

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove source")
    void deleteById_shouldRemoveSource() {
        Source saved = underTest.save(new Source("source name", "publish date", "source publisher", 1L));

        underTest.deleteById(saved.getId());

        Optional<Source> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
