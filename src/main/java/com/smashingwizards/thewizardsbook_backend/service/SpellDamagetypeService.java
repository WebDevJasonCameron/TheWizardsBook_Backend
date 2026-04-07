package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDamagetypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpellDamagetypeService {
    List<SpellDamagetypeDTO> getSpellDamagetypes();
    SpellDamagetypeDTO getSpellDamagetypeById(Long id);
    SpellDamagetypeDTO createSpellDamagetype(SpellDamagetypeDTO spellDamagetypeDTO);
    SpellDamagetypeDTO updateSpellDamagetype(Long id, SpellDamagetypeDTO spellDamagetypeDTO);
    void deleteSpellDamagetype(Long id);
}
