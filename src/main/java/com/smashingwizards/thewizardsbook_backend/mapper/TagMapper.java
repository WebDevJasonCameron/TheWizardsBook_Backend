package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;
import com.smashingwizards.thewizardsbook_backend.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDTO tagToTagDTO(Tag tag);
    Tag tagDTOToTag(TagDTO tagDTO);
}
