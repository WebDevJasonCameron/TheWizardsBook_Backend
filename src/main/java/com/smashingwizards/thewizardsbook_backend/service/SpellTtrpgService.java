package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;

import java.util.List;

public interface SpellTtrpgService {
    List<SpellTtrpgDTO> getAllSpellTtrpgs();
    SpellTtrpgDTO getSpellTtrpgById(Long id);
    SpellTtrpgDTO createSpellTtrpg(SpellTtrpgDTO spellTtrpgDTO);
    SpellTtrpgDTO updateSpellTtrpg(Long id, SpellTtrpgDTO spellTtrpgDTO);
    void deleteSpellTtrpg(Long id);
}
