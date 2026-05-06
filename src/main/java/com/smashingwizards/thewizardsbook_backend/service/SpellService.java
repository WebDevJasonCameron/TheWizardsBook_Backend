package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.dto.SpellDetailsDTO;

import java.util.List;

public interface SpellService {
    List<SpellDTO> getAllSpells();
    SpellDTO getSpellById(Long id);
    SpellDTO createSpell(SpellDTO spellDTO);
    SpellDTO updateSpell(Long id, SpellDTO spellDTO);
    void deleteSpell(Long id);

    /** ADDs */
    List<SpellDTO> getAllByNameContainingIgnoreCase(String name);
    List<SpellDTO> getAllByRpgClassNameContainingIgnoreCase(String name);
    List<SpellDTO> getAllBySourceNameContainingIgnoreCase(String name);
    List<SpellDTO> getAllByTagNameContainingIgnoreCase(String name);
    List<SpellDTO> getAllByTtrpgNameContainingIgnoreCase(String name);

    SpellDetailsDTO getSpellDetailsById(Long id);

    List<SpellDTO> searchSpells(
            String name,
            List<Long> ttrpgIds,
            List<Long> classIds,
            List<String> levels
    );
}
