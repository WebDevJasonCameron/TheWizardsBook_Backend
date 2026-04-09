package com.smashingwizards.thewizardsbook_backend.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ImageUrlTest {

    @Test
    void defaultConstructor_createObject() {
        ImageUrl imageUrl = new ImageUrl();

        assertNotNull(imageUrl);
        assertNull(imageUrl.getId());
        assertNull(imageUrl.getUrl());
        assertNull(imageUrl.getType());
        assertNull(imageUrl.getHash());
        assertNull(imageUrl.getCreatedAt());
    }

    @Test
    void constructor_sets_Url_Type_Hash_CreatedAt() {
        Instant createdAt = Instant.now();
        ImageUrl imageUrl = new ImageUrl("example.com/image.jpg", "jpg", "hash123", createdAt);

        assertNotNull(imageUrl);
        assertNull(imageUrl.getId());
        assertEquals("example.com/image.jpg", imageUrl.getUrl());
        assertEquals("jpg", imageUrl.getType());
        assertEquals("hash123", imageUrl.getHash());
        assertEquals(imageUrl.getCreatedAt(), createdAt);
    }

    @Test
    void settersAndGetters_workCorrectly() {
        ImageUrl imageUrl = new ImageUrl();
        Instant createdAt = Instant.now();

        imageUrl.setId(1L);
        imageUrl.setUrl("example.com/image.jpg");
        imageUrl.setType("jpg");
        imageUrl.setHash("hash123");
        imageUrl.setCreatedAt(createdAt);

        assertEquals(1L, imageUrl.getId());
        assertEquals("example.com/image.jpg", imageUrl.getUrl());
        assertEquals("jpg", imageUrl.getType());
        assertEquals("hash123", imageUrl.getHash());
        assertEquals(createdAt, imageUrl.getCreatedAt());
    }

    @Test
    void toString_containsFieldValues() {
        Instant createdAt = Instant.now();
        ImageUrl imageUrl = new ImageUrl("example.com/image.jpg", "jpg", "hash123", createdAt);
        imageUrl.setId(2L);

        String result = imageUrl.toString();

        assertTrue(result.contains("2"));
        assertTrue(result.contains("example.com/image.jpg"));
        assertTrue(result.contains("jpg"));
        assertTrue(result.contains("hash123"));
        assertTrue(result.contains(createdAt.toString()));
    }


}
