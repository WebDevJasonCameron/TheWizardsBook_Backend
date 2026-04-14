package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RpgClassMapper {

    RpgClassDTO rpgClassToRpgClassDTO(RpgClass rpgClass);
    RpgClass rpgClassDTOToRpgClass(RpgClassDTO rpgClassDTO);
}
