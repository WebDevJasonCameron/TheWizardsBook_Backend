package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RpgClassTest {

    @Test
    void defaultConstructor_createObject() {
        RpgClass rpgClass = new RpgClass();

        assertNotNull(rpgClass);
        assertNull(rpgClass.getId());
        assertNull(rpgClass.getName());
        assertNull(rpgClass.getSubClassName());
        assertNull(rpgClass.getDescription());
    }

    @Test
    void constructor_sets_Name_SubClassName_Description() {
        RpgClass rpgClass = new RpgClass("Fighter", "Boxer", "Fighter is a humanoid creature with a light body and powerful gloves.");

        assertNotNull(rpgClass);
        assertNull(rpgClass.getId());
        assertEquals("Fighter", rpgClass.getName());
        assertEquals("Boxer", rpgClass.getSubClassName());
        assertEquals("Fighter is a humanoid creature with a light body and powerful gloves.", rpgClass.getDescription());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        RpgClass rpgClass = new RpgClass();

        rpgClass.setId(1L);
        rpgClass.setName("Fighter");
        rpgClass.setSubClassName("Boxer");
        rpgClass.setDescription("Fighter is a humanoid creature with a light body and powerful gloves.");

        assertEquals(1L, rpgClass.getId());
        assertEquals("Fighter", rpgClass.getName());
        assertEquals("Boxer", rpgClass.getSubClassName());
        assertEquals("Fighter is a humanoid creature with a light body and powerful gloves.", rpgClass.getDescription());
    }

    @Test
    void toString_containsFieldValues() {
        RpgClass rpgClass = new RpgClass("Fighter", "Boxer", "Fighter is a humanoid creature with a light body and powerful gloves.");
        rpgClass.setId(2L);

        String result = rpgClass.toString();

        assertTrue(result.contains("2"));
        assertTrue(result.contains("Fighter"));
        assertTrue(result.contains("Boxer"));
        assertTrue(result.contains("Fighter is a humanoid creature with a light body and powerful gloves."));
    }

}
