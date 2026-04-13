package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellSourceTest {

    @Test
    void defaultConstructor_createObject() {
        SpellSource spellSource = new SpellSource();

        assertNotNull(spellSource);
        assertNull(spellSource.getId());
        assertNull(spellSource.getSource());
        assertNull(spellSource.getSpell());
        assertNull(spellSource.getPage());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        Source source = new Source();

        SpellSource spellSource = new SpellSource(spell, source, "page");

        assertNotNull(spellSource);
        assertNull(spellSource.getId());
        assertSame(source, spellSource.getSource());
        assertSame(spell, spellSource.getSpell());
        assertEquals("page", spellSource.getPage());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        Source source = new Source();

        SpellSource spellSource = new SpellSource(spell, source, "page");
        spellSource.setId(1L);
        spellSource.setPage("111");

        String result = spellSource.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("source="));
        assertTrue(result.contains("page=111"));
    }
}
