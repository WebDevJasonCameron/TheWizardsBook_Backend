package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellRepositoryTest {

    @Autowired
    private SpellRepository underTest;

    @Test
    @DisplayName("Repository loads successfully")
    void testRepositoryLoads() {
        assertNotNull(underTest);
    }

    @Test
    @DisplayName("save() should persist a Spell")
    void save_shouldPersistSpell() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Spell saved = underTest.save(spell);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("name", saved.getName());
        assertEquals("level", saved.getLevel());
        assertEquals("casting time", saved.getCastingTime());
        assertEquals("range", saved.getRangeArea());
        assertFalse(saved.getComponentVisual());
        assertFalse(saved.getComponentSemantic());
        assertFalse(saved.getComponentMaterial());
        assertEquals("", saved.getComponentMaterials());
        assertEquals("duration", saved.getDuration());
        assertFalse(saved.getConcentration());
        assertFalse(saved.getRitual());
        assertEquals("school", saved.getSchool());
        assertEquals("description", saved.getDescription());
        assertEquals(1L, saved.getSourceId());
    }

    @Test
    @DisplayName("findById() should return saved Spell")
    void findById_shouldReturnSavedSpell() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Spell saved = underTest.save(spell);

        Optional<Spell> result = underTest.findById(saved.getId());

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("name", saved.getName());
        assertEquals("level", saved.getLevel());
        assertEquals("casting time", saved.getCastingTime());
        assertEquals("range", saved.getRangeArea());
        assertFalse(saved.getComponentVisual());
        assertFalse(saved.getComponentSemantic());
        assertFalse(saved.getComponentMaterial());
        assertEquals("", saved.getComponentMaterials());
        assertEquals("duration", saved.getDuration());
        assertFalse(saved.getConcentration());
        assertFalse(saved.getRitual());
        assertEquals("school", saved.getSchool());
        assertEquals("description", saved.getDescription());
        assertEquals(1L, saved.getSourceId());
    }

    @Test
    @DisplayName("findAll() should return all saved Spells")
    void findAll_shouldReturnAllSavedSpells() {
        underTest.save(new Spell("name1", "level1", "casting time1", "range1", false, false, false, "", "duration1", false, false, "school1", "description1", 1L));
        underTest.save(new Spell("name2", "level2", "casting time2", "range2", false, false, false, "", "duration2", false, false, "school2", "description2", 2L));

        List<Spell> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove Spell")
    void deleteById_shouldRemoveSpell() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Spell saved = underTest.save(spell);

        underTest.deleteById(saved.getId());

        Optional<Spell> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }
}
