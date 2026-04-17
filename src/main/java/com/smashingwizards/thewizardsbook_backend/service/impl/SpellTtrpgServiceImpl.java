package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellTtrpgMapper;
import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import com.smashingwizards.thewizardsbook_backend.repository.TtrpgRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellTtrpgRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.service.SpellTtrpgService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellTtrpgServiceImpl implements SpellTtrpgService {

    private final SpellTtrpgRepository spellTtrpgRepository;
    private final SpellRepository spellRepository;
    private final TtrpgRepository ttrpgRepository;
    private final SpellTtrpgMapper spellTtrpgMapper;

    // CONs
    public SpellTtrpgServiceImpl(SpellTtrpgRepository spellTtrpgRepository, SpellRepository spellRepository, TtrpgRepository ttrpgRepository, SpellTtrpgMapper spellTtrpgMapper) {
        this.spellTtrpgRepository = spellTtrpgRepository;
        this.spellRepository = spellRepository;
        this.ttrpgRepository = ttrpgRepository;
        this.spellTtrpgMapper = spellTtrpgMapper;
    }


    // CRUDs
    @Override
    public List<SpellTtrpgDTO> getAllSpellTtrpgs() {
        return spellTtrpgRepository.findAll()
                .stream()
                .map(spellTtrpgMapper::spellTtrpgToSpellTtrpgDTO)
                .toList();
    }

    @Override
    public SpellTtrpgDTO getSpellTtrpgById(Long id) {
        return spellTtrpgRepository.findById(id)
                .map(spellTtrpgMapper::spellTtrpgToSpellTtrpgDTO)
                .orElseThrow(() -> new RuntimeException("SpellTtrpg not found"));
    }

    @Override
    public SpellTtrpgDTO createSpellTtrpg(SpellTtrpgDTO spellTtrpgDTO) {
        Spell spellRef = spellRepository.getReferenceById(spellTtrpgDTO.getSpellId());
        Ttrpg ttrpgRef = ttrpgRepository.getReferenceById(spellTtrpgDTO.getTtrpgId());

        SpellTtrpg spellTtrpg = new SpellTtrpg(spellRef, ttrpgRef);
        return spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(spellTtrpgRepository.save(spellTtrpg));
    }

    @Override
    public SpellTtrpgDTO updateSpellTtrpg(Long id, SpellTtrpgDTO spellTtrpgDTO) {
        SpellTtrpg existingSpellTtrpg = spellTtrpgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpellTtrpg not found"));

        Spell spellRef = spellRepository.getReferenceById(spellTtrpgDTO.getSpellId());
        Ttrpg ttrpgRef = ttrpgRepository.getReferenceById(spellTtrpgDTO.getTtrpgId());

        existingSpellTtrpg.setSpell(spellRef);
        existingSpellTtrpg.setTtrpg(ttrpgRef);

        return spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(spellTtrpgRepository.save(existingSpellTtrpg));
    }

    @Override
    public void deleteSpellTtrpg(Long id) {
        spellTtrpgRepository.deleteById(id);
    }
}
