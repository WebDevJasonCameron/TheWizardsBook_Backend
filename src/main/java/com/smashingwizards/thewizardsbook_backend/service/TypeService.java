package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TypeService {
    List<TypeDTO> getTypes();
    TypeDTO getTypeById(Long id);
    TypeDTO createType(TypeDTO typeDTO);
    TypeDTO updateType(Long id, TypeDTO typeDTO);
    void deleteType(Long id);

}
