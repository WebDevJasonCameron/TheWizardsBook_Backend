package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TtrpgRepositoryTest {

    @Autowired
    private TtrpgRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void testRepositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a Ttrpg")
    void save_shouldPersistTtrpg() {
        Ttrpg Ttrpg = new Ttrpg("Test Ttrpg", "Ttrpg type");
        Ttrpg saved = underTest.save(Ttrpg);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Test Ttrpg", saved.getName());
        assertEquals("Ttrpg type", saved.getVersion());
    }

    @Test
    @DisplayName("findById() should return saved Ttrpg")
    void findById_shouldReturnSavedTtrpg() {
        Ttrpg Ttrpg = new Ttrpg("Test Ttrpg", "Ttrpg type");
        Ttrpg saved = underTest.save(Ttrpg);

        Optional<Ttrpg> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Test Ttrpg", result.get().getName());
        assertEquals("Ttrpg type", result.get().getVersion());
    }

    @Test
    @DisplayName("findAll() should return all saved ttrpg")
    void findAll_shouldReturnAllSavedTtrpg() {
        underTest.save(new Ttrpg("Test Ttrpg 1", "Ttrpg type 1"));
        underTest.save(new Ttrpg("Test Ttrpg 2", "Ttrpg type 2"));

        List<Ttrpg> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove Ttrpg")
    void deleteById_shouldRemoveTtrpg() {
        Ttrpg Ttrpg = new Ttrpg("Test Ttrpg", "Ttrpg type");
        Ttrpg saved = underTest.save(Ttrpg);

        underTest.deleteById(saved.getId());

        Optional<Ttrpg> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
