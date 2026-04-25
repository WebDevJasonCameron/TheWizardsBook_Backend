package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getAllTypes() {
        return ResponseEntity.ok(typeService.getAllTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDTO> getTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(typeService.getTypeById(id));
    }

    @PostMapping
    public ResponseEntity<TypeDTO> createType(@RequestBody TypeDTO typeDTO) {
        TypeDTO createdType = typeService.createType(typeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeDTO> updateType(@PathVariable Long id, @RequestBody TypeDTO typeDTO) {
        TypeDTO updatedType = typeService.updateType(id, typeDTO);
        return ResponseEntity.ok(updatedType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }

    /** ADDs */
    @GetMapping("/search")
    public ResponseEntity<List<TypeDTO>> getAllByNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(typeService.getAllByNameContainingIgnoreCase(name));
    }
}
