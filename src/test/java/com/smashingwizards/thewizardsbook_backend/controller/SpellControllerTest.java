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
                getSpellDTO(1L),
                getSpellDTO(2L)
        );

        when(spellService.getAllSpells()).thenReturn(spells);

        mockMvc.perform(get("/api/spells"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
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
    }

    @Test
    void getSpellById_shouldReturnSpell() throws Exception {
        SpellDTO spell = getSpellDTO(1L);

        when(spellService.getSpellById(1L)).thenReturn(spell);

        mockMvc.perform(get("/api/spells/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Spell Name 1"))
                .andExpect(jsonPath("$.level").value("Test Level"))
                .andExpect(jsonPath("$.castingTime").value("Test CastingTime"))
                .andExpect(jsonPath("$.rangeArea").value("Test Range"))
                .andExpect(jsonPath("$.componentVisual").value(false))
                .andExpect(jsonPath("$.componentSemantic").value(false))
                .andExpect(jsonPath("$.componentMaterial").value(false))
                .andExpect(jsonPath("$.componentMaterials").value("Test Materials"))
                .andExpect(jsonPath("$.duration").value("Test Duration"))
                .andExpect(jsonPath("$.concentration").value(false))
                .andExpect(jsonPath("$.ritual").value(false))
                .andExpect(jsonPath("$.school").value("Test School"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void createSpell_shouldReturnCreatedSpell() throws Exception {
        SpellDTO spell = getSpellDTO(1L);

        when(spellService.createSpell(any(SpellDTO.class))).thenReturn(spell);

        mockMvc.perform(post("/api/spells")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Test Spell Name 1",
                            "level": "Test Level",
                            "castingTime": "Test CastingTime",
                            "rangeArea": "Test Range",
                            "componentVisual": false,
                            "componentSemantic": false,
                            "componentMaterial": false,
                            "componentMaterials": "Test Materials",
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
                .andExpect(jsonPath("$.name").value("Test Spell Name 1"))
                .andExpect(jsonPath("$.level").value("Test Level"))
                .andExpect(jsonPath("$.castingTime").value("Test CastingTime"))
                .andExpect(jsonPath("$.rangeArea").value("Test Range"))
                .andExpect(jsonPath("$.componentVisual").value(false))
                .andExpect(jsonPath("$.componentSemantic").value(false))
                .andExpect(jsonPath("$.componentMaterial").value(false))
                .andExpect(jsonPath("$.componentMaterials").value("Test Materials"))
                .andExpect(jsonPath("$.duration").value("Test Duration"))
                .andExpect(jsonPath("$.concentration").value(false))
                .andExpect(jsonPath("$.ritual").value(false))
                .andExpect(jsonPath("$.school").value("Test School"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void updateSpell_shouldReturnUpdatedSpell() throws Exception {
        SpellDTO updatedSpell = getNewSpellDTO(1L);

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
                .andExpect(jsonPath("$.name").value("Updated Spell Name"))
                .andExpect(jsonPath("$.level").value("Updated Level"))
                .andExpect(jsonPath("$.castingTime").value("Updated CastingTime"))
                .andExpect(jsonPath("$.rangeArea").value("Updated Range"))
                .andExpect(jsonPath("$.componentVisual").value(false))
                .andExpect(jsonPath("$.componentSemantic").value(false))
                .andExpect(jsonPath("$.componentMaterial").value(false))
                .andExpect(jsonPath("$.componentMaterials").value("Updated Materials"))
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
    @DisplayName("GET /api/spells/search/by-rpg-class?name=test should return matching rpgClass name")
    void getAllSpellsContainingIgnorCase_shouldReturnMatchingRpgClassName() throws Exception {
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

    @Test
    @DisplayName("GET /api/spells/search/by-source?name=test should return matching source name")
    void getAllSpellsContainingIgnorCase_shouldReturnMatchingSourceName() throws Exception {
        SpellDTO spellDto1 = getSpellDTO(1L);
        SpellDTO spellDto2 = getSpellDTO(2L);

        when(spellService.getAllBySourceNameContainingIgnoreCase(eq("Test"))).thenReturn(List.of(spellDto1, spellDto2));

        mockMvc.perform(get("/api/spells/search/by-source")
                        .param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Spell Name 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Spell Name 2"));
    }

    /** SUPs */
    private static @NonNull Spell getSpell() {
        Spell spell = new Spell();
        spell.setId(1L);
        spell.setName("Test Name");
        spell.setLevel("Test Level");
        spell.setCastingTime("Test CastingTime");
        spell.setRangeArea("Test Range");
        spell.setComponentVisual(false);
        spell.setComponentSemantic(false);
        spell.setComponentMaterial(false);
        spell.setComponentMaterials("Test Materials");
        spell.setDuration("Test Duration");
        spell.setConcentration(false);
        spell.setRitual(false);
        spell.setSchool("Test School");
        spell.setDescription("Test Description");
        return spell;
    }

    private static @NonNull Spell getSpell(Long num) {
        Spell spell = new Spell();
        spell.setId(num);
        spell.setName("Test Name " +  num);
        spell.setLevel("Test Level");
        spell.setCastingTime("Test CastingTime");
        spell.setRangeArea("Test Range");
        spell.setComponentVisual(false);
        spell.setComponentSemantic(false);
        spell.setComponentMaterial(false);
        spell.setComponentMaterials("Test Materials");
        spell.setDuration("Test Duration");
        spell.setConcentration(false);
        spell.setRitual(false);
        spell.setSchool("Test School");
        spell.setDescription("Test Description");
        return spell;
    }

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

    private static @NonNull SpellDTO getNewSpellDTO(Long num) {
        SpellDTO spellDto = new SpellDTO();
        spellDto.setId(num);
        spellDto.setName("Updated Spell Name");
        spellDto.setLevel("Updated Level");
        spellDto.setCastingTime("Updated CastingTime");
        spellDto.setRangeArea("Updated Range");
        spellDto.setComponentVisual(false);
        spellDto.setComponentSemantic(false);
        spellDto.setComponentMaterial(false);
        spellDto.setComponentMaterials("Updated Materials");
        spellDto.setDuration("Updated Duration");
        spellDto.setConcentration(false);
        spellDto.setRitual(false);
        spellDto.setSchool("Updated School");
        spellDto.setDescription("Updated Description");
        return spellDto;
    }

    private static @NonNull SpellDTO getOldSpellDTO(Long num) {
        SpellDTO spellDto = new SpellDTO();
        spellDto.setId(num);
        spellDto.setName("Old Spell Name");
        spellDto.setLevel("Old Level");
        spellDto.setCastingTime("Old CastingTime");
        spellDto.setRangeArea("Old Range");
        spellDto.setComponentVisual(false);
        spellDto.setComponentSemantic(false);
        spellDto.setComponentMaterial(false);
        spellDto.setComponentMaterials("Old Materials");
        spellDto.setDuration("Old Duration");
        spellDto.setConcentration(false);
        spellDto.setRitual(false);
        spellDto.setSchool("Old School");
        spellDto.setDescription("Old Description");
        return spellDto;
    }
}
