package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.EffectMapper;
import com.smashingwizards.thewizardsbook_backend.model.Effect;
import com.smashingwizards.thewizardsbook_backend.repository.EffectRepository;
import com.smashingwizards.thewizardsbook_backend.service.EffectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EffectServiceImpl implements EffectService {

    // ATTs
    private final EffectRepository effectRepository;
    private final EffectMapper effectMapper;

    // CONs
    public EffectServiceImpl(EffectRepository effectRepository, EffectMapper effectMapper) {
        this.effectRepository = effectRepository;
        this.effectMapper = effectMapper;
    }

    // CRUDs
    @Override
    public List<EffectDTO> getEffects() {
        return effectRepository.findAll()
                .stream()
                .map(effectMapper::effectToEffectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EffectDTO getEffectById(Long id) {
        return effectRepository.findById(id)
                .map(effectMapper::effectToEffectDTO)
                .orElseThrow(() -> new RuntimeException("Effect not found"));
    }

    @Override
    public EffectDTO createEffect(EffectDTO effectDTO) {
        return effectMapper.effectToEffectDTO(effectRepository
                .save(effectMapper.effectDTOToEffect(effectDTO)));
    }

    @Override
    public EffectDTO updateEffect(Long id, EffectDTO effectDTO) {
        Optional<Effect> optionalEffect = effectRepository.findById(id);
        if (!optionalEffect.isPresent()) {
            throw new RuntimeException("Effect not found");
        }
        Effect existingEffect = optionalEffect.get();
        existingEffect.setName(effectDTO.getName());
        existingEffect.setSubEffect(effectDTO.getSubEffect());

        return effectMapper.effectToEffectDTO(effectRepository.save(existingEffect));
    }

    @Override
    public void deleteEffect(Long id) {
        effectRepository.deleteById(id);
    }
}
