package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

public interface SourceService {
    List<SourceDTO> getSources();
    SourceDTO getSourceById(Long id);
    SourceDTO createSource(SourceDTO sourceDto);
    SourceDTO updateSource(Long id, SourceDTO sourceDto);
    void deleteSource(Long id);

    // ADDs
    Page<SourceDTO> search(String nameContains,
                           Pageable pageable);
}
