package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.TtrpgDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TtrpgService {
    List<TtrpgDTO> getTtrpgs();
    TtrpgDTO getTtrpgById(Long id);
    TtrpgDTO createTtrpg(TtrpgDTO ttrpgDTO);
    TtrpgDTO updateTtrpg(Long id, TtrpgDTO ttrpgDTO);
    void deleteTtrpg(Long id);
}
