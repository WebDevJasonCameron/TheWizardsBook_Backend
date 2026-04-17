package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellDamagetypeService;
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

@WebMvcTest(SpellDamagetypeController.class)
public class SpellDamagetypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellDamagetypeService spellDamagetypeService;

    @Test
    void getAllSpellDamagetypes_shouldReturnListOfSpellDamagetypes() throws Exception {
        List<SpellDamagetypeDTO> spellDamagetypesDto = List.of(
                new SpellDamagetypeDTO(1L, 10L, 20L),
                new SpellDamagetypeDTO(2L, 11L, 21L)
        );

        when(spellDamagetypeService.getAllSpellDamagetypes()).thenReturn(spellDamagetypesDto);

        mockMvc.perform(get("/api/spelldamagetypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].spellId").value(10L))
                .andExpect(jsonPath("$[0].damagetypeId").value(20L))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].spellId").value(11L))
                .andExpect(jsonPath("$[1].damagetypeId").value(21L));
    }

    @Test
    void getSpellDamagetypeById_shouldReturnSpellDamagetype() throws Exception {
        SpellDamagetypeDTO spellDamagetypeDto = new SpellDamagetypeDTO(1L, 10L, 20L);

        when(spellDamagetypeService.getSpellDamagetypeById(1L)).thenReturn(spellDamagetypeDto);

        mockMvc.perform(get("/api/spelldamagetypes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.spellId").value(10L))
                .andExpect(jsonPath("$.damagetypeId").value(20L));
    }

    @Test
    void createSpellDamagetype_shouldReturnCreatedSpellDamagetype() throws Exception {
        SpellDamagetypeDTO spellDamagetypeDto = new SpellDamagetypeDTO(1L, 10L, 20L);

        when(spellDamagetypeService.createSpellDamagetype(any(SpellDamagetypeDTO.class))).thenReturn(spellDamagetypeDto);

        mockMvc.perform(post("/api/spelldamagetypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "damagetypeId": 20
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spelldamagetypes/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.damagetypeId").value(20));

        verify(spellDamagetypeService).createSpellDamagetype(any(SpellDamagetypeDTO.class));
    }

    @Test
    void updateSpellDamagetype_shouldReturnUpdatedSpellDamagetype() throws Exception {
        SpellDamagetypeDTO spellDamagetypeDto = new SpellDamagetypeDTO(1L, 10L, 21L);

        when(spellDamagetypeService.updateSpellDamagetype(eq(1L), any(SpellDamagetypeDTO.class))).thenReturn(spellDamagetypeDto);

        mockMvc.perform(put("/api/spelldamagetypes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "spellId": 10,
                                "damagetypeId": 21
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.damagetypeId").value(21));

        verify(spellDamagetypeService).updateSpellDamagetype(eq(1L), any(SpellDamagetypeDTO.class));
    }

    @Test
    void deleteSpellDamagetype_shouldReturnNoContent() throws Exception {
        doNothing().when(spellDamagetypeService).deleteSpellDamagetype(1L);

        mockMvc.perform(delete("/api/spelldamagetypes/1"))
                .andExpect(status().isNoContent());

        verify(spellDamagetypeService).deleteSpellDamagetype(1L);
    }
}
