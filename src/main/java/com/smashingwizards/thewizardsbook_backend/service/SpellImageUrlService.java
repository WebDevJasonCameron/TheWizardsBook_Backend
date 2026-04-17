package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;

import java.util.List;

public interface SpellImageUrlService {
    List<SpellImageUrlDTO> getAllSpellImageUrls();
    SpellImageUrlDTO getSpellImageUrlById(Long id);
    SpellImageUrlDTO createSpellImageUrl(SpellImageUrlDTO spellImageUrlDTO);
    SpellImageUrlDTO updateSpellImageUrl(Long id, SpellImageUrlDTO spellImageUrlDTO);
    void deleteSpellImageUrl(Long id);
}
