package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RpgClassRepositoryTest {

    @Autowired
    private RpgClassRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    public void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a RpgClass")
    void save_shouldPersistRpgClass() {
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");

        underTest.save(rpgClass);

        assertNotNull(rpgClass.getId());
        assertEquals("class name", rpgClass.getName());
        assertEquals("subclass name", rpgClass.getSubClassName());
        assertEquals("description", rpgClass.getDescription());
    }

    @Test
    @DisplayName("findById() should return saved RpgClass")
    void findById_shouldReturnSavedRpgClass() {
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");
        RpgClass saved = underTest.save(rpgClass);

        Optional<RpgClass> result = underTest.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("class name", result.get().getName());
        assertEquals("subclass name", result.get().getSubClassName());
        assertEquals("description", result.get().getDescription());
        assertNotNull(result.get().getId());
    }

    @Test
    @DisplayName("findAll() should return all saved RpgClasses")
    void findAll_shouldReturnAllSavedRpgClasses() {
        underTest.save(new RpgClass("class name", "subclass name", "description"));
        underTest.save(new RpgClass("class name 2", "subclass name 2", "description 2"));

        List<RpgClass> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove RpgClass")
    void deleteById_shouldRemoveRpgClass() {
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");
        RpgClass saved = underTest.save(rpgClass);

        underTest.deleteById(saved.getId());

        Optional<RpgClass> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }

    /** ADDs */
    @Test
    @DisplayName("findAllByNameContainingIgnoreCase() whould return matching RpgClass")
    void findAllByNameContainingIgnoreCase_shouldReturnMatchingRpgClass() {
        RpgClass rpgClass1 = new RpgClass("Test Name 1", "Test SubClass 1", "Test Description 1");
        RpgClass rpgClass2 = new RpgClass("Test Name 2", "Test SubClass 2", "Test Description 2");
        RpgClass rpgClass3 = new RpgClass("Name 3", "Test SubClass 3", "Test Description 3");

        underTest.save(rpgClass1);
        underTest.save(rpgClass2);
        underTest.save(rpgClass3);

        List<RpgClass> results = underTest.findAllByNameContainingIgnoreCase("Test");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(rpgClass -> rpgClass.getName().equals("Test Name 1")));
        assertTrue(results.stream().anyMatch(rpgClass -> rpgClass.getName().equals("Test Name 2")));
    }
}
