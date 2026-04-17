package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.ImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.service.ImageUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageUrlController.class)
public class ImageUrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ImageUrlService imageUrlService;

    @Test
    void getAllImageUrls_shouldReturnListOfImageUrls() throws Exception {
        Instant instant = Instant.now();
        List<ImageUrlDTO> imageUrls = List.of(
                new ImageUrlDTO(1L, "TestURL 1", "Test Type 1", "Test Hash 1", instant),
                new ImageUrlDTO(2L, "TestURL 2", "Test Type 2", "Test Hash 2", instant)
        );

        when(imageUrlService.getAllImageUrls()).thenReturn(imageUrls);

        mockMvc.perform(get("/api/imageurls"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].url").value("TestURL 1"))
                .andExpect(jsonPath("$[0].type").value("Test Type 1"))
                .andExpect(jsonPath("$[0].hash").value("Test Hash 1"))
                .andExpect(jsonPath("$[0].createdAt").value(instant.toString()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].url").value("TestURL 2"))
                .andExpect(jsonPath("$[1].type").value("Test Type 2"))
                .andExpect(jsonPath("$[1].hash").value("Test Hash 2"))
                .andExpect(jsonPath("$[1].createdAt").value(instant.toString()));
    }

    @Test
    void getImageUrlById_shouldReturnImageUrl() throws Exception {
        Instant instant = Instant.now();
        ImageUrlDTO imageUrl = new ImageUrlDTO(1L, "TestURL", "Test Type", "Test Hash", instant);

        when(imageUrlService.getImageUrlById(1L)).thenReturn(imageUrl);

        mockMvc.perform(get("/api/imageurls/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.url").value("TestURL"))
                .andExpect(jsonPath("$.type").value("Test Type"))
                .andExpect(jsonPath("$.hash").value("Test Hash"))
                .andExpect(jsonPath("$.createdAt").value(instant.toString()));
    }

    @Test
    void createImageUrl_shouldReturnCreatedImageUrl() throws Exception {
        Instant instant = Instant.now();
        ImageUrlDTO imageUrl = new ImageUrlDTO(1L, "TestURL", "Test Type", "Test Hash", instant);

        when(imageUrlService.createImageUrl(any(ImageUrlDTO.class))).thenReturn(imageUrl);

        mockMvc.perform(post("/api/imageurls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "url": "TestURL",
                                    "type": "Test Type",
                                    "hash": "Test Hash"
                                }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.url").value("TestURL"))
                .andExpect(jsonPath("$.type").value("Test Type"))
                .andExpect(jsonPath("$.hash").value("Test Hash"))
                .andExpect(jsonPath("$.createdAt").value(instant.toString()));
    }

    @Test
    void updateImageUrl_shouldReturnUpdatedImageUrl() throws Exception {
        Instant instant = Instant.now();
        ImageUrlDTO imageUrl = new ImageUrlDTO(1L, "UpdatedURL", "Updated Type", "Updated Hash", instant);

        when(imageUrlService.updateImageUrl(eq(1L), any(ImageUrlDTO.class))).thenReturn(imageUrl);

        mockMvc.perform(put("/api/imageurls/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "url": "UpdatedURL",
                                    "type": "Updated Type",
                                    "hash": "Updated Hash"
                                }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.url").value("UpdatedURL"))
                .andExpect(jsonPath("$.type").value("Updated Type"))
                .andExpect(jsonPath("$.hash").value("Updated Hash"))
                .andExpect(jsonPath("$.createdAt").value(instant.toString()));
    }

    @Test
    void deleteImageUrl_shouldReturnNoContent() throws Exception {
        doNothing().when(imageUrlService).deleteImageUrl(1L);

        mockMvc.perform(delete("/api/imageurls/1"))
                .andExpect(status().isNoContent());
    }
}
