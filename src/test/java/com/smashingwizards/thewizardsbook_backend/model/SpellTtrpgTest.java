package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpellTtrpgTest {

    @Test
    void defaultConstructor_createObject() {
        SpellTtrpg spellTtrpg = new SpellTtrpg();

        assertNotNull(spellTtrpg);
        assertNull(spellTtrpg.getId());
        assertNull(spellTtrpg.getSpell());
        assertNull(spellTtrpg.getTtrpg());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        Ttrpg ttrpg = new Ttrpg();

        SpellTtrpg spellTtrpg = new SpellTtrpg(spell, ttrpg);

        assertNotNull(spellTtrpg);
        assertNull(spellTtrpg.getId());
        assertSame(spell, spellTtrpg.getSpell());
        assertSame(ttrpg, spellTtrpg.getTtrpg());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        Ttrpg ttrpg = new Ttrpg();

        SpellTtrpg spellTtrpg = new SpellTtrpg(spell, ttrpg);
        spellTtrpg.setId(1L);

        String result = spellTtrpg.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("ttrpg="));
    }
}
