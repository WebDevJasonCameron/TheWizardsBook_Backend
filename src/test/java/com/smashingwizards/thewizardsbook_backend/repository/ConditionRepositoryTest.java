package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConditionRepositoryTest {

    @Autowired
    private ConditionRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() {
        assertNotNull(underTest);
    }

    @Test
    @DisplayName("save() should persist a condition")
    void save_shouldPersistCondition() {
        Condition condition = new Condition("Blinded", "A blinded creature cannot see.");

        Condition saved = underTest.save(condition);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Blinded", saved.getName());
        assertEquals("A blinded creature cannot see.", saved.getDescription());
    }

    @Test
    @DisplayName("findById() should return saved condition")
    void findById_shouldReturnSavedCondition() {
        Condition condition = new Condition("Poisoned", "A poisoned creature has disadvantage.");
        Condition saved = underTest.save(condition);

        Optional<Condition> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Poisoned", result.get().getName());
        assertEquals("A poisoned creature has disadvantage.", result.get().getDescription());
    }

    @Test
    @DisplayName("findAll() should return all saved conditions")
    void findAll_shouldReturnAllConditions() {
        underTest.save(new Condition("Charmed", "Cannot attack the charmer."));
        underTest.save(new Condition("Frightened", "Has disadvantage while source of fear is in sight."));

        List<Condition> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove condition")
    void deleteById_shouldRemoveCondition() {
        Condition saved = underTest.save(new Condition("Restrained", "Speed becomes 0."));

        underTest.deleteById(saved.getId());

        Optional<Condition> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}