package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import com.smashingwizards.thewizardsbook_backend.model.Condition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConditionMapper {

    ConditionDTO conditionToConditionDTO(Condition condition);
    Condition conditionDTOToCondition(ConditionDTO conditionDTO);
}
