package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellTagService;
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

@WebMvcTest(SpellTagController.class)
public class SpellTagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellTagService spellTagService;

    @Test
    void getAllSpellTags_shouldReturnListOfSpellTags() throws Exception {
        List<SpellTagDTO> spellTags = List.of(
                new SpellTagDTO(1L, 10L, 20L),
                new SpellTagDTO(2L, 11L, 21L)
        );

        when(spellTagService.getAllSpellTags()).thenReturn(spellTags);

        mockMvc.perform(get("/api/spelltags"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].spellId").value(10))
                .andExpect(jsonPath("$[0].tagId").value(20))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].spellId").value(11))
                .andExpect(jsonPath("$[1].tagId").value(21));
    }

    @Test
    void getSpellTagById_shouldReturnSpellTag() throws Exception {
        SpellTagDTO spellTag = new SpellTagDTO(1L, 10L, 20L);

        when(spellTagService.getSpellTagById(1L)).thenReturn(spellTag);

        mockMvc.perform(get("/api/spelltags/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.tagId").value(20));
    }

    @Test
    void createSpellTag_shouldReturnCreatedSpellTag() throws Exception {
        SpellTagDTO spellTagDTO = new SpellTagDTO(1L, 10l, 20l);

        when(spellTagService.createSpellTag(any(SpellTagDTO.class))).thenReturn(spellTagDTO);

        mockMvc.perform(post("/api/spelltags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "tagId": 20
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spelltags/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.tagId").value(20));

        verify(spellTagService).createSpellTag(any(SpellTagDTO.class));
    }

    @Test
    void updateSpellTag_shouldReturnUpdatedSpellTag() throws Exception {
        SpellTagDTO spellTagDTO = new SpellTagDTO(1L, 10l, 20l);

        when(spellTagService.updateSpellTag(eq(1L), any(SpellTagDTO.class)))
                .thenReturn(spellTagDTO);

        mockMvc.perform(put("/api/spelltags/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "tagId": 20
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.tagId").value(20));

        verify(spellTagService).updateSpellTag(eq(1L), any(SpellTagDTO.class));
    }

    @Test
    void deleteSpellTag_shouldReturnNoContent() throws Exception {
        doNothing().when(spellTagService).deleteSpellTag(1L);

        mockMvc.perform(delete("/api/spelltags/1"))
                .andExpect(status().isNoContent());

        verify(spellTagService).deleteSpellTag(1L);
    }
}
