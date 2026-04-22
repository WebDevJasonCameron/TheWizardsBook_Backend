package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbook_backend.service.EffectService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EffectController.class)
public class EffectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EffectService effectService;

    @Test
    void getAllEffects_shouldReturnListOfEffects() throws Exception {
        List<EffectDTO> effects = List.of(
                new EffectDTO(1L, "Test Name 1", "Test subEffect 1"),
                new EffectDTO(2L, "Test Name 2", "Test subEffect 2")
        );

        when(effectService.getAllEffects()).thenReturn(effects);

        mockMvc.perform(get("/api/effects"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].subEffect").value("Test subEffect 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].subEffect").value("Test subEffect 2"));
    }

    @Test
    void getEffectById_shouldReturnEffect() throws Exception {
        EffectDTO effect = new EffectDTO(1L, "Test Name 1", "Test subEffect 1");

        when(effectService.getEffectById(1L)).thenReturn(effect);

        mockMvc.perform(get("/api/effects/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name 1"))
                .andExpect(jsonPath("$.subEffect").value("Test subEffect 1"));
    }

    @Test
    void createEffect_shouldReturnCreatedEffect() throws Exception {
        EffectDTO responseDto = new EffectDTO(1L, "Test Name 1", "Test subEffect 1");

        when(effectService.createEffect(any(EffectDTO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/effects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Test Name 1",
                                    "subEffect": "Test subEffect 1"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name 1"))
                .andExpect(jsonPath("$.subEffect").value("Test subEffect 1"));
    }

    @Test
    void updateEffect_shouldReturnUpdatedEffect() throws Exception {
        EffectDTO responseDto = new EffectDTO(1L, "Updated Name", "Updated subEffect");

        when(effectService.updateEffect(eq(1L), any(EffectDTO.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/effects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                    "name": "Updated Name",
                                    "subEffect": "Updated subEffect"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.subEffect").value("Updated subEffect"));
    }

    @Test
    void deleteEffect_shouldReturnNotContent() throws Exception {
        doNothing().when(effectService).deleteEffect(1L);

        mockMvc.perform(delete("/api/effects/1"))
                .andExpect(status().isNoContent());
    }

    /** ADDs */
    @Test
    @DisplayName("GET /api/effects/search?name=test should return matching effects")
    void getEffectByName_shouldReturnMatchingEffects() throws Exception {
        EffectDTO effectDto1 = new EffectDTO(1L, "Test Name 1", "Test subEffect 1");
        EffectDTO effectDto2 = new EffectDTO(2L, "Test Name 2", "Test subEffect 2");

        when(effectService.getAllByNameContainingIgnoreCase(eq("test"))).thenReturn(List.of(effectDto1, effectDto2));

        mockMvc.perform(get("/api/effects/search")
                .param("name", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].subEffect").value("Test subEffect 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].subEffect").value("Test subEffect 2"));

        verify(effectService).getAllByNameContainingIgnoreCase(eq("test"));
    }
}
