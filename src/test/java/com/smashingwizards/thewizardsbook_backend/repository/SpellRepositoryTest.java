package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellMapper;
import com.smashingwizards.thewizardsbook_backend.model.*;
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
    private SpellSourceRepository spellSourceRepository;
    @Autowired
    private SpellTagRepository spellTagRepository;
    @Autowired
    private SpellTtrpgRepository spellTtrpgRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TtrpgRepository ttrpgRepository;
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
        Spell spell = getSpell();
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
    @DisplayName("findAll() should return all saved Spells")
    void findAll_shouldReturnAllSavedSpells() {
        underTest.save(getSpell(1L));
        underTest.save(getSpell(2L));
        List<Spell> results = underTest.findAll();

        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("deleteById() should remove Spell")
    void deleteById_shouldRemoveSpell() {
        Spell spell = getSpell();
        Spell saved = underTest.save(spell);

        underTest.deleteById(saved.getId());

        Optional<Spell> result = underTest.findById(saved.getId());
        assertFalse(result.isPresent());
    }

    /** ADDs */
    @Test
    @DisplayName("findAllByNameContainingIgnoreCase() should return matching spells")
    void findAllByNameContainingIgnoreCase_shouldReturnMatchingSpells() {
        Spell spell1 = getSpell(1L);
        Spell spell2 = getSpell(2L);
        Spell spell3 = new Spell("Name 3", "Test Level", "Test CastingTime", "Test Range", false, false, false, "Test Materials", "Test Duration", false, false, "Test School", "Test Description");

        underTest.save(spell1);
        underTest.save(spell2);
        underTest.save(spell3);

        List<Spell> results = underTest.findAllByNameContainingIgnoreCase("test spell name");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(spell -> spell.getName().equals("Test Spell Name 1")));
        assertTrue(results.stream().anyMatch(spell -> spell.getName().equals("Test Spell Name 2")));
    }

    @Test
    @DisplayName("findAllByRpgClass_NameContainingIgnoreCase() should return spells matching rpgClass name")
    void findAllByRpgClassNameContainingIgnoreCase_shouldReturnMatchingRpgClassName() {
        Spell spell1 = underTest.save(getSpell(1L));
        Spell spell2 = underTest.save(getSpell(2L));
        Spell spell3 = underTest.save(getSpell());

        RpgClass rpgClass1 = rpgClassRepository.save(
                new RpgClass("Test RpgClass 1", "Test SubClass 1", "Test Description 1")
        );

        RpgClass rpgClass2 = rpgClassRepository.save(
                new RpgClass("Other RpgClass", "Test SubClass 2", "Test Description 2")
        );

        spellClassRepository.save(new SpellClass(spell1, rpgClass1));
        spellClassRepository.save(new SpellClass(spell2, rpgClass1));
        spellClassRepository.save(new SpellClass(spell3, rpgClass2));

        List<SpellClass> results =
                spellClassRepository.findAllByRpgClass_NameContainingIgnoreCase("Test RpgClass 1");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 1")));
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 2")));
    }

    @Test
    @DisplayName("findAllBySource_NameContainingIgnoreCase() should return spell classes matching source name")
    void findAllBySourceNameContainingIgnoreCase_shouldReturnMatchingSourceName() {
        Spell spell1 = underTest.save(getSpell(1L));
        Spell spell2 = underTest.save(getSpell(2L));
        Spell spell3 = underTest.save(getSpell());

        Source source1 = sourceRepository.save(
                new Source("Test Source 1", "Test SubClass 1", "Test Description 1")
        );

        Source source2 = sourceRepository.save(
                new Source("Other Source", "Test SubClass 2", "Test Description 2")
        );

        spellSourceRepository.save(new SpellSource(spell1, source1, "pg 1"));
        spellSourceRepository.save(new SpellSource(spell2, source1, "pg 2"));
        spellSourceRepository.save(new SpellSource(spell3, source2, "pg 3"));

        List<SpellSource> results =
                spellSourceRepository.findAllBySource_NameContainingIgnoreCase("Test Source 1");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 1")));
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 2")));
    }

    @Test
    @DisplayName("findAllByTag_NameContainingIgnoreCase() should return spell classes matching tag name")
    void findAllByTagTagNameContainingIgnoreCase_shouldReturnMatchingTagName() {
        Spell spell1 = underTest.save(getSpell(1L));
        Spell spell2 = underTest.save(getSpell(2L));
        Spell spell3 = underTest.save(getSpell());

        Tag tag1 = tagRepository.save(
                new Tag("Test Tag 1", "Test Description 1")
        );

        Tag tag2 = tagRepository.save(
                new Tag("Test Tag 2", "Test Description 2")
        );

        spellTagRepository.save(new SpellTag(spell1, tag1));
        spellTagRepository.save(new SpellTag(spell2, tag1));
        spellTagRepository.save(new SpellTag(spell3, tag2));

        List<SpellTag> results =
                spellTagRepository.findAllByTag_NameContainingIgnoreCase("Test Tag 1");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 1")));
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 2")));
    }

    @Test
    @DisplayName("findAllByTtrpg_NameContainingIgnoreCase() should return spell classes matching ttrpg name")
    void findAllByTagTtrpgNameContainingIgnoreCase_shouldReturnMatchingTtrpgName() {
        Spell spell1 = underTest.save(getSpell(1L));
        Spell spell2 = underTest.save(getSpell(2L));
        Spell spell3 = underTest.save(getSpell());

        Ttrpg ttrpg1 = ttrpgRepository.save(
                new Ttrpg("Test Ttrpg 1", "Test Description")
        );

        Ttrpg ttrpg2 = ttrpgRepository.save(
                new Ttrpg("Test Ttrpg 2", "Test Description")
        );

        spellTtrpgRepository.save(new SpellTtrpg(spell1, ttrpg1));
        spellTtrpgRepository.save(new SpellTtrpg(spell2, ttrpg1));
        spellTtrpgRepository.save(new SpellTtrpg(spell3, ttrpg2));

        List<SpellTtrpg> results =
                spellTtrpgRepository.findAllByTtrpg_NameContainingIgnoreCase("Test Ttrpg 1");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 1")));
        assertTrue(results.stream().anyMatch(sc -> sc.getSpell().getName().equals("Test Spell Name 2")));
    }

    /** SUPs */
    private static @NonNull Spell getSpell(Long num) {
        String numString = (num != null) ? " " + num : "";
        Spell spell = new Spell();

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

    private static @NonNull Spell getSpell() {
        Spell spell = new Spell();

        spell.setName("Test Spell Name");
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
