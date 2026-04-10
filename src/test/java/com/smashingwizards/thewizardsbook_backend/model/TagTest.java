package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TagTest {
    @Test
    void defaultConstructor_createObject() {
        Tag tag = new Tag();

        assertNotNull(tag);
        assertNull(tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getType());
    }

    @Test
    void constructor_setsName_type() {
        Tag tag = new Tag("Fighter", "RpgClass");

        assertNotNull(tag);
        assertNull(tag.getId());
        assertEquals("Fighter", tag.getName());
        assertEquals("RpgClass", tag.getType());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Tag tag = new Tag();

        tag.setId(1L);
        tag.setName("Fighter");
        tag.setType("RpgClass");

        assertEquals(1L, tag.getId());
        assertEquals("Fighter", tag.getName());
        assertEquals("RpgClass", tag.getType());
    }

    @Test
    void tostring_containsFieldValues() {
        Tag tag = new Tag("Fighter", "RpgClass");
        tag.setId(2L);

        String result = tag.toString();
        assertTrue(result.contains("2"));
        assertTrue(result.contains("Fighter"));
        assertTrue(result.contains("RpgClass"));
        assertTrue(result.contains("Tag{id=2, name='Fighter', type='RpgClass'}"));
    }
}
