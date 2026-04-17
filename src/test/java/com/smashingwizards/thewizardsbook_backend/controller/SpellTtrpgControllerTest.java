package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellTtrpgService;
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

@WebMvcTest(SpellTtrpgController.class)
public class SpellTtrpgControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellTtrpgService spellTtrpgService;

    @Test
    void getAllSpellTtrpgs_shouldReturnListOfSpellTtrpgs() throws Exception {
        List<SpellTtrpgDTO> spellTtrpgs = List.of(
                new SpellTtrpgDTO(1L, 10L, 20L),
                new SpellTtrpgDTO(2L, 11L, 21L)
        );

        when(spellTtrpgService.getAllSpellTtrpgs()).thenReturn(spellTtrpgs);

        mockMvc.perform(get("/api/spellttrpgs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].spellId").value(10))
                .andExpect(jsonPath("$[0].ttrpgId").value(20))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].spellId").value(11))
                .andExpect(jsonPath("$[1].ttrpgId").value(21));
    }

    @Test
    void getSpellTtrpgById_shouldReturnSpellTtrpg() throws Exception {
        SpellTtrpgDTO spellTtrpg = new SpellTtrpgDTO(1L, 10L, 20L);

        when(spellTtrpgService.getSpellTtrpgById(1L)).thenReturn(spellTtrpg);

        mockMvc.perform(get("/api/spellttrpgs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.ttrpgId").value(20));
    }

    @Test
    void createSpellTtrpg_shouldReturnCreatedSpellTtrpg() throws Exception {
        SpellTtrpgDTO spellTtrpg = new SpellTtrpgDTO(1L, 10L, 20L);

        when(spellTtrpgService.createSpellTtrpg(any(SpellTtrpgDTO.class))).thenReturn(spellTtrpg);

        mockMvc.perform(post("/api/spellttrpgs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "ttrpgId": 20
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spellttrpgs/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.ttrpgId").value(20));

        verify(spellTtrpgService).createSpellTtrpg(any(SpellTtrpgDTO.class));
    }

    @Test
    void updateSpellTtrpg_shouldReturnUpdatedSpellTtrpg() throws Exception {
        SpellTtrpgDTO spellTtrpg = new SpellTtrpgDTO(1L, 10L, 21L);

        when(spellTtrpgService.updateSpellTtrpg(eq(1L), any(SpellTtrpgDTO.class))).thenReturn(spellTtrpg);

        mockMvc.perform(put("/api/spellttrpgs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "ttrpgId": 21
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.ttrpgId").value(21));

        verify(spellTtrpgService).updateSpellTtrpg(eq(1L), any(SpellTtrpgDTO.class));
    }

    @Test
    void deleteSpellTtrpg_shouldReturnNoContent() throws Exception {
        doNothing().when(spellTtrpgService).deleteSpellTtrpg(1L);

        mockMvc.perform(delete("/api/spellttrpgs/1"))
                .andExpect(status().isNoContent());

        verify(spellTtrpgService).deleteSpellTtrpg(1L);
    }
}
