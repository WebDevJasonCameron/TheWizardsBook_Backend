package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.ImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageUrlMapper {

    ImageUrlDTO imageUrlToImageUrlDTO(ImageUrl imageUrl);
    ImageUrl imageUrlDTOToImageUrl(ImageUrlDTO imageUrlDTO);
}
