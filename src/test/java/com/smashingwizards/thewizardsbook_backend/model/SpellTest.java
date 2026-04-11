package com.smashingwizards.thewizardsbook_backend.model;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpellTest {

    @Test
    void defaultConstructor_createObject() {
        Spell spell = new Spell();

        assertNotNull(spell);
        assertNull(spell.getId());
        assertNull(spell.getName());
        assertNull(spell.getLevel());
        assertNull(spell.getCastingTime());
        assertNull(spell.getRangeArea());
        assertNull(spell.getComponentVisual());
        assertNull(spell.getComponentSemantic());
        assertNull(spell.getComponentMaterial());
        assertNull(spell.getComponentMaterials());
        assertNull(spell.getDuration());
        assertNull(spell.getConcentration());
        assertNull(spell.getRitual());
        assertNull(spell.getSchool());
        assertNull(spell.getDescription());
        assertNull(spell.getSourceId());
    }

    @Test
    void constructor_setsAllFields() {
        Spell spell = new Spell(
                "Fireball",
                1,
                "1 action",
                "self",
                true,
                true,
                true,
                "anything",
                "1 minute",
                false,
                false,
                "school",
                "description",
                5L);

        assertNull(spell.getId());
        assertEquals("Fireball", spell.getName());
        assertEquals(1, spell.getLevel());
        assertEquals("1 action", spell.getCastingTime());
        assertEquals("self", spell.getRangeArea());
        assertTrue(spell.getComponentVisual());
        assertTrue(spell.getComponentSemantic());
        assertTrue(spell.getComponentMaterial());
        assertEquals("anything", spell.getComponentMaterials());
        assertEquals("1 minute", spell.getDuration());
        assertFalse(spell.getConcentration());
        assertFalse(spell.getRitual());
        assertEquals("school", spell.getSchool());
        assertEquals("description", spell.getDescription());
        assertEquals(5L, spell.getSourceId());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Spell spell = getSpell();

        assertEquals(1L, spell.getId());
        assertEquals("Fireball", spell.getName());
        assertEquals(1, spell.getLevel());
        assertEquals("1 action", spell.getCastingTime());
        assertEquals("60 feet", spell.getRangeArea());
        assertTrue(spell.getComponentVisual());
        assertTrue(spell.getComponentSemantic());
        assertTrue(spell.getComponentMaterial());
        assertEquals("flint", spell.getComponentMaterials());
        assertEquals("1 minute", spell.getDuration());
        assertTrue(spell.getConcentration());
        assertTrue(spell.getRitual());
        assertEquals("Evocation", spell.getSchool());
        assertEquals("A fireball streaks toward a creature within range.", spell.getDescription());
        assertEquals(1L, spell.getSourceId());
    }

    private static @NonNull Spell getSpell() {
        Spell spell = new Spell();

        spell.setId(1L);
        spell.setName("Fireball");
        spell.setLevel(1);
        spell.setCastingTime("1 action");
        spell.setRangeArea("60 feet");
        spell.setComponentVisual(true);
        spell.setComponentSemantic(true);
        spell.setComponentMaterial(true);
        spell.setComponentMaterials("flint");
        spell.setDuration("1 minute");
        spell.setConcentration(true);
        spell.setRitual(true);
        spell.setSchool("Evocation");
        spell.setDescription("A fireball streaks toward a creature within range.");
        spell.setSourceId(1L);
        return spell;
    }

    @Test
    void toString_containsFieldValues(){
        Spell spell = new Spell(
                "Fireball",
                1,
                "1 action",
                "self",
                true,
                true,
                true,
                "anything",
                "1 minute",
                false,
                false,
                "school",
                "description",
                5L);
        spell.setId(2L);

        String result = spell.toString();

        assertTrue(result.contains("2"));
        assertTrue(result.contains("Fireball"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("1 action"));
        assertTrue(result.contains("self"));
        assertTrue(result.contains("true"));
        assertTrue(result.contains("true"));
        assertTrue(result.contains("true"));
        assertTrue(result.contains("anything"));
        assertTrue(result.contains("1 minute"));
        assertTrue(result.contains("false"));
        assertTrue(result.contains("school"));
        assertTrue(result.contains("description"));
        assertTrue(result.contains("5"));
    }
}
