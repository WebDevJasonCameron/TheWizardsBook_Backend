package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellConditionService;
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

@WebMvcTest(SpellConditionController.class)
class SpellConditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellConditionService spellConditionService;

    @Test
    void getSpellConditions_shouldReturnListOfSpellConditions() throws Exception {
        List<SpellConditionDTO> spellConditionsDto = List.of(
                new SpellConditionDTO(1L, 10L, 20L),
                new SpellConditionDTO(2L, 11L, 21L)
        );

        when(spellConditionService.getAllSpellConditions()).thenReturn(spellConditionsDto);

        mockMvc.perform(get("/api/spellconditions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].spellId").value(10))
                .andExpect(jsonPath("$[0].conditionId").value(20))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].spellId").value(11))
                .andExpect(jsonPath("$[1].conditionId").value(21));
    }

    @Test
    void getSpellConditionById_shouldReturnSpellCondition() throws Exception {
        SpellConditionDTO spellConditionDto = new SpellConditionDTO(1L, 10L, 20L);

        when(spellConditionService.getSpellConditionById(1L)).thenReturn(spellConditionDto);

        mockMvc.perform(get("/api/spellconditions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.conditionId").value(20));
    }

    @Test
    void createSpellCondition_shouldReturnCreatedSpellCondition() throws Exception {
        SpellConditionDTO spellConditionDto = new SpellConditionDTO(1L, 10L, 20L);

        when(spellConditionService.createSpellCondition(any(SpellConditionDTO.class))).thenReturn(spellConditionDto);

        mockMvc.perform(post("/api/spellconditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "conditionId": 20
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spellconditions/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.conditionId").value(20));

        verify(spellConditionService).createSpellCondition(any(SpellConditionDTO.class));
    }

    @Test
    void updateSpellCondition_shouldReturnUpdatedSpellCondition() throws Exception {
        SpellConditionDTO spellConditionDto = new SpellConditionDTO(1L, 10L, 21L);

        when(spellConditionService.updateSpellCondition(eq(1L), any(SpellConditionDTO.class)))
                .thenReturn(spellConditionDto);

        mockMvc.perform(put("/api/spellconditions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "spellId": 10,
                                  "conditionId": 21
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.conditionId").value(21));

        verify(spellConditionService).updateSpellCondition(eq(1L), any(SpellConditionDTO.class));
    }

    @Test
    void deleteSpellCondition_shouldReturnNoContent() throws Exception {
        doNothing().when(spellConditionService).deleteSpellCondition(1L);

        mockMvc.perform(delete("/api/spellconditions/1"))
                .andExpect(status().isNoContent());

        verify(spellConditionService).deleteSpellCondition(1L);
    }
}