package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpellService {
    List<SpellDTO> getSpells();
    SpellDTO getSpellById(Long id);
    SpellDTO createSpell(SpellDTO spellDto);
    SpellDTO updateSpell(Long id, SpellDTO spellDto);
    void deleteSpell(Long id);

    // ADDs
    Page<SpellDTO> search(String nameContains,
                          String nameNotContains,
                          Long ttrpg,
                          String level,
                          Boolean concentration,
                          Boolean ritual,
                          String school,
                          Long spellId,
                          Pageable pageable);
}
