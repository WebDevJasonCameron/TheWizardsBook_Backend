package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.service.SourceService;
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

@WebMvcTest(SourceController.class)
public class SourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SourceService sourceService;

    @Test
    void getAllSources_shouldReturnListOfSources() throws Exception {
        List<SourceDTO> sources = List.of(
                new SourceDTO(1L, "Test Name 1", "Test PublishDate 1", "Test Publisher 1"),
                new SourceDTO(2L, "Test Name 2", "Test PublishDate 2", "Test Publisher 2")
        );

        when(sourceService.getAllSources()).thenReturn(sources);

        mockMvc.perform(get("/api/sources"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].publishDate").value("Test PublishDate 1"))
                .andExpect(jsonPath("$[0].publisher").value("Test Publisher 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].publishDate").value("Test PublishDate 2"))
                .andExpect(jsonPath("$[1].publisher").value("Test Publisher 2"));
    }

    @Test
    void getSourceById_shouldReturnSource() throws Exception {
        SourceDTO source = new SourceDTO(1L, "Test Name", "Test PublishDate", "Test Publisher");

        when(sourceService.getSourceById(1L)).thenReturn(source);

        mockMvc.perform(get("/api/sources/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.publishDate").value("Test PublishDate"))
                .andExpect(jsonPath("$.publisher").value("Test Publisher"));
    }

    @Test
    void createSource_shouldReturnCreatedSource() throws Exception {
        SourceDTO responseDto = new SourceDTO(1L, "Test Name", "Test PublishDate", "Test Publisher");

        when(sourceService.createSource(any(SourceDTO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/sources")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Test Name",
                            "publishDate": "Test PublishDate",
                            "publisher": "Test Publisher"
                        }
                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.publishDate").value("Test PublishDate"))
                .andExpect(jsonPath("$.publisher").value("Test Publisher"));
    }

    @Test
    void updateSource_shouldReturnUpdatedSource() throws Exception {
        SourceDTO responseDto = new SourceDTO(1L, "Updated Name", "Updated PublishDate", "Updated Publisher");

        when(sourceService.updateSource(eq(1L), any(SourceDTO.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/sources/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Updated Name",
                            "publishDate": "Updated PublishDate",
                            "publisher": "Updated Publisher"
                        }
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.publishDate").value("Updated PublishDate"))
                .andExpect(jsonPath("$.publisher").value("Updated Publisher"));
    }

    @Test
    void deleteSource_shouldReturnNoContent() throws Exception {
        doNothing().when(sourceService).deleteSource(1L);

        mockMvc.perform(delete("/api/sources/1"))
                .andExpect(status().isNoContent());
    }
}
