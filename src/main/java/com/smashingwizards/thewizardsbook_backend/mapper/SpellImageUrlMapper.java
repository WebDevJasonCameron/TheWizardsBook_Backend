package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.model.SpellImageUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpellImageUrlMapper {

    @Mapping(source = "spell.id", target = "spellId")
    @Mapping(source = "imageUrl.id", target = "imageUrlId")
    SpellImageUrlDTO spellImageUrlToSpellImageUrlDTO(SpellImageUrl spellImageUrl);
    // no DTO->Entity here; resolve IDs in the service
}

