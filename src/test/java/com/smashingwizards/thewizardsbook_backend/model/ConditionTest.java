package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConditionTest {

    @Test
    void defaultConstructor_createObject() {
        Condition condition = new Condition();

        assertNotNull(condition);
        assertNull(condition.getId());
        assertNull(condition.getName());
        assertNull(condition.getDescription());
    }

    @Test
    void constructor_setNameAndDescription() {
        Condition condition = new Condition("Blinded", "A blinded creature cannot see.");

        assertNotNull(condition);
        assertNull(condition.getId());
        assertEquals("Blinded", condition.getName());
        assertNotNull("A blinded creature cannot see.", condition.getDescription());
    }

    @Test
    void settersAndGetters() {
        Condition condition = new Condition();

        condition.setId(1L);
        condition.setName("Poisoned");
        condition.setDescription("A poisoned creature has disadvantage on attack rolls.");

        assertEquals(1L, condition.getId());
        assertEquals("Poisoned", condition.getName());
        assertEquals("A poisoned creature has disadvantage on attack rolls.", condition.getDescription());
    }

    @Test
    void toString_containsFieldValues() {
        Condition condition = new Condition("Charmed", "A charmed creature cannot attack the charmer.");
        condition.setId(2L);

        String result = condition.toString();
        assertTrue(result.contains("2"));
        assertTrue(result.contains("Charmed"));
        assertTrue(result.contains("A charmed creature cannot attack the charmer."));


    }
}
