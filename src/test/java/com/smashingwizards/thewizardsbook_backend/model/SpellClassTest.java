package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpellClassTest {

    @Test
    void defaultConstructor_createObject() {
        SpellClass spellClass = new SpellClass();

        assertNotNull(spellClass);
        assertNull(spellClass.getId());
        assertNull(spellClass.getRpgClass());
        assertNull(spellClass.getSpell());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        RpgClass rpgClass = new RpgClass();

        SpellClass spellClass = new SpellClass(spell, rpgClass);

        assertNotNull(spellClass);
        assertNull(spellClass.getId());
        assertSame(rpgClass, spellClass.getRpgClass());
        assertSame(spell, spellClass.getSpell());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        RpgClass rpgClass = new RpgClass();

        SpellClass spellClass = new SpellClass(spell, rpgClass);
        spellClass.setId(1L);

        String result = spellClass.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("rpgClass="));
    }
}
