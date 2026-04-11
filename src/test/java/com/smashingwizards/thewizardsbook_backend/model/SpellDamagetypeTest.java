package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellDamagetypeTest {

    @Test
    void defaultConstructor_createObject() {
        SpellDamagetype spellDamagetype = new SpellDamagetype();

        assertNotNull(spellDamagetype);
        assertNull(spellDamagetype.getId());
        assertNull(spellDamagetype.getDamagetype());
        assertNull(spellDamagetype.getSpell());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        Damagetype damagetype = new Damagetype();

        SpellDamagetype spellDamagetype = new SpellDamagetype(spell, damagetype);

        assertNotNull(spellDamagetype);
        assertNull(spellDamagetype.getId());
        assertSame(damagetype, spellDamagetype.getDamagetype());
        assertSame(spell, spellDamagetype.getSpell());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        Damagetype damagetype = new Damagetype();

        SpellDamagetype spellDamagetype = new SpellDamagetype(spell, damagetype);
        spellDamagetype.setId(1L);

        String result = spellDamagetype.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("damagetype="));
    }
}
