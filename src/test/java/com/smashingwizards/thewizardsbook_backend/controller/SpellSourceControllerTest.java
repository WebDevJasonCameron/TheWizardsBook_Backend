package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellSourceDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellSourceService;
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

@WebMvcTest(SpellSourceController.class)
public class SpellSourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellSourceService spellSourceService;

    @Test
    void getAllSpellSources_shouldReturnListOfSpellSources() throws Exception {
        List<SpellSourceDTO> spellSources = List.of(
                new SpellSourceDTO(1L, 10L, 20L, "page 01"),
                new SpellSourceDTO(2L, 11L, 21L, "page 02")
        );

        when(spellSourceService.getAllSpellSources()).thenReturn(spellSources);

        mockMvc.perform(get("/api/spellsources"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].spellId").value(10))
                .andExpect(jsonPath("$[0].sourceId").value(20))
                .andExpect(jsonPath("$[0].page").value("page 01"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].spellId").value(11))
                .andExpect(jsonPath("$[1].sourceId").value(21))
                .andExpect(jsonPath("$[1].page").value("page 02"));
    }

    @Test
    void getSpellSourceById_shouldReturnSpellSource() throws Exception {
        SpellSourceDTO spellSource = new SpellSourceDTO(1L, 10L, 20L, "page 01");

        when(spellSourceService.getSpellSourceById(1L)).thenReturn(spellSource);

        mockMvc.perform(get("/api/spellsources/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.sourceId").value(20))
                .andExpect(jsonPath("$.page").value("page 01"));
    }

    @Test
    void createSpellSource_shouldReturnCreatedSpellSource() throws Exception {
        SpellSourceDTO spellSource = new SpellSourceDTO(1L, 10L, 20L, "page 01");

        when(spellSourceService.createSpellSource(any(SpellSourceDTO.class))).thenReturn(spellSource);

        mockMvc.perform(post("/api/spellsources")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "spellId": 10,
                                "sourceId": 20,
                                "page": "page 01"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spellsources/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.sourceId").value(20))
                .andExpect(jsonPath("$.page").value("page 01"));

        verify(spellSourceService).createSpellSource(any(SpellSourceDTO.class));
    }

    @Test
    void updateSpellSource_shouldReturnUpdatedSpellSource() throws Exception {
        SpellSourceDTO spellSource = new SpellSourceDTO(1L, 10L, 20L, "page 01");

        when(spellSourceService.updateSpellSource(eq(1L), any(SpellSourceDTO.class))).thenReturn(spellSource);

        mockMvc.perform(put("/api/spellsources/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "spellId": 10,
                                "sourceId": 20,
                                "page": "page 01"
                            }
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.sourceId").value(20))
                .andExpect(jsonPath("$.page").value("page 01"));

        verify(spellSourceService).updateSpellSource(eq(1L), any(SpellSourceDTO.class));
    }

    @Test
    void deleteSpellSource_shouldReturnNoContent() throws Exception {
        doNothing().when(spellSourceService).deleteSpellSource(1L);

        mockMvc.perform(delete("/api/spellsources/1"))
                .andExpect(status().isNoContent());

        verify(spellSourceService).deleteSpellSource(1L);
    }
}
