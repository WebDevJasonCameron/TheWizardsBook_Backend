package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;

import java.util.List;

public interface EffectService {
    List<EffectDTO> getAllEffects();
    EffectDTO getEffectById(Long id);
    EffectDTO createEffect(EffectDTO effectDTO);
    EffectDTO updateEffect(Long id, EffectDTO effectDTO);
    void deleteEffect(Long id);
}
