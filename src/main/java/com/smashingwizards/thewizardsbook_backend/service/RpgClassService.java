package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;

import java.util.List;

public interface RpgClassService {
    List<RpgClassDTO> getAllRpgClasses();
    RpgClassDTO getRpgClassById(Long id);
    RpgClassDTO createRpgClass(RpgClassDTO rpgClassDTO);
    RpgClassDTO updateRpgClass(Long id, RpgClassDTO rpgClassDTO);
    void deleteRpgClass(Long id);
}
