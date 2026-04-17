package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellSourceDTO;

import java.util.List;

public interface SpellSourceService {
    List<SpellSourceDTO> getAllSpellSources();
    SpellSourceDTO getSpellSourceById(Long id);
    SpellSourceDTO createSpellSource(SpellSourceDTO spellSourceDTO);
    SpellSourceDTO updateSpellSource(Long id, SpellSourceDTO spellSourceDTO);
    void deleteSpellSource(Long id);
}
