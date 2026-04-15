package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;

import java.util.List;

public interface SourceService {
    List<SourceDTO> getAllSources();
    SourceDTO getSourceById(Long id);
    SourceDTO createSource(SourceDTO sourceDTO);
    SourceDTO updateSource(Long id, SourceDTO sourceDTO);
    void deleteSource(Long id);
}
