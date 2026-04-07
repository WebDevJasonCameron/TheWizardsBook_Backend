package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.SpellTagDTO;
import com.smashingwizards.thewizardsbag_backend.model.SpellTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpellTagMapper {

    @Mapping(source = "spell.id", target = "spellId")
    @Mapping(source = "tag.id", target = "tagId")
    SpellTagDTO spellTagToSpellTagDTO(SpellTag spellTag);
    // no DTO->Entity here; resolve IDs in the service

}
