package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellImageUrlMapper;
import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellImageUrl;
import com.smashingwizards.thewizardsbook_backend.repository.ImageUrlRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellImageUrlRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.service.SpellImageUrlService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellImageUrlServiceImpl implements SpellImageUrlService {

    private final SpellImageUrlRepository spellImageUrlRepository;
    private final SpellRepository spellRepository;
    private final ImageUrlRepository imageUrlRepository;
    private final SpellImageUrlMapper spellImageUrlMapper;

    // CONs
    public SpellImageUrlServiceImpl(SpellImageUrlRepository spellImageUrlRepository, SpellRepository spellRepository, ImageUrlRepository imageUrlRepository, SpellImageUrlMapper spellImageUrlMapper) {
        this.spellImageUrlRepository = spellImageUrlRepository;
        this.spellRepository = spellRepository;
        this.imageUrlRepository = imageUrlRepository;
        this.spellImageUrlMapper = spellImageUrlMapper;
    }

    // CRUDs
    @Override
    public List<SpellImageUrlDTO> getAllSpellImageUrls() {
        return spellImageUrlRepository.findAll()
                .stream()
                .map(spellImageUrlMapper::spellImageUrlToSpellImageUrlDTO)
                .toList();
    }

    public SpellImageUrlDTO getSpellImageUrlById(Long id) {
        return spellImageUrlRepository.findById(id)
                .map(spellImageUrlMapper::spellImageUrlToSpellImageUrlDTO)
                .orElseThrow(() -> new RuntimeException("SpellImageUrl not found"));
    }

    public SpellImageUrlDTO createSpellImageUrl(SpellImageUrlDTO spellImageUrlDTO) {
        Spell spellRef = spellRepository.getReferenceById(spellImageUrlDTO.getSpellId());
        ImageUrl imageUrlRef = imageUrlRepository.getReferenceById(spellImageUrlDTO.getImageUrlId());

        SpellImageUrl spellImageUrl = new SpellImageUrl(spellRef, imageUrlRef);
        return spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(spellImageUrlRepository.save(spellImageUrl));
    }

    public SpellImageUrlDTO updateSpellImageUrl(Long id, SpellImageUrlDTO spellImageUrlDTO) {
        SpellImageUrl existingSpellImageUrl = spellImageUrlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpellImageUrl not found"));

        Spell spellRef = spellRepository.getReferenceById(spellImageUrlDTO.getSpellId());
        ImageUrl imageUrlRef = imageUrlRepository.getReferenceById(spellImageUrlDTO.getImageUrlId());

        existingSpellImageUrl.setSpell(spellRef);
        existingSpellImageUrl.setImageUrl(imageUrlRef);

        return spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(spellImageUrlRepository.save(existingSpellImageUrl));
    }

    public void deleteSpellImageUrl(Long id) {
        spellImageUrlRepository.deleteById(id);
    }
}
