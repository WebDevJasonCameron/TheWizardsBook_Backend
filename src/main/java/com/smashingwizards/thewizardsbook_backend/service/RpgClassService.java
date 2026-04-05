package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;

import java.util.List;

public interface RpgClassService {
    List<RpgClassDTO> getRpgClasses();
    RpgClassDTO getRpgClassById(Long id);
    RpgClassDTO createRpgClass(RpgClassDTO rpgClassDto);
    RpgClassDTO updateRpgClass(Long id, RpgClassDTO rpgClassDto);
    void deleteRpgClass(Long id);
}
