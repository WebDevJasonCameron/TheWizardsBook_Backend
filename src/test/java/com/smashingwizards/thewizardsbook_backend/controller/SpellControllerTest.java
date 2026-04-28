package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.service.SpellService;
import org.jspecify.annotations.NonNull;
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

@WebMvcTest(SpellController.class)
public class SpellControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpellService spellService;

    @Test
    void getAllSpells_shouldReturnListOfSpells() throws Exception {
        List<SpellDTO> spells = List.of(
                new SpellDTO(1L, "Test Name 1", "Test Level 1", "Test CastingTime 1", "Test RangeArea 1", false, false, false, "N/A", "Test Duration 1", false, false, "Test School 1", "Test Description 1"),
                new SpellDTO(2L, "Test Name 2", "Test Level 2", "Test CastingTime 2", "Test RangeArea 2", false, false, false, "N/A", "Test Duration 2", false, false, "Test School 2", "Test Description 2")
        );

        when(spellService.getAllSpells()).thenReturn(spells);

        mockMvc.perform(get("/api/spells"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].level").value("Test Level 1"))
                .andExpect(jsonPath("$[0].castingTime").value("Test CastingTime 1"))
                .andExpect(jsonPath("$[0].rangeArea").value("Test RangeArea 1"))
                .andExpect(jsonPath("$[0].componentVisual").value(false))
                .andExpect(jsonPath("$[0].componentSemantic").value(false))
                .andExpect(jsonPath("$[0].componentMaterial").value(false))
                .andExpect(jsonPath("$[0].componentMaterials").value("N/A"))
                .andExpect(jsonPath("$[0].duration").value("Test Duration 1"))
                .andExpect(jsonPath("$[0].concentration").value(false))
                .andExpect(jsonPath("$[0].ritual").value(false))
                .andExpect(jsonPath("$[0].school").value("Test School 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].level").value("Test Level 2"))
                .andExpect(jsonPath("$[1].castingTime").value("Test CastingTime 2"))
                .andExpect(jsonPath("$[1].rangeArea").value("Test RangeArea 2"))
                .andExpect(jsonPath("$[1].componentVisual").value(false))
                .andExpect(jsonPath("$[1].componentSemantic").value(false))
                .andExpect(jsonPath("$[1].componentMaterial").value(false))
                .andExpect(jsonPath("$[1].componentMaterials").value("N/A"))
                .andExpect(jsonPath("$[1].duration").value("Test Duration 2"))
                .andExpect(jsonPath("$[1].concentration").value(false))
                .andExpect(jsonPath("$[1].ritual").value(false))
                .andExpect(jsonPath("$[1].school").value("Test School 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"));
    }

    @Test
    void getSpellById_shouldReturnSpell() throws Exception {
        SpellDTO spell = new SpellDTO(1L, "Test Name", "Test Level", "Test CastingTime", "Test RangeArea", false, false, false, "N/A", "Test Duration", false, false, "Test School", "Test Description");

        when(spellService.getSpellById(1L)).thenReturn(spell);

        mockMvc.perform(get("/api/spells/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.level").value("Test Level"))
                .andExpect(jsonPath("$.castingTime").value("Test CastingTime"))
                .andExpect(jsonPath("$.rangeArea").value("Test RangeArea"))
                .andExpect(jsonPath("$.componentVisual").value(false))
                .andExpect(jsonPath("$.componentSemantic").value(false))
                .andExpect(jsonPath("$.componentMaterial").value(false))
                .andExpect(jsonPath("$.componentMaterials").value("N/A"))
                .andExpect(jsonPath("$.duration").value("Test Duration"))
                .andExpect(jsonPath("$.concentration").value(false))
                .andExpect(jsonPath("$.ritual").value(false))
                .andExpect(jsonPath("$.school").value("Test School"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void createSpell_shouldReturnCreatedSpell() throws Exception {
        SpellDTO spell = new SpellDTO(1L, "Test Name", "Test Level", "Test CastingTime", "Test RangeArea", false, false, false, "N/A", "Test Duration", false, false, "Test School", "Test Description");

        when(spellService.createSpell(any(SpellDTO.class))).thenReturn(spell);

        mockMvc.perform(post("/api/spells")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Test Name",
                            "level": "Test Level",
                            "castingTime": "Test CastingTime",
                            "rangeArea": "Test RangeArea",
                            "componentVisual": false,
                            "componentSemantic": false,
                            "componentMaterial": false,
                            "componentMaterials": "N/A",
                            "duration": "Test Duration",
                            "concentration": false,
                            "ritual": false,
                            "school": "Test School",
                            "description": "Test Description"
                        }
                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.level").value("Test Level"))
                .andExpect(jsonPath("$.castingTime").value("Test CastingTime"))
                .andExpect(jsonPath("$.rangeArea").value("Test RangeArea"))
                .andExpect(jsonPath("$.componentVisual").value(false))
                .andExpect(jsonPath("$.componentSemantic").value(false))
                .andExpect(jsonPath("$.componentMaterial").value(false))
                .andExpect(jsonPath("$.componentMaterials").value("N/A"))
                .andExpect(jsonPath("$.duration").value("Test Duration"))
                .andExpect(jsonPath("$.concentration").value(false))
                .andExpect(jsonPath("$.ritual").value(false))
                .andExpect(jsonPath("$.school").value("Test School"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    void updateSpell_shouldReturnUpdatedSpell() throws Exception {
        SpellDTO updatedSpell = new SpellDTO(1L, "Updated Name", "Updated Level", "Updated CastingTime", "Updated RangeArea", false, false, false, "Updated N/A", "Updated Duration", false, false, "Updated School", "Updated Description");

        when(spellService.updateSpell(eq(1L), any(SpellDTO.class))).thenReturn(updatedSpell);

        mockMvc.perform(put("/api/spells/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Updated Name",
                            "level": "Updated Level",
                            "castingTime": "Updated CastingTime",
                            "rangeArea": "Updated RangeArea",
                            "componentVisual": false,
                            "componentSemantic": false,
                            "componentMaterial": false,
                            "componentMaterials": "N/A",
                            "duration": "Updated Duration",
                            "concentration": false,
                            "ritual": false,
                            "school": "Updated School",
                            "description": "Updated Description"
                        }
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.level").value("Updated Level"))
                .andExpect(jsonPath("$.castingTime").value("Updated CastingTime"))
                .andExpect(jsonPath("$.rangeArea").value("Updated RangeArea"))
                .andExpect(jsonPath("$.componentVisual").value(false))
                .andExpect(jsonPath("$.componentSemantic").value(false))
                .andExpect(jsonPath("$.componentMaterial").value(false))
                .andExpect(jsonPath("$.componentMaterials").value("N/A"))
                .andExpect(jsonPath("$.duration").value("Updated Duration"))
                .andExpect(jsonPath("$.concentration").value(false))
                .andExpect(jsonPath("$.ritual").value(false))
                .andExpect(jsonPath("$.school").value("Updated School"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void deleteSpell_shouldReturnNoContent() throws Exception {
        doNothing().when(spellService).deleteSpell(1L);

        mockMvc.perform(delete("/api/spells/1"))
                .andExpect(status().isNoContent());
    }

    /** ADDs */
    @Test
    @DisplayName("GET /api/spells/search?name=test should return matching spells")
    void getSpellsByName_shouldReturnMatchingSpells() throws Exception {
        SpellDTO spellDto1 = getSpellDTO(1L);
        SpellDTO spellDto2 = getSpellDTO(2L);

        when(spellService.getAllByNameContainingIgnoreCase(eq("Test"))).thenReturn(List.of(spellDto1, spellDto2));

        mockMvc.perform(get("/api/spells/search")
                .param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Spell Name 1"))
                .andExpect(jsonPath("$[0].level").value("Test Level"))
                .andExpect(jsonPath("$[0].castingTime").value("Test CastingTime"))
                .andExpect(jsonPath("$[0].rangeArea").value("Test Range"))
                .andExpect(jsonPath("$[0].componentVisual").value(false))
                .andExpect(jsonPath("$[0].componentSemantic").value(false))
                .andExpect(jsonPath("$[0].componentMaterial").value(false))
                .andExpect(jsonPath("$[0].componentMaterials").value("Test Materials"))
                .andExpect(jsonPath("$[0].duration").value("Test Duration"))
                .andExpect(jsonPath("$[0].concentration").value(false))
                .andExpect(jsonPath("$[0].ritual").value(false))
                .andExpect(jsonPath("$[0].school").value("Test School"))
                .andExpect(jsonPath("$[0].description").value("Test Description"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Spell Name 2"))
                .andExpect(jsonPath("$[1].level").value("Test Level"))
                .andExpect(jsonPath("$[1].castingTime").value("Test CastingTime"))
                .andExpect(jsonPath("$[1].rangeArea").value("Test Range"))
                .andExpect(jsonPath("$[1].componentVisual").value(false))
                .andExpect(jsonPath("$[1].componentSemantic").value(false))
                .andExpect(jsonPath("$[1].componentMaterial").value(false))
                .andExpect(jsonPath("$[1].componentMaterials").value("Test Materials"))
                .andExpect(jsonPath("$[1].duration").value("Test Duration"))
                .andExpect(jsonPath("$[1].concentration").value(false))
                .andExpect(jsonPath("$[1].ritual").value(false))
                .andExpect(jsonPath("$[1].school").value("Test School"))
                .andExpect(jsonPath("$[1].description").value("Test Description"));

        verify(spellService).getAllByNameContainingIgnoreCase(eq("Test"));
    }

    @Test
    @DisplayName("GET /api/spells/search/by-rpg-class?name=test should return matching spells")
    void getAllByRpgClassNameContainingIgnorCase_shouldReturnMatchingSpells() throws Exception {
        SpellDTO spellDto1 = getSpellDTO(1L);
        SpellDTO spellDto2 = getSpellDTO(2L);

        when(spellService.getAllByRpgClassNameContainingIgnoreCase(eq("Test"))).thenReturn(List.of(spellDto1, spellDto2));

        mockMvc.perform(get("/api/spells/search/by-rpg-class")
                        .param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Spell Name 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Spell Name 2"));

        verify(spellService).getAllByRpgClassNameContainingIgnoreCase(eq("Test"));
    }

    /** SUPs */
    private static @NonNull SpellDTO getSpellDTO(Long num) {
        SpellDTO spellDto = new SpellDTO();
        spellDto.setId(num);
        spellDto.setName("Test Spell Name " + num);
        spellDto.setLevel("Test Level");
        spellDto.setCastingTime("Test CastingTime");
        spellDto.setRangeArea("Test Range");
        spellDto.setComponentVisual(false);
        spellDto.setComponentSemantic(false);
        spellDto.setComponentMaterial(false);
        spellDto.setComponentMaterials("Test Materials");
        spellDto.setDuration("Test Duration");
        spellDto.setConcentration(false);
        spellDto.setRitual(false);
        spellDto.setSchool("Test School");
        spellDto.setDescription("Test Description");
        return spellDto;
    }

}
