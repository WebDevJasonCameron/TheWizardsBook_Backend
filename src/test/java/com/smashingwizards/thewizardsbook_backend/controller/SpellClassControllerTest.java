package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellClassService;
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

@WebMvcTest(SpellClassController.class)
public class SpellClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellClassService spellClassService;

    @Test
    void getSpellClasses_shouldReturnListOfSpellClasses() throws Exception {
        List<SpellClassDTO> spellClassesDto = List.of(
                new SpellClassDTO(1L, 10L, 20L),
                new SpellClassDTO(2L, 11L, 21L)
        );

        when(spellClassService.getAllSpellClasses()).thenReturn(spellClassesDto);

        mockMvc.perform(get("/api/spellclasses"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].spellId").value(10))
                .andExpect(jsonPath("$[0].rpgClassId").value(20))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].spellId").value(11))
                .andExpect(jsonPath("$[1].rpgClassId").value(21));
    }

    @Test
    void getSpellClassById_shouldReturnSpellClass() throws Exception {
        SpellClassDTO spellClassDto = new SpellClassDTO(1L, 10L, 20L);

        when(spellClassService.getSpellClassById(1L)).thenReturn(spellClassDto);

        mockMvc.perform(get("/api/spellclasses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.rpgClassId").value(20));
    }

    @Test
    void createSpellClass_shouldReturnCreatedSpellClass() throws Exception {
        SpellClassDTO spellClassDto = new SpellClassDTO(1L, 10L, 20L);

        when(spellClassService.createSpellClass(any(SpellClassDTO.class))).thenReturn(spellClassDto);

        mockMvc.perform(post("/api/spellclasses")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "spellId": 10,
                                "rpgClassId": 20
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/spellclasses/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.rpgClassId").value(20));
    }

    @Test
    void updateSpellClass_shouldReturnUpdatedSpellClass() throws Exception {
        SpellClassDTO spellClassDto = new SpellClassDTO(1L, 10L, 21L);

        when(spellClassService.updateSpellClass(eq(1L), any(SpellClassDTO.class))).thenReturn(spellClassDto);

        mockMvc.perform(put("/api/spellclasses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                "spellId": 10,
                                "rpgClassId": 20
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.spellId").value(10))
                .andExpect(jsonPath("$.rpgClassId").value(21));

        verify(spellClassService).updateSpellClass(eq(1L), any(SpellClassDTO.class));
    }

    @Test
    void deleteSpellClass_shouldReturnNoContent() throws Exception {
        doNothing().when(spellClassService).deleteSpellClass(1L);

        mockMvc.perform(delete("/api/spellclasses/1"))
                .andExpect(status().isNoContent());
    }
}
