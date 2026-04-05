package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;

import java.util.List;

public interface SpellTagService {
    List<SpellTagDTO> getSpellTags();
    SpellTagDTO getSpellTagById(Long id);
    SpellTagDTO createSpellTag(SpellTagDTO spellTagDTO);
    SpellTagDTO updateSpellTag(Long id, SpellTagDTO spellTagDTO);
    void deleteSpellTag(Long id);
}
