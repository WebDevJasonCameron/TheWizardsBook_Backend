package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.RpgClassMapper;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import com.smashingwizards.thewizardsbook_backend.repository.RpgClassRepository;
import com.smashingwizards.thewizardsbook_backend.service.RpgClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RpgClassServiceImpl implements RpgClassService {

    // ATTs
    private final RpgClassRepository rpgClassRepository;
    private final RpgClassMapper rpgClassMapper;

    // CONs
    public RpgClassServiceImpl(RpgClassRepository rpgClassRepository, RpgClassMapper rpgClassMapper) {
        this.rpgClassRepository = rpgClassRepository;
        this.rpgClassMapper = rpgClassMapper;
    }

    // CRUDs
    @Override
    public List<RpgClassDTO> getRpgClasses() {
        return rpgClassRepository.findAll()
                .stream()
                .map(rpgClassMapper::rpgClassToRpgClassDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public RpgClassDTO getRpgClassById(Long id) {
        return rpgClassRepository.findById(id)
                .map(rpgClassMapper::rpgClassToRpgClassDTO)
                .orElseThrow(() -> new RuntimeException("RpgClass not found"));
    }

    @Override
    public RpgClassDTO createRpgClass(RpgClassDTO rpgClassDTO) {
        return rpgClassMapper.rpgClassToRpgClassDTO(rpgClassRepository
                .save(rpgClassMapper.rpgClassDTOToRpgClass(rpgClassDTO)));
    }

    @Override
    public RpgClassDTO updateRpgClass(Long id, RpgClassDTO rpgClassDTO) {
        Optional<RpgClass> optionalRpgClass = rpgClassRepository.findById(id);
        if (!optionalRpgClass.isPresent()) {
            throw new RuntimeException("RpgClass not found");
        }
        RpgClass existingRpgClass = optionalRpgClass.get();
        existingRpgClass.setName(rpgClassDTO.getName());
        existingRpgClass.setSubClassName(rpgClassDTO.getSubClassName());
        existingRpgClass.setDescription(rpgClassDTO.getDescription());

        return rpgClassMapper.rpgClassToRpgClassDTO(rpgClassRepository.save(existingRpgClass));
    }

    @Override
    public void deleteRpgClass(Long id) {
        rpgClassRepository.deleteById(id);
    }

}
