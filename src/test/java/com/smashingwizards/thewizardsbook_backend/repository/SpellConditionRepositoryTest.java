package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Condition;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellConditionRepositoryTest {

    @Autowired
    public SpellConditionRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public ConditionRepository conditionRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest);
    }

    @Test
    @DisplayName("save() should persist a SpellCondition")
    void save_shouldPersistSpellCondition() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Condition condition = new Condition("condition name", "condition type");

        Spell savedSpell = spellRepository.save(spell);
        Condition savedCondition = conditionRepository.save(condition);

        SpellCondition savedSpellCondition = new SpellCondition(savedSpell, savedCondition);
        savedSpellCondition = underTest.save(savedSpellCondition);

        assertNotNull(savedSpellCondition);
        assertNotNull(savedSpellCondition.getId());
        assertEquals(spell, savedSpellCondition.getSpell());
        assertEquals(condition, savedSpellCondition.getCondition());
    }

    @Test
    @DisplayName("findById() should return saved SpellCondition")
    void findById_shouldReturnSavedSpellCondition() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Condition condition = new Condition("condition name", "condition type");

        Spell savedSpell = spellRepository.save(spell);
        Condition savedCondition = conditionRepository.save(condition);

        SpellCondition savedSpellCondition = new SpellCondition(savedSpell, savedCondition);
        savedSpellCondition = underTest.save(savedSpellCondition);

        Optional<SpellCondition> result = underTest.findById(savedSpellCondition.getId());

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(spell, result.get().getSpell());
        assertEquals(condition, result.get().getCondition());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Condition condition = new Condition("condition name", "condition type");

        Spell savedSpell_1 = spellRepository.save(spell);
        Condition savedCondition_1 = conditionRepository.save(condition);

        Spell savedSpell_2 = spellRepository.save(spell);
        Condition savedCondition_2 = conditionRepository.save(condition);

        SpellCondition savedSpellCondition_1 = new SpellCondition(savedSpell_1, savedCondition_1);
        SpellCondition savedSpellCondition_2 = new SpellCondition(savedSpell_2, savedCondition_2);

        savedSpellCondition_1 = underTest.save(savedSpellCondition_1);
        savedSpellCondition_2 = underTest.save(savedSpellCondition_2);

        List<SpellCondition> result = underTest.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(savedSpellCondition_1));
        assertTrue(result.contains(savedSpellCondition_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellCondition")
    void deleteById_shouldRemoveSpellCondition() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description");
        Condition condition = new Condition("condition name", "condition type");

        Spell savedSpell = spellRepository.save(spell);
        Condition savedCondition = conditionRepository.save(condition);

        SpellCondition savedSpellCondition = new SpellCondition(savedSpell, savedCondition);
        savedSpellCondition = underTest.save(savedSpellCondition);

        underTest.deleteById(savedSpellCondition.getId());

        Optional<SpellCondition> result = underTest.findById(savedSpellCondition.getId());
        assertFalse(result.isPresent());
    }
}
