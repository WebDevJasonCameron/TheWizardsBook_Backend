package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DamagetypeMapper {

    DamagetypeDTO damagetypeToDamagetypeDTO(Damagetype damagetype);
    Damagetype damagetypeDTOToDamagetype(DamagetypeDTO damagetypeDTO);
}
