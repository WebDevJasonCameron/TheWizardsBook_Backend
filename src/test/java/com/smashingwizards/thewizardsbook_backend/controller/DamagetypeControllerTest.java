package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.DamagetypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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

@WebMvcTest(DamagetypeController.class)
class DamagetypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DamagetypeService damagetypeService;

    @Test
    void getAllDamagetypes_shouldReturnListOfDamagetypes() throws Exception {
        List<DamagetypeDTO> damagetypes = List.of(
                new DamagetypeDTO(1L, "Test Name 1"),
                new DamagetypeDTO(2L, "Test Name 2")
        );

        when(damagetypeService.getAllDamagetypes()).thenReturn(damagetypes);

        mockMvc.perform(get("/api/damagetypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"));
    }

    @Test
    void getDamagetypeById_shouldReturnDamagetype() throws Exception {
        DamagetypeDTO damagetype = new DamagetypeDTO(1L, "Test Name 1");

        when(damagetypeService.getDamagetypeById(1L)).thenReturn(damagetype);

        mockMvc.perform(get("/api/damagetypes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name 1"));
    }

    @Test
    void createDamagetype_shouldReturnCreatedDamagetype() throws Exception {
        DamagetypeDTO responseDto = new DamagetypeDTO(1L, "Test Name 1");

        when(damagetypeService.createDamagetype(any(DamagetypeDTO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/damagetypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Test Name 1"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name 1"));
    }

    @Test
    void updateDamagetype_shouldReturnUpdatedDamagetype() throws Exception {
        DamagetypeDTO responseDto = new DamagetypeDTO(1L, "Updated Name");

        when(damagetypeService.updateDamagetype(eq(1L), any(DamagetypeDTO.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/damagetypes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Updated Name"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    void deleteDamagetype_shouldReturnNoContent() throws Exception {
        doNothing().when(damagetypeService).deleteDamagetype(1L);

        mockMvc.perform(delete("/api/damagetypes/1"))
                .andExpect(status().isNoContent());
    }

    /** ADDs */
    @Test
    @DisplayName("Get /api/damagetypes/search?name=test should return matching damagetypes")
    void getDamagetypesByName_shouldReturnMatchingSpells() throws Exception{
        DamagetypeDTO damagetypeDto1 = new DamagetypeDTO(1L, "Test Name 1");
        DamagetypeDTO damagetypeDto2 = new DamagetypeDTO(2L, "Test Name 2");

        when(damagetypeService.getAllByNameContainingIgnoreCase("test")).thenReturn(List.of(damagetypeDto1, damagetypeDto2));

        mockMvc.perform(get("/api/damagetypes/search?name=test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"));

        verify(damagetypeService).getAllByNameContainingIgnoreCase("test");
    }
}