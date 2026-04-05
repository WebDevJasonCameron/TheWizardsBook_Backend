package com.smashingwizards.thewizardsbook_backend.controller;


import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import com.smashingwizards.thewizardsbook_backend.service.ConditionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {

    // ATTs
    private final ConditionService conditionService;

    // CONs
    public ConditionController(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    // CRUDs
    @GetMapping()
    public List<ConditionDTO> getConditions() {
        return conditionService.getConditions();
    }

    @GetMapping("/{id}")
    public ConditionDTO getConditionById(@PathVariable Long id) {
        return conditionService.getConditionById(id);
    }

    @PostMapping
    public ConditionDTO createCondition(@RequestBody ConditionDTO conditionDTO) {
        return conditionService.createCondition(conditionDTO);
    }

    @PutMapping("/{id}")
    public ConditionDTO updateCondition(@PathVariable Long id, @RequestBody ConditionDTO conditionDTO) {
        return conditionService.updateCondition(id, conditionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCondition(@PathVariable Long id) {
        conditionService.deleteCondition(id);
    }
}
