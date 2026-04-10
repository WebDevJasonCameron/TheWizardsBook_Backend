package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SourceTest {

    @Test
    void defaultConstructor_createObject() {
        Source source = new Source();

        assertNotNull(source);
        assertNull(source.getId());
        assertNull(source.getName());
        assertNull(source.getPublishData());
        assertNull(source.getPublisher());
    }

    @Test
    void constructor_setName_publishDate_publisher_ttrpg(){
        Source source = new Source("The Wizards of the Coast", "2019-01-01", "Wizards of the Coast", 1L);

        assertNotNull(source);
        assertNull(source.getId());
        assertEquals("The Wizards of the Coast", source.getName());
        assertEquals("2019-01-01", source.getPublishData());
        assertEquals("Wizards of the Coast", source.getPublisher());
        assertEquals(1L, source.getTtrpg());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Source source = new Source();

        source.setId(1L);
        source.setName("The Wizards of the Coast");
        source.setPublishData("2019-01-01");
        source.setPublisher("Wizards of the Coast");
        source.setTtrpg(1L);

        assertEquals(1L, source.getId());
        assertEquals("The Wizards of the Coast", source.getName());
        assertEquals("2019-01-01", source.getPublishData());
        assertEquals("Wizards of the Coast", source.getPublisher());
        assertEquals(1L, source.getTtrpg());
    }

    @Test
    void toString_containsFieldValues() {
        Source source = new Source("The Wizards of the Coast", "2019-01-01", "Wizards of the Coast", 1L);
        source.setId(2L);

        String result = source.toString();
        assertTrue(result.contains("2"));
        assertTrue(result.contains("The Wizards of the Coast"));
        assertTrue(result.contains("2019-01-01"));
        assertTrue(result.contains("Wizards of the Coast"));
        assertTrue(result.contains("1"));
    }
}
