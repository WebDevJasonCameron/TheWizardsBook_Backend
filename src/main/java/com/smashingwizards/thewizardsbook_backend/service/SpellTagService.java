package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpellTagService {
    List<SpellTagDTO> getSpellTags();
    SpellTagDTO getSpellTagById(Long id);
    SpellTagDTO createSpellTag(SpellTagDTO spellTagDTO);
    SpellTagDTO updateSpellTag(Long id, SpellTagDTO spellTagDTO);
    void deleteSpellTag(Long id);
}
