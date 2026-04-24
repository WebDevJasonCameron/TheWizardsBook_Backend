package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.service.SourceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
public class SourceRepositoryTest {

    @Autowired
    private SourceRepository underTest;
    @Autowired
    private SourceService sourceService;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a condition")
    void save_shouldPersistCondition() {
        Source source = new Source("source name", "publish date", "source publisher");
        Source saved = underTest.save(source);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("source name", saved.getName());
        assertEquals("publish date", saved.getPublishDate());
    }

    @Test
    @DisplayName("findById() should return saved source")
    void findById_shouldReturnSavedSource() {
        Source source = new Source("source name", "publish date", "source publisher");
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
        underTest.save(new Source("source name", "publish date", "source publisher"));

        List<Source> results = underTest.findAll();

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove source")
    void deleteById_shouldRemoveSource() {
        Source saved = underTest.save(new Source("source name", "publish date", "source publisher"));

        underTest.deleteById(saved.getId());

        Optional<Source> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }

    /** ADDs */
    @Test
    @DisplayName("findAllByNameContainingIgnoreCase() should return sources with matching name")
    void findAllByNameContainingIgnoreCase_shouldReturnMatchingSources() {
        Source source1 = new Source("Test Name 1", "Test Publish Date 1", "Test Publisher 1");
        Source source2 = new Source("Test Name 2", "Test Publish Date 2", "Test Publisher 2");
        Source source3 = new Source("Name 3", "Test Publish Date 3", "Test Publisher 3");

        underTest.save(source1);
        underTest.save(source2);
        underTest.save(source3);

        List<Source> results = underTest.findAllByNameContainingIgnoreCase("Test");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(source -> source.getName().equals("Test Name 1")));
        assertTrue(results.stream().anyMatch(source -> source.getName().equals("Test Name 2")));
    }

}
