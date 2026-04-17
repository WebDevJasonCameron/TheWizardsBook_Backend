package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellSourceDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellSourceMapper;
import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellSource;
import com.smashingwizards.thewizardsbook_backend.repository.SourceRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellSourceRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.service.SpellSourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellSourceServiceImpl implements SpellSourceService {

    private final SpellSourceRepository spellSourceRepository;
    private final SpellRepository spellRepository;
    private final SourceRepository sourceRepository;
    private final SpellSourceMapper spellSourceMapper;

    // CONs
    public SpellSourceServiceImpl(SpellSourceRepository spellSourceRepository, SpellRepository spellRepository, SourceRepository sourceRepository, SpellSourceMapper spellSourceMapper) {
        this.spellSourceRepository = spellSourceRepository;
        this.spellRepository = spellRepository;
        this.sourceRepository = sourceRepository;
        this.spellSourceMapper = spellSourceMapper;
    }


    // CRUDs
    @Override
    public List<SpellSourceDTO> getAllSpellSources() {
        return spellSourceRepository.findAll()
                .stream()
                .map(spellSourceMapper::spellSourceToSpellSourceDTO)
                .toList();
    }

    @Override
    public SpellSourceDTO getSpellSourceById(Long id) {
        return spellSourceRepository.findById(id)
                .map(spellSourceMapper::spellSourceToSpellSourceDTO)
                .orElseThrow(() -> new RuntimeException("SpellSource not found"));
    }

    @Override
    public SpellSourceDTO createSpellSource(SpellSourceDTO spellSourceDTO) {
        Spell spellRef = spellRepository.getReferenceById(spellSourceDTO.getSpellId());
        Source sourceRef = sourceRepository.getReferenceById(spellSourceDTO.getSourceId());
        String page = spellSourceDTO.getPage();

        SpellSource spellSource = new SpellSource(spellRef, sourceRef, page);
        return spellSourceMapper.spellSourceToSpellSourceDTO(spellSourceRepository.save(spellSource));
    }

    @Override
    public SpellSourceDTO updateSpellSource(Long id, SpellSourceDTO spellSourceDTO) {
        SpellSource existingSpellSource = spellSourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpellSource not found"));

        Spell spellRef = spellRepository.getReferenceById(spellSourceDTO.getSpellId());
        Source sourceRef = sourceRepository.getReferenceById(spellSourceDTO.getSourceId());

        existingSpellSource.setSpell(spellRef);
        existingSpellSource.setSource(sourceRef);

        return spellSourceMapper.spellSourceToSpellSourceDTO(spellSourceRepository.save(existingSpellSource));
    }

    @Override
    public void deleteSpellSource(Long id) {
        spellSourceRepository.deleteById(id);
    }

}
