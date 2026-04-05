package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbag_backend.model.Spell;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpellMapper {

    SpellDTO spellToSpellDTO(Spell spell);
    Spell spellDTOToSpell(SpellDTO spellDTO);
}
