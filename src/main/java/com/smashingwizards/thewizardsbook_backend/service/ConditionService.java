package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConditionService {
    List<ConditionDTO> getConditions();
    ConditionDTO getConditionById(Long id);
    ConditionDTO createCondition(ConditionDTO conditionDTO);
    ConditionDTO updateCondition(Long id, ConditionDTO conditionDTO);
    void deleteCondition(Long id);
}
