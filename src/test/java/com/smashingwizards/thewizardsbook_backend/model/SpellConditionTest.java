package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellConditionTest {

    @Test
    void defaultConstructor_createObject() {
        SpellCondition spellCondition = new SpellCondition();

        assertNotNull(spellCondition);
        assertNull(spellCondition.getId());
        assertNull(spellCondition.getCondition());
        assertNull(spellCondition.getSpell());
    }

    @Test
    void defaultConstructor_withParameters(){
        Spell spell = new Spell();
        Condition condition = new Condition();

        SpellCondition spellCondition = new SpellCondition(spell, condition);

        assertNotNull(spellCondition);
        assertNull(spellCondition.getId());
        assertSame(condition, spellCondition.getCondition());
        assertSame(spell, spellCondition.getSpell());
    }

    @Test
    void toString_containsMainFields() {
        Spell spell = new Spell();
        Condition condition = new Condition();

        SpellCondition spellCondition = new SpellCondition(spell, condition);
        spellCondition.setId(1L);

        String result = spellCondition.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("spell="));
        assertTrue(result.contains("condition="));
    }
}
