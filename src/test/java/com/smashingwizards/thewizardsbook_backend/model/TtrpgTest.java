package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TtrpgTest {

    @Test
    void defaultConstructor_createObject() {
        Ttrpg ttrpg = new Ttrpg();

        assertNotNull(ttrpg);
        assertNull(ttrpg.getId());
        assertNull(ttrpg.getName());
        assertNull(ttrpg.getVersion());
    }

    @Test
    void constructor_setsName_version() {
        Ttrpg ttrpg = new Ttrpg("D&D 5e", "5.0");

        assertNotNull(ttrpg);
        assertNull(ttrpg.getId());
        assertEquals("D&D 5e", ttrpg.getName());
        assertEquals("5.0", ttrpg.getVersion());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Ttrpg ttrpg = new Ttrpg();

        ttrpg.setId(1L);
        ttrpg.setName("D&D 5e");
        ttrpg.setVersion("5.0");

        assertEquals(1L, ttrpg.getId());
        assertEquals("D&D 5e", ttrpg.getName());
        assertEquals("5.0", ttrpg.getVersion());
    }

    @Test
    void toString_containsFieldValues() {
        Ttrpg ttrpg = new Ttrpg("D&D 5e", "5.0");

        String toStringResult = ttrpg.toString();

        assertTrue(toStringResult.contains("id=null"));
        assertTrue(toStringResult.contains("D&D 5e"));
        assertTrue(toStringResult.contains("5.0"));
    }
}
