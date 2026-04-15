package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;

import java.util.List;

public interface SpellService {
    List<SpellDTO> getAllSpells();
    SpellDTO getSpellById(Long id);
    SpellDTO createSpell(SpellDTO spellDTO);
    SpellDTO updateSpell(Long id, SpellDTO spellDTO);
    void deleteSpell(Long id);
}
