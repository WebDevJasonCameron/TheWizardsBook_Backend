package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellConditionMapper;
import com.smashingwizards.thewizardsbook_backend.model.Condition;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellCondition;
import com.smashingwizards.thewizardsbook_backend.repository.ConditionRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellConditionRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.service.SpellConditionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellConditionServiceImpl implements SpellConditionService {

    // ATTs
    private final SpellConditionRepository spellConditionRepository;
    private final SpellRepository spellRepository;
    private final ConditionRepository conditionRepository;
    private final SpellConditionMapper spellConditionMapper;

    // CONs
    public SpellConditionServiceImpl(SpellConditionRepository spellConditionRepository, SpellRepository spellRepository, ConditionRepository conditionRepository, SpellConditionMapper spellConditionMapper) {
        this.spellConditionRepository = spellConditionRepository;
        this.spellRepository = spellRepository;
        this.conditionRepository = conditionRepository;
        this.spellConditionMapper = spellConditionMapper;
    }


    // CRUDs
    @Override
    public List<SpellConditionDTO> getSpellConditions() {
        return spellConditionRepository.findAll()
                .stream()
                .map(spellConditionMapper::spellConditionToSpellConditionDTO)
                .toList();
    }

    @Override
    public SpellConditionDTO getSpellConditionById(Long id) {
        return spellConditionRepository.findById(id)
                .map(spellConditionMapper::spellConditionToSpellConditionDTO)
                .orElseThrow(() -> new RuntimeException("SpellCondition not found"));
    }

    @Override
    public SpellConditionDTO createSpellCondition(SpellConditionDTO spellConditionDTO) {
        Spell spellRef = spellRepository.getReferenceById(spellConditionDTO.getSpellId());
        Condition conditionRef = conditionRepository.getReferenceById(spellConditionDTO.getConditionId());

        SpellCondition spellCondition = new SpellCondition(spellRef, conditionRef);
        return spellConditionMapper.spellConditionToSpellConditionDTO(spellConditionRepository.save(spellCondition));
    }

    @Override
    public SpellConditionDTO updateSpellCondition(Long id, SpellConditionDTO spellConditionDTO) {
        SpellCondition existingSpellCondition = spellConditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpellCondition not found"));

        Spell spellRef = spellRepository.getReferenceById(spellConditionDTO.getSpellId());
        Condition conditionRef = conditionRepository.getReferenceById(spellConditionDTO.getConditionId());

        existingSpellCondition.setSpell(spellRef);
        existingSpellCondition.setCondition(conditionRef);

        return spellConditionMapper.spellConditionToSpellConditionDTO(spellConditionRepository.save(existingSpellCondition));
    }

    @Override
    public void deleteSpellCondition(Long id) {
        spellConditionRepository.deleteById(id);
    }
}
