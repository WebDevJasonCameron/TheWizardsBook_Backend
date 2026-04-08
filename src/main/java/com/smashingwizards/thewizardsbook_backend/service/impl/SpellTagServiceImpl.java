package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellTagMapper;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellTag;
import com.smashingwizards.thewizardsbook_backend.model.Tag;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellTagRepository;
import com.smashingwizards.thewizardsbook_backend.repository.TagRepository;
import com.smashingwizards.thewizardsbook_backend.service.SpellTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellTagServiceImpl implements SpellTagService {

    // ATTs
    private final SpellTagRepository spellTagRepository;
    private final SpellRepository spellRepository;
    private final TagRepository tagRepository;
    private final SpellTagMapper spellTagMapper;

    // CONs
    public SpellTagServiceImpl(SpellTagRepository spellTagRepository, SpellRepository spellRepository, TagRepository tagRepository, SpellTagMapper spellTagMapper) {
        this.spellTagRepository = spellTagRepository;
        this.spellRepository = spellRepository;
        this.tagRepository = tagRepository;
        this.spellTagMapper = spellTagMapper;
    }

    // CRUDs
    @Override
    public List<SpellTagDTO> getSpellTags() {
        return spellTagRepository.findAll()
                .stream()
                .map(spellTagMapper::spellTagToSpellTagDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpellTagDTO getSpellTagById(Long id) {
        return spellTagRepository.findById(id)
                .map(spellTagMapper::spellTagToSpellTagDTO)
                .orElseThrow(() -> new RuntimeException("SpellTag not found"));
    }

    @Override
    public SpellTagDTO createSpellTag(SpellTagDTO spellTagDTO) {
        Spell spellRef = spellRepository.getReferenceById(spellTagDTO.getTagId());
        Tag tagRef = tagRepository.getReferenceById(spellTagDTO.getTagId());

        SpellTag spellTag = new SpellTag(spellRef, tagRef);
        return spellTagMapper.spellTagToSpellTagDTO(spellTagRepository.save(spellTag));
    }

    @Override
    public SpellTagDTO updateSpellTag(Long id, SpellTagDTO spellTagDTO) {
        SpellTag existingSpellTag = spellTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpellTag not found"));

        Spell spellRef = spellRepository.getReferenceById(spellTagDTO.getTagId());
        Tag tagRef = tagRepository.getReferenceById(spellTagDTO.getTagId());

        existingSpellTag.setSpell(spellRef);
        existingSpellTag.setTag(tagRef);

        return spellTagMapper.spellTagToSpellTagDTO(spellTagRepository.save(existingSpellTag));
    }

    @Override
    public void deleteSpellTag(Long id) {
        spellTagRepository.deleteById(id);
    }

}
