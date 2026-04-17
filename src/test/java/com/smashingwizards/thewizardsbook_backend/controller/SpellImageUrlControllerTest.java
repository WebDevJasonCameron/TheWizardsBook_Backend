package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellImageUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(SpellImageUrlController.class)
public class SpellImageUrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellImageUrlService spellImageUrlService;

    @Test
    void getAllSpellImageUrls_shouldReturnListOfSpellImageUrls() throws Exception {
        List<SpellImageUrlDTO> spellImageUrls = List.of(
                new SpellImageUrlDTO(1L, 10L, 20L),
                new SpellImageUrlDTO(2L, 11L, 21L)
        );

        when(spellImageUrlService.getAllSpellImageUrls()).thenReturn(spellImageUrls);

        mockMvc.perform(get("/api/spellimageurls"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].spellId").value(10))
                .andExpect(jsonPath("$[1].spellId").value(11))
                .andExpect(jsonPath("$[0].imageUrlId").value(20))
                .andExpect(jsonPath("$[1].imageUrlId").value(21));
    }

    @Test
    void getSpellImageUrlById_shouldReturnSpellImageUrl() throws Exception {
        SpellImageUrlDTO spellImageUrl = new SpellImageUrlDTO(1L, 10L, 20L);

        when(spellImageUrlService.getSpellImageUrlById(1L)).thenReturn(spellImageUrl);

        mockMvc.perform(get("/api/spellimageurls/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.imageUrlId").value(20));
    }

    @Test
    void createSpellImageUrl_shouldReturnCreatedSpellImageUrl() throws Exception {
        SpellImageUrlDTO spellImageUrl = new SpellImageUrlDTO(1L, 10L, 20L);

        when(spellImageUrlService.createSpellImageUrl(any(SpellImageUrlDTO.class))).thenReturn(spellImageUrl);

        mockMvc.perform(post("/api/spellimageurls")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                            {
                                 "spellId": 10,
                                 "imageUrlId": 20
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spellimageurls/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.imageUrlId").value(20));

        verify(spellImageUrlService).createSpellImageUrl(any(SpellImageUrlDTO.class));
    }

    @Test
    void updateSpellImageUrl_shouldReturnUpdatedSpellImageUrl() throws Exception {
        SpellImageUrlDTO spellImageUrlDto = new SpellImageUrlDTO(1L, 10L, 21L);

        when(spellImageUrlService.updateSpellImageUrl(eq(1L), any(SpellImageUrlDTO.class))).thenReturn(spellImageUrlDto);

        mockMvc.perform(put("/api/spellimageurls/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                            {
                                 "spellId": 10,
                                 "imageUrlId": 21
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.imageUrlId").value(21));

        verify(spellImageUrlService).updateSpellImageUrl(eq(1L), any(SpellImageUrlDTO.class));
    }

    @Test
    void deleteSpellImageUrl_shouldReturnNoContent() throws Exception {
        doNothing().when(spellImageUrlService).deleteSpellImageUrl(1L);

        mockMvc.perform(delete("/api/spellimageurls/1"))
                .andExpect(status().isNoContent());

        verify(spellImageUrlService).deleteSpellImageUrl(1L);
    }


}
