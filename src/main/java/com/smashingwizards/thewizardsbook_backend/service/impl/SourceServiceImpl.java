package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SourceMapper;
import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.repository.SourceRepository;
import com.smashingwizards.thewizardsbook_backend.service.SourceService;
import com.smashingwizards.thewizardsbook_backend.spec.SourceSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {

    // ATTs
    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;

    // CONs
    public SourceServiceImpl(SourceRepository sourceRepository, SourceMapper sourceMapper) {
        this.sourceRepository = sourceRepository;
        this.sourceMapper = sourceMapper;
    }

    // CRUDs
    @Override
    public List<SourceDTO> getSources() {
        return sourceRepository.findAll()
                .stream()
                .map(sourceMapper::sourceToSourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SourceDTO getSourceById(Long id) {
        return sourceRepository.findById(id)
                .map(sourceMapper::sourceToSourceDTO)
                .orElseThrow(() -> new RuntimeException("Source not found"));
    }

    @Override
    public SourceDTO createSource(SourceDTO sourceDTO) {
        return sourceMapper.sourceToSourceDTO(sourceRepository
                .save(sourceMapper.sourceDTOToSource(sourceDTO)));
    }

    @Override
    public SourceDTO updateSource(Long id, SourceDTO sourceDTO) {
        Optional<Source> optionalSource = sourceRepository.findById(id);
        if (!optionalSource.isPresent()) {
            throw new RuntimeException("Source not found");
        }
        Source existingSource = optionalSource.get();

        existingSource.setName(sourceDTO.getName());
        existingSource.setPublisher(sourceDTO.getPublisher());
        existingSource.setPublisher(sourceDTO.getPublisher());
        existingSource.setTtrpg(sourceDTO.getTtrpg());

        return sourceMapper.sourceToSourceDTO(sourceRepository.save(existingSource));
    }

    @Override
    public void deleteSource(Long id) {
        sourceRepository.deleteById(id);
    }

    // ADDs
    @Override
    @Transactional(readOnly = true)
    public Page<SourceDTO> search(String nameContains,
                                Pageable pageable) {
        Specification<Source> spec = (root, cq, cb) -> cb.conjunction();

        if (nameContains != null && !nameContains.isBlank()) {
            spec = spec.and(SourceSpecifications.nameContains(nameContains));
        }

        return sourceRepository.findAll(spec, pageable).map(sourceMapper::sourceToSourceDTO);

    }


}
