package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DamagetypeService {
    List<DamagetypeDTO> getDamagetypes();
    DamagetypeDTO getDamagetypeById(Long id);
    DamagetypeDTO createDamagetype(DamagetypeDTO dto);
    DamagetypeDTO updateDamagetype(Long id, DamagetypeDTO dto);
    void deleteDamagetype(Long id);
}
