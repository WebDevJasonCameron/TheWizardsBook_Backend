package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.TypeService;
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

@WebMvcTest(TypeController.class)
public class TypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TypeService typeService;

    @Test
    void getAllTypes_shouldReturnListOfTypes() throws Exception {
        List<TypeDTO> types = List.of(
                new TypeDTO(1L, "Test Type 1", "Test SubType 1"),
                new TypeDTO(2L, "Test Type 2", "Test SubType 2")
        );

        when(typeService.getAllTypes()).thenReturn(types);


        mockMvc.perform(get("/api/types"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Type 1"))
                .andExpect(jsonPath("$[0].subType").value("Test SubType 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Type 2"))
                .andExpect(jsonPath("$[1].subType").value("Test SubType 2"));

    }

    @Test
    void getTypeById_shouldReturnType() throws Exception {
        TypeDTO type = new TypeDTO(1L, "Test Type", "Test SubType");

        when(typeService.getTypeById(1L)).thenReturn(type);

        mockMvc.perform(get("/api/types/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Type"))
                .andExpect(jsonPath("$.subType").value("Test SubType"));
    }

    @Test
    void createType_shouldReturnCreatedType() throws Exception {
        TypeDTO type = new TypeDTO(1L, "Test Type", "Test SubType");

        when(typeService.createType(any(TypeDTO.class))).thenReturn(type);

        mockMvc.perform(post("/api/types")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Test Type",
                            "subType": "Test SubType"
                        }
                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Type"))
                .andExpect(jsonPath("$.subType").value("Test SubType"));
    }

    @Test
    void updateType_shouldReturnUpdatedType() throws Exception {
        TypeDTO typeDto = new TypeDTO(1L, "Updated Type", "Updated SubType");

        when(typeService.updateType(eq(1L), any(TypeDTO.class))).thenReturn(typeDto);

        mockMvc.perform(put("/api/types/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Updated Type",
                            "subType": "Updated SubType"
                        }
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Type"))
                .andExpect(jsonPath("$.subType").value("Updated SubType"));
    }

    @Test
    void deleteType_shouldReturnNoContent() throws Exception {
        doNothing().when(typeService).deleteType(1L);

        mockMvc.perform(delete("/api/types/1"))
                .andExpect(status().isNoContent());
    }
}
