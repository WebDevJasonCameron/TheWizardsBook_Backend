package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellSourceDTO;
import com.smashingwizards.thewizardsbook_backend.model.SpellSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpellSourceMapper {

    @Mapping(source = "spell.id", target = "spellId")
    @Mapping(source = "source.id", target = "sourceId")
    SpellSourceDTO spellSourceToSpellSourceDTO(SpellSource spellSource);
    // no DTO->Entity here; resolve IDs in the service
}
