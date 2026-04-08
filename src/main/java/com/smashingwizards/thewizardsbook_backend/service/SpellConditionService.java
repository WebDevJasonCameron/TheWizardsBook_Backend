package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SpellConditionService {
    List<SpellConditionDTO> getSpellConditions();
    SpellConditionDTO getSpellConditionById(Long id);
    SpellConditionDTO createSpellCondition(SpellConditionDTO spellConditionDTO);
    SpellConditionDTO updateSpellCondition(Long id, SpellConditionDTO spellConditionDTO);
    void deleteSpellCondition(Long id);
}
