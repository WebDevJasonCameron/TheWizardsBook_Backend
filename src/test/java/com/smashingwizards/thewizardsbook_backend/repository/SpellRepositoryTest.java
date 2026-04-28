package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellMapper;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
public class SpellRepositoryTest {

    @Autowired
    private SpellClassRepository spellClassRepository;
    @Autowired
    private RpgClassRepository rpgClassRepository;
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
        Spell spell = getSpell(null);

        Spell saved = underTest.save(spell);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Test Spell Name", saved.getName());
        assertEquals("Test Level", saved.getLevel());
        assertEquals("Test CastingTime", saved.getCastingTime());
        assertEquals("Test Range", saved.getRangeArea());
        assertFalse(saved.getComponentVisual());
        assertFalse(saved.getComponentSemantic());
        assertFalse(saved.getComponentMaterial());
        assertEquals("Test Materials", saved.getComponentMaterials());
        assertEquals("Test Duration", saved.getDuration());
        assertFalse(saved.getConcentration());
        assertFalse(saved.getRitual());
        assertEquals("Test School", saved.getSchool());
        assertEquals("Test Description", saved.getDescription());
    }

    @Test
    @DisplayName("findById() should return saved Spell")
    void findById_shouldReturnSavedSpell() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
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
    }

    @Test
    @DisplayName("findAll() should return all saved Spells")
    void findAll_shouldReturnAllSavedSpells() {
        underTest.save(new Spell("name1", "level1", "casting time1", "range1", false, false, false, "", "duration1", false, false, "school1", "description1"));
        underTest.save(new Spell("name2", "level2", "casting time2", "range2", false, false, false, "", "duration2", false, false, "school2", "description2"));

        List<Spell> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove Spell")
    void deleteById_shouldRemoveSpell() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Spell saved = underTest.save(spell);

        underTest.deleteById(saved.getId());

        Optional<Spell> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }

    /** ADDs */
    @Test
    @DisplayName("findAllByNameContainingIgnoreCase() should return matching spells")
    void findAllByNameContainingIgnoreCase_shouldReturnMatchingSpells() {
        Spell spell1 = new Spell("Test Name 1", "Test Level 1", "Test CastingTime 1", "Test RangeArea 1", false, false, false, "N/A", "Test Duration 1", false, false, "Test School 1", "Test Description 1");

        Spell spell2 = new Spell("Test Name 2", "Test Level 2", "Test CastingTime 2", "Test RangeArea 2", false, false, false, "N/A", "Test Duration 2", false, false, "Test School 2", "Test Description 2");

        Spell spell3 = new Spell("Name 3", "Test Level 3", "Test CastingTime 3", "Test RangeArea 3", false, false, false, "N/A", "Test Duration 3", false, false, "Test School 3", "Test Description 3");

        underTest.save(spell1);
        underTest.save(spell2);
        underTest.save(spell3);

        List<Spell> results = underTest.findAllByNameContainingIgnoreCase("test name");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(spell -> spell.getName().equals("Test Name 1")));
        assertTrue(results.stream().anyMatch(spell -> spell.getName().equals("Test Name 2")));

    }

    /** SUPs */
    private static @NonNull Spell getSpell(Long num) {
        String numString = (num != null) ? " " + num : "";

        Spell spell = new Spell();

        // Only set ID when you are intentionally testing an existing object.
        if (num != null) {
            spell.setId(num);
        }

        spell.setName("Test Spell Name" + numString);
        spell.setLevel("Test Level");
        spell.setCastingTime("Test CastingTime");
        spell.setRangeArea("Test Range");
        spell.setComponentVisual(false);
        spell.setComponentSemantic(false);
        spell.setComponentMaterial(false);
        spell.setComponentMaterials("Test Materials");
        spell.setDuration("Test Duration");
        spell.setConcentration(false);
        spell.setRitual(false);
        spell.setSchool("Test School");
        spell.setDescription("Test Description");

        return spell;
    }
}
