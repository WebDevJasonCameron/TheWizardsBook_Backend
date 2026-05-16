package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.*;
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

        when(spellService.searchSpells(
                eq("Test"),
                isNull(),
                isNull(),
                isNull(),
                isNull(),
                isNull(),
                isNull(),
                isNull(),
                isNull(),
                isNull(),
                isNull()
        )).thenReturn(List.of(spellDto1, spellDto2));

        mockMvc.perform(get("/api/spells/search")
                        .param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Spell Name 1"));
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

    @Test
    @DisplayName("GET /api/spells/search/by-tag?name=test should return matching tag name")
    void getAllSpellsContainingIgnorCase_shouldReturnMatchingTagName() throws Exception {
        SpellDTO spellDto1 = getSpellDTO(1L);
        SpellDTO spellDto2 = getSpellDTO(2L);

        when(spellService.getAllByTagNameContainingIgnoreCase(eq("Test"))).thenReturn(List.of(spellDto1, spellDto2));

        mockMvc.perform(get("/api/spells/search/by-tag")
                        .param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Spell Name 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Spell Name 2"));
    }

    @Test
    @DisplayName("GET /api/spells/search/by-ttrpg?name=test should return matching ttrpg name")
    void getAllSpellsContainingIgnorCase_shouldReturnMatchingTtrpgName() throws Exception {
        SpellDTO spellDto1 = getSpellDTO(1L);
        SpellDTO spellDto2 = getSpellDTO(2L);

        when(spellService.getAllByTtrpgNameContainingIgnoreCase(eq("Test"))).thenReturn(List.of(spellDto1, spellDto2));

        mockMvc.perform(get("/api/spells/search/by-ttrpg")
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

    @Test
    @DisplayName("POST /api/spells/with-details should create spell with relationships")
    void createSpellWithDetails_shouldCreateSpellWithRelationships() throws Exception {
        String requestBody = """
            {
              "spell": {
                "name": "Fireball",
                "level": "3",
                "castingTime": "1 action",
                "rangeArea": "150 feet",
                "componentVisual": true,
                "componentSemantic": true,
                "componentMaterial": true,
                "componentMaterials": "A tiny ball of bat guano and sulfur",
                "duration": "Instantaneous",
                "concentration": false,
                "ritual": false,
                "school": "Evocation",
                "description": "A bright streak flashes from your pointing finger..."
              },
              "rpgClassIds": [1],
              "tagIds": [2, 5],
              "sources": [
                {
                  "sourceId": 1,
                  "page": "pg. 241"
                }
              ],
              "ttrpgIds": [1]
            }
            """;

        SpellDTO spellDTO = new SpellDTO();
        spellDTO.setId(1L);
        spellDTO.setName("Fireball");
        spellDTO.setLevel("3");

        RpgClassDTO rpgClassDTO = new RpgClassDTO();
        rpgClassDTO.setId(1L);
        rpgClassDTO.setName("Wizard");

        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(2L);
        tagDTO.setName("Damage");

        SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setId(1L);
        sourceDTO.setName("Player Handbook");

        TtrpgDTO ttrpgDTO = new TtrpgDTO();
        ttrpgDTO.setId(1L);
        ttrpgDTO.setName("Dungeons & Dragons 5.5");

        SpellDetailsDTO responseDTO = new SpellDetailsDTO();
        responseDTO.setSpell(spellDTO);
        responseDTO.setRpgClasses(List.of(rpgClassDTO));
        responseDTO.setTags(List.of(tagDTO));
        responseDTO.setSources(List.of(sourceDTO));
        responseDTO.setTtrpgs(List.of(ttrpgDTO));

        when(spellService.createSpellWithDetails(any(CreateSpellRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/spells/with-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.spell.id").value(1))
                .andExpect(jsonPath("$.spell.name").value("Fireball"))
                .andExpect(jsonPath("$.spell.level").value("3"))
                .andExpect(jsonPath("$.rpgClasses[0].name").value("Wizard"))
                .andExpect(jsonPath("$.tags[0].name").value("Damage"))
                .andExpect(jsonPath("$.sources[0].name").value("Player Handbook"))
                .andExpect(jsonPath("$.ttrpgs[0].name").value("Dungeons & Dragons 5.5"));

        verify(spellService).createSpellWithDetails(any(CreateSpellRequestDTO.class));
    }
}
