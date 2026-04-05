package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.TtrpgDTO;
import com.smashingwizards.thewizardsbag_backend.model.Ttrpg;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TtrpgMapper {

    TtrpgDTO ttrpgToTtrpgDTO(Ttrpg ttrpg);
    Ttrpg ttrpgDTOToTtrpg(TtrpgDTO ttrpgDTO);
}
