package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellImageUrlTest {

    @Test
    void defaultConstructor_createObject() {
        SpellImageUrl spellImageUrl = new SpellImageUrl();

        assertNotNull(spellImageUrl);
        assertNull(spellImageUrl.getId());
        assertNull(spellImageUrl.getImageUrl());
        assertNull(spellImageUrl.getSpell());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        ImageUrl imageUrl = new ImageUrl();

        SpellImageUrl spellImageUrl = new SpellImageUrl(spell, imageUrl);

        assertNotNull(spellImageUrl);
        assertNull(spellImageUrl.getId());
        assertSame(imageUrl, spellImageUrl.getImageUrl());
        assertSame(spell, spellImageUrl.getSpell());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        ImageUrl imageUrl = new ImageUrl();

        SpellImageUrl spellImageUrl = new SpellImageUrl(spell, imageUrl);
        spellImageUrl.setId(1L);

        String result = spellImageUrl.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("imageUrl="));
    }
}
