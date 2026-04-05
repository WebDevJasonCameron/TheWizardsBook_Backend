package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.SpellDamagetypeDTO;
import com.smashingwizards.thewizardsbag_backend.model.SpellDamagetype;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpellDamagetypeMapper {

    @Mapping(source = "spell.id", target = "spellId")
    @Mapping(source = "damagetype.id", target = "damagetypeId")
    SpellDamagetypeDTO spellDamagetypeToSpellDamagetypeDTO(SpellDamagetype spellDamagetype);
}
