package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EffectTest {

    @Test
    void defaultConstructor_createObject() {
        Effect effect = new Effect();

        assertNotNull(effect);
        assertNull(effect.getId());
        assertNull(effect.getName());
        assertNull(effect.getSubEffect());
    }

    @Test
    void constructor_setNameAndSubEffect() {
        Effect effect = new Effect("Fireball", "Explosion");

        assertNotNull(effect);
        assertNull(effect.getId());
        assertEquals("Fireball", effect.getName());
        assertEquals("Explosion", effect.getSubEffect());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Effect effect = new Effect();

        effect.setId(1L);
        effect.setName("Lightning Bolt");
        effect.setSubEffect("Electric Shock");

        assertEquals(1L, effect.getId());
        assertEquals("Lightning Bolt", effect.getName());
        assertEquals("Electric Shock", effect.getSubEffect());
    }

    @Test
    void toString_containsFieldValues() {
        Effect effect = new Effect("Fireball", "Explosion");
        effect.setId(2L);

        String result = effect.toString();

        assertTrue(result.contains("id=2"));
        assertTrue(result.contains("name='Fireball'"));
        assertTrue(result.contains("subEffect='Explosion'"));
    }
}
