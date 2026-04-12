package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellClassRepositoryTest {

    @Autowired
    public SpellClassRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public RpgClassRepository rpgClassRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a SpellClass")
    void save_shouldPersistSpellClass() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");

        Spell savedSpell = spellRepository.save(spell);
        RpgClass savedRpgClass = rpgClassRepository.save(rpgClass);

        SpellClass savedSpellClass = new SpellClass(savedSpell, savedRpgClass);
        savedSpellClass = underTest.save(savedSpellClass);

        assertNotNull(savedSpellClass);
        assertNotNull(savedSpellClass.getId());
        assertEquals(spell, savedSpellClass.getSpell());
        assertEquals(rpgClass, savedSpellClass.getRpgClass());
    }

    @Test
    @DisplayName("findById() should return saved SpellClass")
    void findById_shouldReturnSavedSpellClass() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");

        Spell savedSpell = spellRepository.save(spell);
        RpgClass savedRpgClass = rpgClassRepository.save(rpgClass);

        SpellClass savedSpellClass = new SpellClass(savedSpell, savedRpgClass);
        savedSpellClass = underTest.save(savedSpellClass);

        Optional<SpellClass> result = underTest.findById(savedSpellClass.getId());
        assertTrue(result.isPresent());
        assertEquals(spell, result.get().getSpell());
        assertEquals(rpgClass, result.get().getRpgClass());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");

        Spell savedSpell_1 = spellRepository.save(spell);
        RpgClass savedRpgClass_1 = rpgClassRepository.save(rpgClass);
        Spell savedSpell_2 = spellRepository.save(spell);
        RpgClass savedRpgClass_2 = rpgClassRepository.save(rpgClass);

        SpellClass savedSpellClass_1 = new SpellClass(savedSpell_1, savedRpgClass_1);
        SpellClass savedSpellClass_2 = new SpellClass(savedSpell_2, savedRpgClass_2);

        savedSpellClass_1 = underTest.save(savedSpellClass_1);
        savedSpellClass_2 = underTest.save(savedSpellClass_2);

        List<SpellClass> result = underTest.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(savedSpellClass_1));
        assertTrue(result.contains(savedSpellClass_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellClass")
    void deleteById_shouldRemoveSpellClass() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        RpgClass rpgClass = new RpgClass("class name", "subclass name", "description");

        Spell savedSpell = spellRepository.save(spell);
        RpgClass savedRpgClass = rpgClassRepository.save(rpgClass);

        SpellClass savedSpellClass = new SpellClass(savedSpell, savedRpgClass);
        savedSpellClass = underTest.save(savedSpellClass);

        underTest.deleteById(savedSpellClass.getId());

        Optional<SpellClass> result = underTest.findById(savedSpellClass.getId());
        assertFalse(result.isPresent());
    }
}
