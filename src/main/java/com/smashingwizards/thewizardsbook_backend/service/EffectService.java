package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EffectService {
    List<EffectDTO> getEffects();
    EffectDTO getEffectById(Long id);
    EffectDTO createEffect(EffectDTO dto);
    EffectDTO updateEffect(Long id, EffectDTO effectDTO);
    void deleteEffect(Long id);
}
