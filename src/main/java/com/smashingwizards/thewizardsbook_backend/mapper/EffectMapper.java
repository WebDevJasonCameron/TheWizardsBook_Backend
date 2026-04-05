package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbag_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbag_backend.model.Effect;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EffectMapper {

    EffectDTO  effectToEffectDTO(Effect effect);
    Effect effectDTOToEffect(EffectDTO effectDTO);
}
