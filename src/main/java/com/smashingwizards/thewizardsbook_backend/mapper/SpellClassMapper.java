package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbag_backend.model.SpellClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpellClassMapper {

    @Mapping(source = "spell.id", target = "spellId")
    @Mapping(source = "rpgClass.id", target = "rpgClassId")
    SpellClassDTO spellClassToSpellClassDTO(SpellClass spellClass);
    // no DTO->Entity here; resolve IDs in the service

}
