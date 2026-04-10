package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeTest {

    @Test
    void defaultConstructor_createObject() {
        Type type = new Type();

        assertNotNull(type);
        assertNull(type.getId());
        assertNull(type.getName());
        assertNull(type.getSubType());
    }

    @Test
    void constructor_withParameters_createObject() {
        Type type = new Type("Fire", "Elemental");

        assertNotNull(type);
        assertNull(type.getId());
        assertEquals("Fire", type.getName());
        assertEquals("Elemental", type.getSubType());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Type type = new Type();

        type.setId(1L);
        type.setName("Fire");
        type.setSubType("Elemental");

        assertEquals(1L, type.getId());
        assertEquals("Fire", type.getName());
        assertEquals("Elemental", type.getSubType());
    }

    @Test
    void toString_containsFieldValues() {
        Type type = new Type("Fire", "Elemental");
        type.setId(2L);

        String result = type.toString();

        assertTrue(result.contains("2"));
        assertTrue(result.contains("Fire"));
        assertTrue(result.contains("Elemental"));
    }
}
