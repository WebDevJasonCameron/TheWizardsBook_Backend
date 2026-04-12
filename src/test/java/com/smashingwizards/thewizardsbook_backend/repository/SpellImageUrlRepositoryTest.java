package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellImageUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellImageUrlRepositoryTest {

    @Autowired
    private SpellImageUrlRepository underTest;
    @Autowired
    private SpellRepository spellRepository;
    @Autowired
    private ImageUrlRepository imageUrlRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a SpellImageUrl")
    void save_shouldPersistSpellImageUrl() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);

        Spell savedSpell = spellRepository.save(spell);
        ImageUrl savedImageUrl = imageUrlRepository.save(imageUrl);

        SpellImageUrl savedSpellImageUrl = new SpellImageUrl(savedSpell, savedImageUrl);
        savedSpellImageUrl = underTest.save(savedSpellImageUrl);

        assertNotNull(savedSpellImageUrl);
        assertNotNull(savedSpellImageUrl.getId());
        assertEquals(spell, savedSpellImageUrl.getSpell());
        assertEquals(imageUrl, savedSpellImageUrl.getImageUrl());
    }

    @Test
    @DisplayName("findById() should return saved SpellImageUrl")
    void findById_shouldReturnSavedSpellImageUrl(){
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);

        Spell savedSpell = spellRepository.save(spell);
        ImageUrl savedImageUrl = imageUrlRepository.save(imageUrl);

        SpellImageUrl savedSpellImageUrl = new SpellImageUrl(savedSpell, savedImageUrl);
        savedSpellImageUrl = underTest.save(savedSpellImageUrl);

        Optional<SpellImageUrl> result = underTest.findById(savedSpellImageUrl.getId());
        assertTrue(result.isPresent());
        assertEquals(spell, result.get().getSpell());
        assertEquals(imageUrl, result.get().getImageUrl());
    }

    @Test
    @DisplayName("findAll() should return all saved types")
    void findAll_shouldReturnAllSavedTypes() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);

        Spell savedSpell_1 = spellRepository.save(spell);
        ImageUrl savedImageUrl_1 = imageUrlRepository.save(imageUrl);
        Spell savedSpell_2 = spellRepository.save(spell);
        ImageUrl savedImageUrl_2 = imageUrlRepository.save(imageUrl);

        SpellImageUrl savedSpellImageUrl_1 = new SpellImageUrl(savedSpell_1, savedImageUrl_1);
        SpellImageUrl savedSpellImageUrl_2 = new SpellImageUrl(savedSpell_2, savedImageUrl_2);

        savedSpellImageUrl_1 = underTest.save(savedSpellImageUrl_1);
        savedSpellImageUrl_2 = underTest.save(savedSpellImageUrl_2);

        List<SpellImageUrl> result = underTest.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(savedSpellImageUrl_1));
        assertTrue(result.contains(savedSpellImageUrl_2));
    }

    @Test
    @DisplayName("deleteById() should remove SpellImageUrl")
    void deleteById_shouldRemoveSpellImageUrl() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        ImageUrl imageUrl = new ImageUrl("url", "type", "1234hash", null);

        Spell savedSpell = spellRepository.save(spell);
        ImageUrl savedImageUrl = imageUrlRepository.save(imageUrl);

        underTest.save(new SpellImageUrl(savedSpell, savedImageUrl));

        Optional<SpellImageUrl> result = underTest.findById(savedSpell.getId());
        assertFalse(result.isPresent());
    }
}
