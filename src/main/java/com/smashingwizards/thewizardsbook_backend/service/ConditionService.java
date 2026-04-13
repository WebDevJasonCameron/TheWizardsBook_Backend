package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;

import java.util.List;

public interface ConditionService {
    List<ConditionDTO> getAllConditions();
    ConditionDTO getConditionById(Long id);
    ConditionDTO createCondition(ConditionDTO conditionDTO);
    ConditionDTO updateCondition(Long id, ConditionDTO conditionDTO);
    void deleteCondition(Long id);
}
