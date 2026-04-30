package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellMapper;
import com.smashingwizards.thewizardsbook_backend.model.*;
import com.smashingwizards.thewizardsbook_backend.repository.*;
import com.smashingwizards.thewizardsbook_backend.service.SpellService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpellServiceImpl implements SpellService {

    private SpellRepository spellRepository;
    private final SpellClassRepository spellClassRepository;
    private final SpellSourceRepository spellSourceRepository;
    private final SpellTagRepository spellTagRepository;
    private final SpellTtrpgRepository spellTtrpgRepository;
    public SpellMapper spellMapper;

    // CONs
    public SpellServiceImpl(SpellRepository spellRepository, SpellClassRepository spellClassRepository, SpellSourceRepository spellSourceRepository, SpellTagRepository spellTagRepository,  SpellTtrpgRepository spellTtrpgRepository, SpellMapper spellMapper) {
        this.spellRepository = spellRepository;
        this.spellClassRepository = spellClassRepository;
        this.spellSourceRepository = spellSourceRepository;
        this.spellTagRepository = spellTagRepository;
        this.spellTtrpgRepository = spellTtrpgRepository;
        this.spellMapper = spellMapper;
    }

    // CRUDs
    @Override
    public List<SpellDTO> getAllSpells() {
        return spellRepository.findAll().stream()
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public SpellDTO getSpellById(Long id) {
        return spellRepository.findById(id)
                .map(spellMapper::spellToSpellDTO)
                .orElseThrow(() -> new RuntimeException("Spell not found"));
    }

    @Override
    @Transactional
    public SpellDTO createSpell(SpellDTO spellDTO) {
        return spellMapper.spellToSpellDTO(spellRepository
                .save(spellMapper.spellDTOToSpell(spellDTO)));
    }

    @Override
    @Transactional
    public SpellDTO updateSpell(Long id, SpellDTO spellDTO) {
        Optional<Spell> optionalSpell = spellRepository.findById(id);
        if (!optionalSpell.isPresent()) {
            throw new RuntimeException("Spell not found");
        }
        Spell existingSpell = optionalSpell.get();

        existingSpell.setName(spellDTO.getName());
        existingSpell.setLevel(spellDTO.getLevel());
        existingSpell.setCastingTime(spellDTO.getCastingTime());
        existingSpell.setRangeArea(spellDTO.getRangeArea());
        existingSpell.setComponentVisual(spellDTO.isComponentVisual());
        existingSpell.setComponentSemantic(spellDTO.isComponentSemantic());
        existingSpell.setComponentMaterial(spellDTO.isComponentMaterial());
        existingSpell.setComponentMaterials(spellDTO.getComponentMaterials());
        existingSpell.setDuration(spellDTO.getDuration());
        existingSpell.setConcentration(spellDTO.isConcentration());
        existingSpell.setRitual(spellDTO.isRitual());
        existingSpell.setSchool(spellDTO.getSchool());
        existingSpell.setDescription(spellDTO.getDescription());

        return spellMapper.spellToSpellDTO(spellRepository.save(existingSpell));
    }

    @Override
    public void deleteSpell(Long id) {
        spellRepository.deleteById(id);
    }

    /** ADDs */
    @Override
    public List<SpellDTO> getAllByNameContainingIgnoreCase(String name) {
        return spellRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllByRpgClassNameContainingIgnoreCase(String name) {
        return spellClassRepository.findAllByRpgClass_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellClass::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllBySourceNameContainingIgnoreCase(String name) {
        return spellSourceRepository.findAllBySource_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellSource::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllByTagNameContainingIgnoreCase(String name) {
        return spellTagRepository.findAllByTag_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellTag::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllByTtrpgNameContainingIgnoreCase(String name) {
        return spellTtrpgRepository.findAllByTtrpg_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellTtrpg::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

}
