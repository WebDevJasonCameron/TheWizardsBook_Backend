package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import com.smashingwizards.thewizardsbook_backend.service.ConditionService;
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

@WebMvcTest(ConditionController.class)
class ConditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConditionService conditionService;

    @Test
    void getAllConditions_shouldReturnListOfConditions() throws Exception {
        List<ConditionDTO> conditions = List.of(
                new ConditionDTO(1L, "Test Name 1", "Test Description 1"),
                new ConditionDTO(2L, "Test Name 2", "Test Description 2")
        );

        when(conditionService.getAllConditions()).thenReturn(conditions);

        mockMvc.perform(get("/api/conditions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"));
    }

    @Test
    void getConditionById_shouldReturnCondition() throws Exception {
        ConditionDTO condition = new ConditionDTO(1L, "Test Name", "Test Description");

        when(conditionService.getConditionById(1L)).thenReturn(condition);

        mockMvc.perform(get("/api/conditions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void createCondition_shouldReturnCreatedCondition() throws Exception {
        ConditionDTO responseDto = new ConditionDTO(1L, "Test Name", "Test Description");

        when(conditionService.createCondition(any(ConditionDTO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/conditions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Test Name",
                                  "description": "Test Description"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void updateCondition_shouldReturnUpdatedCondition() throws Exception {
        ConditionDTO responseDto = new ConditionDTO(1L, "Test Name", "Updated description");

        when(conditionService.updateCondition(eq(1L), any(ConditionDTO.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/conditions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Test Name",
                                  "description": "Updated description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    void deleteCondition_shouldReturnNoContent() throws Exception {
        doNothing().when(conditionService).deleteCondition(1L);

        mockMvc.perform(delete("/api/conditions/1"))
                .andExpect(status().isNoContent());
    }

    /** ADDs */
    @Test
    @DisplayName("Get /api/conditions/search?name=test should return matching conditions")
    void getConditionsByNameContainingIgnoreCase_shouldReturnMatchingConditions() throws Exception{
        ConditionDTO conditionDto1 = new ConditionDTO(1L, "Test Name 1", "Test Description 1");
        ConditionDTO conditionDto2 = new ConditionDTO(2L, "Test Name 2", "Test Description 2");
        ConditionDTO conditionDto3 = new ConditionDTO(3L, "Name 3", "Test Description 3");

        when(conditionService.getAllByNameContainingIgnoreCase("test")).thenReturn(List.of(conditionDto1, conditionDto2, conditionDto3));

        mockMvc.perform(get("/api/conditions/search")
                .param("name", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"));

        verify(conditionService).getAllByNameContainingIgnoreCase(eq("test"));
    }
}