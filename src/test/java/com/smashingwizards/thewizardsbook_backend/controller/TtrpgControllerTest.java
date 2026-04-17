package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.service.TtrpgService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TtrpgController.class)
public class TtrpgControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TtrpgService ttrpgService;

    @Test
    void getAllTtrpgs_shouldReturnListOfTtrpgs() throws Exception {
        List<TtrpgDTO> ttrpgs = List.of(
                new TtrpgDTO(1L, "Test TTRPG 1", "Test Version 1"),
                new TtrpgDTO(2L, "Test TTRPG 2", "Test Version 2")
        );

        when(ttrpgService.getAllTtrpgs()).thenReturn(ttrpgs);

        mockMvc.perform(get("/api/ttrpgs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test TTRPG 1"))
                .andExpect(jsonPath("$[0].version").value("Test Version 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test TTRPG 2"))
                .andExpect(jsonPath("$[1].version").value("Test Version 2"));
    }

    @Test
    void getTtrpgById_shouldReturnTtrpg() throws Exception {
        TtrpgDTO ttrpg = new TtrpgDTO(1L, "Test TTRPG", "Test Version");

        when(ttrpgService.getTtrpgById(1L)).thenReturn(ttrpg);

        mockMvc.perform(get("/api/ttrpgs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test TTRPG"))
                .andExpect(jsonPath("$.version").value("Test Version"));
    }

    @Test
    void createTtrpg_shouldReturnCreatedTtrpg() throws Exception {
        TtrpgDTO ttrpg = new TtrpgDTO(1L, "Test TTRPG", "Test Version");

        when(ttrpgService.createTtrpg(any(TtrpgDTO.class))).thenReturn(ttrpg);

        mockMvc.perform(post("/api/ttrpgs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Test TTRPG",
                            "version": "Test Version"
                        }
                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test TTRPG"))
                .andExpect(jsonPath("$.version").value("Test Version"));
    }

    @Test
    void updateTtrpg_shouldReturnUpdatedTtrpg() throws Exception {
        TtrpgDTO ttrpg = new TtrpgDTO(1L, "Updated TTRPG", "Updated Version");

        when(ttrpgService.updateTtrpg(eq(1L), any(TtrpgDTO.class))).thenReturn(ttrpg);

        mockMvc.perform(put("/api/ttrpgs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Updated TTRPG",
                            "version": "Updated Version"
                        }
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated TTRPG"))
                .andExpect(jsonPath("$.version").value("Updated Version"));
    }

    @Test
    void deleteTtrpg_shouldReturnNoContent() throws Exception {
        doNothing().when(ttrpgService).deleteTtrpg(1L);

        mockMvc.perform(delete("/api/ttrpgs/1"))
                .andExpect(status().isNoContent());
    }
}
