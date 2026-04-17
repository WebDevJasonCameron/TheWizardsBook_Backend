package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellSourceDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellSourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellsources")
public class SpellSourceController {

    private final SpellSourceService spellSourceService;

    public SpellSourceController(SpellSourceService spellSourceService) {
        this.spellSourceService = spellSourceService;
    }

    @GetMapping
    public ResponseEntity<List<SpellSourceDTO>> getAllSpellSources() {
        return ResponseEntity.ok(spellSourceService.getAllSpellSources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellSourceDTO> getSpellSourceById(@PathVariable Long id) {
        return ResponseEntity.ok(spellSourceService.getSpellSourceById(id));
    }

    @PostMapping
    public ResponseEntity<SpellSourceDTO> createSpellSource(@RequestBody SpellSourceDTO spellSourceDTO) {
        SpellSourceDTO createdSpellSource = spellSourceService.createSpellSource(spellSourceDTO);

        return ResponseEntity
                .created(URI.create("/api/spellsources/" + createdSpellSource.getId()))
                .body(createdSpellSource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellSourceDTO> updateSpellSource(@PathVariable Long id,
                                                                  @RequestBody SpellSourceDTO spellSourceDTO) {
        return ResponseEntity.ok(spellSourceService.updateSpellSource(id, spellSourceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellSource(@PathVariable Long id) {
        spellSourceService.deleteSpellSource(id);
        return ResponseEntity.noContent().build();
    }
}
