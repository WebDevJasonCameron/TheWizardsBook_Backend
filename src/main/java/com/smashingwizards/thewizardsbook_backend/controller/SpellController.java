package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.dto.SpellDetailsDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spells")
public class SpellController {

    private final SpellService spellService;

    public SpellController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GetMapping
    public ResponseEntity<List<SpellDTO>> getAllSpells() {
        return ResponseEntity.ok(spellService.getAllSpells());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellDTO> getSpellById(@PathVariable Long id) {
        return ResponseEntity.ok(spellService.getSpellById(id));
    }

    @PostMapping
    public ResponseEntity<SpellDTO> createSpell(@RequestBody SpellDTO spellDTO) {
        SpellDTO createdSpell = spellService.createSpell(spellDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpell);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellDTO> updateSpell(@PathVariable Long id, @RequestBody SpellDTO spellDTO) {
        SpellDTO updatedSpell = spellService.updateSpell(id, spellDTO);
        return ResponseEntity.ok(updatedSpell);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpell(@PathVariable Long id) {
        spellService.deleteSpell(id);
        return ResponseEntity.noContent().build();
    }

    /** ADDs */
    @GetMapping("/search")
    public ResponseEntity<List<SpellDTO>> searchSpells(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Long> ttrpgIds,
            @RequestParam(required = false) List<Long> classIds,
            @RequestParam(required = false) List<String> levels
    ) {
        return ResponseEntity.ok(
                spellService.searchSpells(name, ttrpgIds, classIds, levels)
        );
    }

    @GetMapping("/search/by-rpg-class")
    public ResponseEntity<List<SpellDTO>> getAllByRpgClassNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllByRpgClassNameContainingIgnoreCase(name));
    }

    @GetMapping("/search/by-source")
    public ResponseEntity<List<SpellDTO>> getAllBySourceNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllBySourceNameContainingIgnoreCase(name));
    }

    @GetMapping("/search/by-tag")
    public ResponseEntity<List<SpellDTO>> getAllByTagNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllByTagNameContainingIgnoreCase(name));
    }

    @GetMapping("/search/by-ttrpg")
    public ResponseEntity<List<SpellDTO>> getAllByTtrpgNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllByTtrpgNameContainingIgnoreCase(name));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<SpellDetailsDTO> getSpellDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(spellService.getSpellDetailsById(id));
    }

}
