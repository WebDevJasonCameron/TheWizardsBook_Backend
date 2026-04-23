package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.service.RpgClassService;
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

@WebMvcTest(RpgClassController.class)
public class RpgClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RpgClassService rpgClassServiceMock;
    @Autowired
    private RpgClassService rpgClassService;

    @Test
    void getAllRpgClasses_shouldReturnListOfRpgClasses() throws Exception {
        List<RpgClassDTO> rpgClasses = List.of(
                new RpgClassDTO(1L, "Test Class 1", "Test subClassName 1", "Test Description 1"),
                new RpgClassDTO(2L, "Test Class 2", "Test subClassName 2", "Test Description 2")
        );

        when(rpgClassService.getAllRpgClasses()).thenReturn(rpgClasses);

        mockMvc.perform(get("/api/rpgclasses"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Class 1"))
                .andExpect(jsonPath("$[0].subClassName").value("Test subClassName 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Class 2"))
                .andExpect(jsonPath("$[1].subClassName").value("Test subClassName 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"));
    }

    @Test
    void getRpgClassById_shouldReturnRpgClass() throws Exception {
        RpgClassDTO rpgClass = new RpgClassDTO(1L, "Test Class", "Test subClassName", "Test Description");

        when(rpgClassService.getRpgClassById(1L)).thenReturn(rpgClass);

        mockMvc.perform(get("/api/rpgclasses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Class"))
                .andExpect(jsonPath("$.subClassName").value("Test subClassName"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void createRpgClass_shouldReturnCreatedRpgClass() throws Exception {
        RpgClassDTO responseDto = new RpgClassDTO(1L, "Test Class", "Test subClassName", "Test Description");

        when(rpgClassService.createRpgClass(any(RpgClassDTO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/rpgclasses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                            "name": "Test Class",
                            "subClassName": "Test subClassName",
                            "description": "Test Description"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Class"))
                .andExpect(jsonPath("$.subClassName").value("Test subClassName"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void updateRpgClass_shouldReturnUpdatedRpgClass() throws Exception {
        RpgClassDTO responseDto = new RpgClassDTO(1L, "Updated Name", "Updated subClassName", "Updated Description");

        when(rpgClassService.updateRpgClass(eq(1L), any(RpgClassDTO.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/rpgclasses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                            "name": "Updated Name",
                            "subClassName": "Updated subClassName",
                            "description": "Updated Description"
                            }
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.subClassName").value("Updated subClassName"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void deleteRpgClass_shouldReturnNoContent() throws Exception {
        doNothing().when(rpgClassService).deleteRpgClass(1L);

        mockMvc.perform(delete("/api/rpgclasses/1"))
                .andExpect(status().isNoContent());
    }

    /** ADDs */
    @Test
    @DisplayName("Get /api/rpgclasses/search?name=test should return matching RpgClass")
    void getRpgClassesByName_shouldReturnMatchingRpgClasses() throws Exception{
        RpgClassDTO rpgClassDto1 = new RpgClassDTO(1L, "Test Name 1", "Test subClassName 1", "Test Description 1");
        RpgClassDTO rpgClassDto2 = new RpgClassDTO(2L, "Test Name 2", "Test subClassName 2", "Test Description 2");

        when(rpgClassService.getAllByNameContainingIgnoreCase(eq("Test"))).thenReturn(List.of(rpgClassDto1, rpgClassDto2));

        mockMvc.perform(get("/api/rpgclasses/search")
                        .param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].subClassName").value("Test subClassName 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].subClassName").value("Test subClassName 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"));

        verify(rpgClassService).getAllByNameContainingIgnoreCase(eq("Test"));
    }
}
