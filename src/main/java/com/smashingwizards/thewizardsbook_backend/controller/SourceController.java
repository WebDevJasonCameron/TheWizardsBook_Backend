package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.service.SourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<SourceDTO> getSources() {
        return sourceService.getSources();
    }

    @GetMapping("/{id}")
    public SourceDTO getSourceById(@PathVariable Long id) {
        return sourceService.getSourceById(id);
    }

    @PostMapping
    public SourceDTO createSource(@RequestBody SourceDTO sourceDTO) {
        return sourceService.createSource(sourceDTO);
    }

    @PutMapping("/{id}")
    public SourceDTO updateSource(@PathVariable Long id, @RequestBody SourceDTO sourceDTO) {
        return sourceService.updateSource(id, sourceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSource(@PathVariable Long id) {
        sourceService.deleteSource(id);
    }

    @GetMapping("/search")
    public Page<SourceDTO> searchSource(
            @RequestParam(name = "name.contains", required = false) String nameContains,
            Pageable pageable
    ) {
        return sourceService.search(nameContains, pageable);
    }
}