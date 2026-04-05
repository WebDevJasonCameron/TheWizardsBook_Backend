package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.service.SourceService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {

    // ATTs
    private final SourceService sourceService;
    private final com.smashingwizards.thewizardsbook_backend.controller.SpringDataWebProperties SpringDataWebProperties;

    // CONs
    public SourceController(SourceService sourceService, com.smashingwizards.thewizardsbook_backend.controller.SpringDataWebProperties SpringDataWebProperties) {
        this.sourceService = sourceService;
        this.SpringDataWebProperties = SpringDataWebProperties;
    }
    // CRUDs
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

    // ADDs
    @GetMapping("/search")
    public Page<SourceDTO> searchSource(
            @RequestParam(name = "name.contains", required = false) String nameContains,
            SpringDataWebProperties.Pageable pageable
    ) {
        return sourceService.search(nameContains, Pageable.unpaged());
    }


}
