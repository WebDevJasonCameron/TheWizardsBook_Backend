package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DamagetypeTest {

    @Test
    void defaultConstructor_createObject() {
        Damagetype damagetype = new Damagetype();

        assertNotNull(damagetype);
        assertNull(damagetype.getId());
        assertNull(damagetype.getName());
    }

    @Test
    void constructor_setName() {
        Damagetype damagetype = new Damagetype("Acid");

        assertNotNull(damagetype);
        assertEquals("Acid", damagetype.getName());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Damagetype damagetype = new Damagetype();

        damagetype.setId(1L);
        damagetype.setName("Acid");

        assertEquals(1L, damagetype.getId());
        assertEquals("Acid", damagetype.getName());
    }

    @Test
    void toString_containsFieldValues() {
        Damagetype damagetype = new Damagetype("Acid");
        damagetype.setId(2L);

        String result = damagetype.toString();
        assertTrue(result.contains("2"));
        assertTrue(result.contains("Acid"));
    }

}
