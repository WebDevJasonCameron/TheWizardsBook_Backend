package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbag_backend.model.Type;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    TypeDTO typeToTypeDTO(Type type);
    Type typeDTOToType(TypeDTO typeDTO);
}
