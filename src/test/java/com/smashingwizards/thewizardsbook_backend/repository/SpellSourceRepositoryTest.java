package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellSourceRepositoryTest {

    @Autowired
    public SpellSourceRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public SourceRepository sourceRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a SpellSource")
    void save_shouldPersistSpellSource() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Source source = new Source("source name", "publish date", "source publisher", 1L);

        Spell savedSpell = spellRepository.save(spell);
        Source savedSource = sourceRepository.save(source);

        SpellSource savedSpellSource = new SpellSource(savedSpell, savedSource, "page");
        savedSpellSource = underTest.save(savedSpellSource);

        Optional<SpellSource> result = underTest.findById(savedSpellSource.getId());
        assertTrue(result.isPresent());
        assertEquals(spell, result.get().getSpell());
        assertEquals(source, result.get().getSource());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Source source = new Source("source name", "publish date", "source publisher", 1L);

        Spell savedSpell_1 = spellRepository.save(spell);
        Spell savedSpell_2 = spellRepository.save(spell);

        Source savedSource_1 = sourceRepository.save(source);
        Source savedSource_2 = sourceRepository.save(source);

        SpellSource savedSpellSource_1 = new SpellSource(savedSpell_1, savedSource_1, "page 1");
        SpellSource savedSpellSource_2 = new SpellSource(savedSpell_2, savedSource_2, "page 2");

        savedSpellSource_1 = underTest.save(savedSpellSource_1);
        savedSpellSource_2 = underTest.save(savedSpellSource_2);

        List<SpellSource> result = underTest.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(savedSpellSource_1));
        assertTrue(result.contains(savedSpellSource_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellSource")
    void deleteById_shouldRemoveSpellSource() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Source source = new Source("source name", "publish date", "source publisher", 1L);

        Spell savedSpell = spellRepository.save(spell);
        Source savedSource = sourceRepository.save(source);

        SpellSource savedSpellSource = new SpellSource(savedSpell, savedSource, "page");
        savedSpellSource = underTest.save(savedSpellSource);

        underTest.deleteById(savedSpellSource.getId());

        Optional<SpellSource> result = underTest.findById(savedSpellSource.getId());
        assertFalse(result.isPresent());
    }
}
