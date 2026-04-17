package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpellTtrpgMapper {

    @Mapping(source = "spell.id", target = "spellId")
    @Mapping(source = "ttrpg.id", target = "ttrpgId")
    SpellTtrpgDTO spellTtrpgToSpellTtrpgDTO(SpellTtrpg spellTtrpg);
    // no DTO->Entity here; resolve IDs in the service
}
