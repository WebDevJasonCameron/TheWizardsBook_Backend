package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;

import java.util.List;

public interface SpellClassService {
    List<SpellClassDTO> getSpellClasses();
    SpellClassDTO getSpellClassById(Long id);
    SpellClassDTO createSpellClass(SpellClassDTO spellClassDTO);
    SpellClassDTO updateSpellClass(Long id, SpellClassDTO spellClassDTO);
    void deleteSpellClass(Long id);
}
