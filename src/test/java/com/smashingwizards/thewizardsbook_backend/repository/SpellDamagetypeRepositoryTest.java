package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellDamagetype;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellDamagetypeRepositoryTest {

    @Autowired
    private SpellDamagetypeRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public DamagetypeRepository damagetypeRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a SpellDamagetype")
    void save_shouldPersistSpellDamagetype() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Damagetype damagetype = new Damagetype("type name");

        Spell savedSpell = spellRepository.save(spell);
        Damagetype savedDamagetype = damagetypeRepository.save(damagetype);

        SpellDamagetype savedSpellDamagetype = new SpellDamagetype(savedSpell, savedDamagetype);
        savedSpellDamagetype = underTest.save(savedSpellDamagetype);

        assertNotNull(savedSpellDamagetype);
        assertNotNull(savedSpellDamagetype.getId());
        assertNotNull(savedSpellDamagetype.getSpell());
        assertNotNull(savedSpellDamagetype.getDamagetype());
    }

    @Test
    @DisplayName("findBy() should return saved SpellDamagetype")
    void findById_shouldReturnSavedSpell() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Damagetype damagetype = new Damagetype("type name");

        Spell savedSpell = spellRepository.save(spell);
        Damagetype savedDamagetype = damagetypeRepository.save(damagetype);

        SpellDamagetype savedSpellDamagetype = new SpellDamagetype(savedSpell, savedDamagetype);
        savedSpellDamagetype = underTest.save(savedSpellDamagetype);

        Optional<SpellDamagetype> result = underTest.findById(savedSpellDamagetype.getId());
        assertTrue(result.isPresent());
        assertEquals(savedSpell, result.get().getSpell());
        assertEquals(savedDamagetype, result.get().getDamagetype());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Damagetype damagetype = new Damagetype("type name");

        Spell savedSpell_1 = spellRepository.save(spell);
        Damagetype savedDamagetype_1 = damagetypeRepository.save(damagetype);
        Spell savedSpell_2 = spellRepository.save(spell);
        Damagetype savedDamagetype_2 = damagetypeRepository.save(damagetype);

        SpellDamagetype savedSpellDamagetype_1 = new SpellDamagetype(savedSpell_1, savedDamagetype_1);
        SpellDamagetype savedSpellDamagetype_2 = new SpellDamagetype(savedSpell_2, savedDamagetype_2);

        savedSpellDamagetype_1 = underTest.save(savedSpellDamagetype_1);
        savedSpellDamagetype_2 = underTest.save(savedSpellDamagetype_2);

        List<SpellDamagetype> result = underTest.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(savedSpellDamagetype_1));
        assertTrue(result.contains(savedSpellDamagetype_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellDamagetype")
    void deleteById_shouldRemoveSpellDamagetype() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Damagetype damagetype = new Damagetype("type name");

        Spell savedSpell = spellRepository.save(spell);
        Damagetype savedDamagetype = damagetypeRepository.save(damagetype);

        SpellDamagetype savedSpellDamagetype = new SpellDamagetype(savedSpell, savedDamagetype);
        savedSpellDamagetype = underTest.save(savedSpellDamagetype);

        underTest.deleteById(savedSpellDamagetype.getId());

        assertFalse(underTest.findById(savedSpellDamagetype.getId()).isPresent());
    }
}
