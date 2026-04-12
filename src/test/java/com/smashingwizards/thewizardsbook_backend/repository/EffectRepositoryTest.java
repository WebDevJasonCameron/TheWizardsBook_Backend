package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Effect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EffectRepositoryTest {

    @Autowired
    private EffectRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() {
        assertNotNull(underTest);
    }

    @Test
    @DisplayName("save() should persist a effect")
    void save_shouldPersistEffect() {
        Effect effect = new Effect("effect name", "sub effect");

        Effect saved = underTest.save(effect);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("effect name", saved.getName());
        assertEquals("sub effect", saved.getSubEffect());
    }

    @Test
    @DisplayName("findById() should return saved effect id")
    void findById_shouldReturnSavedEffect() {
        Effect effect = new Effect("effect name", "sub effect");
        Effect saved = underTest.save(effect);

        Optional<Effect> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("effect name", result.get().getName());
        assertEquals("sub effect", result.get().getSubEffect());
    }

    @Test
    @DisplayName("findAll() should return all saved effects")
    void findAll_shouldReturnAllSavedEffects() {
        underTest.save(new Effect("effect name", "sub effect"));

        List<Effect> results = underTest.findAll();

        assertEquals(1, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove effect")
    void deleteById_shouldRemoveEffect() {
        Effect saved = underTest.save(new Effect("effect name", "sub effect"));

        underTest.deleteById(saved.getId());

        Optional<Effect> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }

}
