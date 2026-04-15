package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellTtrpgRepositoryTest {

    @Autowired
    public SpellTtrpgRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public TtrpgRepository ttrpgRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoadsSuccessfully() {
        assertNotNull(underTest);
    }

    @Test
    @DisplayName("save() should persist a SpellTtrpg")
    void save_shouldPersistSpellTtrpg() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Ttrpg ttrpg = new Ttrpg("Test Ttrpg", "Ttrpg type");

        Spell savedSpell = spellRepository.save(spell);
        Ttrpg savedTtrpg = ttrpgRepository.save(ttrpg);

        SpellTtrpg savedSpellTtrpg = new SpellTtrpg(savedSpell, savedTtrpg);
        savedSpellTtrpg = underTest.save(savedSpellTtrpg);

        Optional<SpellTtrpg> result = underTest.findById(savedSpellTtrpg.getId());
        assertTrue(result.isPresent());
        assertEquals(savedSpell, result.get().getSpell());
        assertEquals(savedTtrpg, result.get().getTtrpg());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Ttrpg ttrpg = new Ttrpg("Test Ttrpg", "Ttrpg type");

        Spell savedSpell_1 = spellRepository.save(spell);
        Ttrpg savedTtrpg_1 = ttrpgRepository.save(ttrpg);

        Spell savedSpell_2 = spellRepository.save(spell);
        Ttrpg savedTtrpg_2 = ttrpgRepository.save(ttrpg);

        SpellTtrpg savedSpellTtrpg_1 = new SpellTtrpg(savedSpell_1, savedTtrpg_1);
        SpellTtrpg savedSpellTtrpg_2 = new SpellTtrpg(savedSpell_2, savedTtrpg_2);

        savedSpellTtrpg_1 = underTest.save(savedSpellTtrpg_1);
        savedSpellTtrpg_2 = underTest.save(savedSpellTtrpg_2);

        List<SpellTtrpg> result = underTest.findAll();
        assertTrue(result.contains(savedSpellTtrpg_1));
        assertTrue(result.contains(savedSpellTtrpg_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellTtrpg")
    void deleteById_shouldRemoveSpellTtrpg() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Ttrpg ttrpg = new Ttrpg("Test Ttrpg", "Ttrpg type");

        Spell savedSpell = spellRepository.save(spell);
        Ttrpg savedTtrpg = ttrpgRepository.save(ttrpg);

        SpellTtrpg savedSpellTtrpg = new SpellTtrpg(savedSpell, savedTtrpg);
        savedSpellTtrpg = underTest.save(savedSpellTtrpg);

        underTest.deleteById(savedSpellTtrpg.getId());

        Optional<SpellTtrpg> result = underTest.findById(savedSpellTtrpg.getId());
        assertFalse(result.isPresent());

    }
}
