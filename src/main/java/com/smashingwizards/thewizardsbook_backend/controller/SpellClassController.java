package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellclasses")
public class SpellClassController {

    private final SpellClassService spellClassService;

    public SpellClassController(SpellClassService spellClassService) {
        this.spellClassService = spellClassService;
    }

    @GetMapping
    public ResponseEntity<List<SpellClassDTO>> getAllSpellClasses() {
        return ResponseEntity.ok(spellClassService.getAllSpellClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellClassDTO> getSpellClassById(@PathVariable Long id) {
        return ResponseEntity.ok(spellClassService.getSpellClassById(id));
    }

    @PostMapping
    public ResponseEntity<SpellClassDTO> createSpellClass(@RequestBody SpellClassDTO spellClassDTO) {
        SpellClassDTO createdSpellClass = spellClassService.createSpellClass(spellClassDTO);

        return ResponseEntity
                .created(URI.create("/api/spellclasses/" + createdSpellClass.getId()))
                .body(createdSpellClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellClassDTO> updateSpellClass(@PathVariable Long id,
                                                                  @RequestBody SpellClassDTO spellClassDTO) {
        return ResponseEntity.ok(spellClassService.updateSpellClass(id, spellClassDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellClass(@PathVariable Long id) {
        spellClassService.deleteSpellClass(id);
        return ResponseEntity.noContent().build();
    }
}
