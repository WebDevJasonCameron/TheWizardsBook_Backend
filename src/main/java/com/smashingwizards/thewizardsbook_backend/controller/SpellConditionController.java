package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellConditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellConditions")
public class SpellConditionController {

    // ATTs
    private final SpellConditionService spellConditionService;

    // CONs
    public SpellConditionController(SpellConditionService spellConditionService) {
        this.spellConditionService = spellConditionService;
    }

    // CRUDs
    @GetMapping
    public List<SpellConditionDTO> getSpellConditions() {
        return spellConditionService.getSpellConditions();
    }

    @GetMapping("/{id}")
    public SpellConditionDTO getSpellConditionById(@PathVariable Long id) {
        return spellConditionService.getSpellConditionById(id);
    }

    @PostMapping
    public ResponseEntity<SpellConditionDTO> createSpellCondition(@RequestBody SpellConditionDTO spellConditionDTO) {
        SpellConditionDTO createdSpellCondition = spellConditionService.createSpellCondition(spellConditionDTO);
        // If your LikeDTO carries 'id', build a location header

        return ResponseEntity
                .created(URI.create("/api/spellConditions" + createdSpellCondition.getId()))
                .body(createdSpellCondition);
    }

    @PutMapping("/{id}")
    public SpellConditionDTO updateSpellCondition(@PathVariable Long id, @RequestBody SpellConditionDTO spellConditionDTO) {
        return spellConditionService.updateSpellCondition(id, spellConditionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSpellCondition(@PathVariable Long id) {
        spellConditionService.deleteSpellCondition(id);
    }


}
