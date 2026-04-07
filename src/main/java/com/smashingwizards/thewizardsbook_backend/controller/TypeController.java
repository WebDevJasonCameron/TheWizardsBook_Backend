package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    // ATTs
    private final TypeService typeService;

    // CONs
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    // CRUDs
    @GetMapping
    public List<TypeDTO> getTypes() {
        return typeService.getTypes();
    }

    @GetMapping("/{id}")
    public TypeDTO getTypeById(@PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @PostMapping
    public TypeDTO createType(@RequestBody TypeDTO typeDTO) {
        return typeService.createType(typeDTO);
    }

    @PutMapping("/{id}")
    public TypeDTO updateType(@PathVariable Long id, @RequestBody TypeDTO typeDTO) {
        return typeService.updateType(id, typeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }

}
