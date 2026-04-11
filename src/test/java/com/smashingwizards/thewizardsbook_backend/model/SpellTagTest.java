package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellTagTest {

    @Test
    void defaultConstructor_createObject() {
        SpellTag spellTag = new SpellTag();

        assertNotNull(spellTag);
        assertNull(spellTag.getId());
        assertNull(spellTag.getTag());
        assertNull(spellTag.getSpell());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        Tag tag = new Tag();

        SpellTag spellTag = new SpellTag(spell, tag);

        assertNotNull(spellTag);
        assertNull(spellTag.getId());
        assertSame(tag, spellTag.getTag());
        assertSame(spell, spellTag.getSpell());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        Tag tag = new Tag();

        SpellTag spellTag = new SpellTag(spell, tag);
        spellTag.setId(1L);

        String result = spellTag.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("tag="));
    }
}
