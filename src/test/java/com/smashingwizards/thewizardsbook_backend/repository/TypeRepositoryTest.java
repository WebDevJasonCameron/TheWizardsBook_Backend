package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TypeRepositoryTest {

    @Autowired
    private TypeRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a type")
    void save_shouldPersistType() {
        Type type = new Type("type name", "sub type");
        Type saved = underTest.save(type);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("type name", saved.getName());
        assertEquals("sub type", saved.getSubType());
    }

    @Test
    @DisplayName("findById() should return saved Type")
    void findById_shouldReturnSavedType() {
        Type type = new Type("type name", "sub type");
        Type saved = underTest.save(type);

        Optional<Type> result = underTest.findById(saved.getId());
        assertTrue(result.isPresent());
        assertEquals("type name", result.get().getName());
        assertEquals("sub type", result.get().getSubType());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        underTest.save(new Type("type1", "sub type1"));
        underTest.save(new Type("type2", "sub type2"));

        List<Type> result = underTest.findAll();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("deleteById() should remove type")
    void deleteById_shouldRemoveType() {
        Type type = new Type("type name", "sub type");
        Type saved = underTest.save(type);

        underTest.deleteById(saved.getId());

        Optional<Type> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
