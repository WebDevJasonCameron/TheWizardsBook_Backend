package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;

import java.util.List;

public interface DamagetypeService {
    List<DamagetypeDTO> getDamagetypes();
    DamagetypeDTO getDamagetypeById(Long id);
    DamagetypeDTO createDamagetype(DamagetypeDTO dto);
    DamagetypeDTO updateDamagetype(Long id, DamagetypeDTO dto);
    void deleteDamagetype(Long id);
}
