package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbag_backend.model.RpgClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RpgClassMapper {

    RpgClassDTO rpgClassToRpgClassDTO(RpgClass rpgClass);
    RpgClass rpgClassDTOToRpgClass(RpgClassDTO rpgClassDTO);
}
