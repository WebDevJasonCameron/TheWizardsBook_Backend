package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class DamagetypeRepositoryTest {

    @Autowired
    private DamagetypeRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() {
        assertNotNull(underTest);
    }

    @Test
    @DisplayName("save() should persist a damagetype")
    void save_shouldPersistDamagetype() {
        Damagetype damagetype = new Damagetype("type name");

        Damagetype saved = underTest.save(damagetype);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("type name", saved.getName());
    }

    @Test
    @DisplayName("findById() should return saved damagetype id")
    void findById_shouldReturnSavedDamagetype() {
        Damagetype damagetype = new Damagetype("type name");
        Damagetype saved = underTest.save(damagetype);

        Optional<Damagetype> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("type name", result.get().getName());
    }

    @Test
    @DisplayName("findAll() should return all saved damagetypes")
    void findAll_shouldReturnAllDamagetypes() {
        underTest.save(new Damagetype("type name"));

        List<Damagetype> results = underTest.findAll();

        assertEquals(1, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove damagetype")
    void deleteById_shouldRemoveDamagetype() {
        Damagetype saved = underTest.save(new Damagetype("type name"));

        underTest.deleteById(saved.getId());

        Optional<Damagetype> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
