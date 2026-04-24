package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.service.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public ResponseEntity<List<SourceDTO>> getAllSources() {
        return ResponseEntity.ok(sourceService.getAllSources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SourceDTO> getSourceById(@PathVariable Long id) {
        return ResponseEntity.ok(sourceService.getSourceById(id));
    }

    @PostMapping
    public ResponseEntity<SourceDTO> createSource(@RequestBody SourceDTO sourceDTO) {
        SourceDTO createdSource = sourceService.createSource(sourceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SourceDTO> updateSource(@PathVariable Long id, @RequestBody SourceDTO sourceDTO) {
        SourceDTO updatedSource = sourceService.updateSource(id, sourceDTO);
        return ResponseEntity.ok(updatedSource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        sourceService.deleteSource(id);
        return ResponseEntity.noContent().build();
    }

    /** Adds */
    @GetMapping("/search")
    public ResponseEntity<List<SourceDTO>> getAllSourcesByName(@RequestParam String name) {
        return ResponseEntity.ok(sourceService.getAllByNameContainingIgnoreCase(name));
    }
}
