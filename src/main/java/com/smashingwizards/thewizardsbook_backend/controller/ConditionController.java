package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import com.smashingwizards.thewizardsbook_backend.service.ConditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {

    private final ConditionService conditionService;

    public ConditionController(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @GetMapping
    public ResponseEntity<List<ConditionDTO>> getAllConditions() {
        return ResponseEntity.ok(conditionService.getAllConditions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionDTO> getConditionById(@PathVariable Long id) {
        return ResponseEntity.ok(conditionService.getConditionById(id));
    }

    @PostMapping
    public ResponseEntity<ConditionDTO> createCondition(@RequestBody ConditionDTO conditionDTO) {
        ConditionDTO createdCondition = conditionService.createCondition(conditionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCondition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConditionDTO> updateCondition(@PathVariable Long id, @RequestBody ConditionDTO conditionDTO) {
        ConditionDTO updatedCondition = conditionService.updateCondition(id, conditionDTO);
        return ResponseEntity.ok(updatedCondition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id) {
        conditionService.deleteCondition(id);
        return ResponseEntity.noContent().build();
    }

    /** ADDs */
    @GetMapping("/search")
    public ResponseEntity<List<ConditionDTO>> getAllByNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(conditionService.getAllByNameContainingIgnoreCase(name));
    }
}