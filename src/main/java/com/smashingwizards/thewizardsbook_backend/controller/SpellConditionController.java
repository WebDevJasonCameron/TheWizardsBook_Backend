package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellConditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellconditions")
public class SpellConditionController {

    private final SpellConditionService spellConditionService;

    public SpellConditionController(SpellConditionService spellConditionService) {
        this.spellConditionService = spellConditionService;
    }

    @GetMapping
    public ResponseEntity<List<SpellConditionDTO>> getAllSpellConditions() {
        return ResponseEntity.ok(spellConditionService.getAllSpellConditions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellConditionDTO> getSpellConditionById(@PathVariable Long id) {
        return ResponseEntity.ok(spellConditionService.getSpellConditionById(id));
    }

    @PostMapping
    public ResponseEntity<SpellConditionDTO> createSpellCondition(@RequestBody SpellConditionDTO spellConditionDTO) {
        SpellConditionDTO createdSpellCondition = spellConditionService.createSpellCondition(spellConditionDTO);

        return ResponseEntity
                .created(URI.create("/api/spellconditions/" + createdSpellCondition.getId()))
                .body(createdSpellCondition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellConditionDTO> updateSpellCondition(@PathVariable Long id,
                                                                  @RequestBody SpellConditionDTO spellConditionDTO) {
        return ResponseEntity.ok(spellConditionService.updateSpellCondition(id, spellConditionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellCondition(@PathVariable Long id) {
        spellConditionService.deleteSpellCondition(id);
        return ResponseEntity.noContent().build();
    }
}