package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellClassMapper;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import com.smashingwizards.thewizardsbook_backend.repository.RpgClassRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellClassRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.service.SpellClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellClassServiceImpl implements SpellClassService {

    // ATTs
    private final SpellClassRepository spellClassRepository;
    private final SpellRepository spellRepository;
    private final RpgClassRepository rpgClassRepository;
    private final SpellClassMapper spellClassMapper;

    // CONs
    public SpellClassServiceImpl(SpellClassRepository spellClassRepository, SpellRepository spellRepository, RpgClassRepository rpgClassRepository, SpellClassMapper spellClassMapper) {
        this.spellClassRepository = spellClassRepository;
        this.spellRepository = spellRepository;
        this.rpgClassRepository = rpgClassRepository;
        this.spellClassMapper = spellClassMapper;
    }

    // CRUDs
    @Override
    public List<SpellClassDTO> getSpellClasses() {
        return spellClassRepository.findAll()
                .stream()
                .map(spellClassMapper::spellClassToSpellClassDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpellClassDTO getSpellClassById(Long id) {
        return spellClassRepository.findById(id)
                .map(spellClassMapper::spellClassToSpellClassDTO)
                .orElseThrow(() -> new RuntimeException("SpellClass not found"));
    }

    @Override
    public SpellClassDTO createSpellClass(SpellClassDTO spellClassDTO) {
        Spell spellRef = spellRepository.getReferenceById(spellClassDTO.getSpellId());
        RpgClass rpgClassRef = rpgClassRepository.getReferenceById(spellClassDTO.getRpgClassId());

        SpellClass spellClass = new SpellClass(spellRef, rpgClassRef);
        return spellClassMapper.spellClassToSpellClassDTO(spellClassRepository.save(spellClass));
    }

    @Override
    public SpellClassDTO updateSpellClass(Long id, SpellClassDTO spellClassDTO) {
        SpellClass existingSpellClass = spellClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpellClass not found"));

        Spell spellRef = spellRepository.getReferenceById(spellClassDTO.getSpellId());
        RpgClass rpgClassRef = rpgClassRepository.getReferenceById(spellClassDTO.getRpgClassId());

        existingSpellClass.setSpell(spellRef);
        existingSpellClass.setRpgClass(rpgClassRef);

        return spellClassMapper.spellClassToSpellClassDTO(spellClassRepository.save(existingSpellClass));
    }

    @Override
    public void deleteSpellClass(Long id) {
        spellClassRepository.deleteById(id);
    }

}
