package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellTagRepositoryTest {

    @Autowired
    public SpellTagRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public TagRepository tagRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a SpellTag")
    void save_shouldPersistSpellTag() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Tag tag = new Tag("tag name", "tag type");

        Spell savedSpell = spellRepository.save(spell);
        Tag savedTag = tagRepository.save(tag);

        SpellTag savedSpellTag = new SpellTag(savedSpell, savedTag);
        savedSpellTag = underTest.save(savedSpellTag);

        assertNotNull(savedSpellTag);
        assertNotNull(savedSpellTag.getId());
        assertEquals(spell, savedSpellTag.getSpell());
        assertEquals(tag, savedSpellTag.getTag());
    }

    @Test
    @DisplayName("findById() should return saved SpellTag")
    void findById_shouldReturnSavedSpellTag() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Tag tag = new Tag("tag name", "tag type");

        Spell savedSpell = spellRepository.save(spell);
        Tag savedTag = tagRepository.save(tag);

        SpellTag savedSpellTag = new SpellTag(savedSpell, savedTag);
        savedSpellTag = underTest.save(savedSpellTag);

        Optional<SpellTag> result = underTest.findById(savedSpellTag.getId());
        assertTrue(result.isPresent());
        assertEquals(tag, result.get().getTag());
    }

    @Test
    @DisplayName("findAll() should return all saved tags")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Tag tag = new Tag("tag name", "tag type");

        Spell savedSpell_1 = spellRepository.save(spell);
        Spell savedSpell_2 = spellRepository.save(spell);

        Tag savedTag_1 = tagRepository.save(tag);
        Tag savedTag_2 = tagRepository.save(tag);

        SpellTag savedSpellTag_1 = new SpellTag(savedSpell_1, savedTag_1);
        SpellTag savedSpellTag_2 = new SpellTag(savedSpell_2, savedTag_2);

        savedSpellTag_1 = underTest.save(savedSpellTag_1);
        savedSpellTag_2 = underTest.save(savedSpellTag_2);

        List<SpellTag> result = underTest.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(savedSpellTag_1));
        assertTrue(result.contains(savedSpellTag_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellTag")
    void deleteById_shouldRemoveSpellTag() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Tag tag = new Tag("tag name", "tag type");

        Spell savedSpell = spellRepository.save(spell);
        Tag savedTag = tagRepository.save(tag);

        SpellTag savedSpellTag = new SpellTag(savedSpell, savedTag);
        savedSpellTag = underTest.save(savedSpellTag);

        underTest.deleteById(savedSpellTag.getId());

        Optional<SpellTag> result = underTest.findById(savedSpellTag.getId());
        assertFalse(result.isPresent());
    }
}
