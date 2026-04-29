package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
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
    public ResponseEntity<List<SpellDTO>> getAllByNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllByNameContainingIgnoreCase(name));
    }

    @GetMapping("/search/by-rpg-class")
    public ResponseEntity<List<SpellDTO>> getAllByRpgClassNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllByRpgClassNameContainingIgnoreCase(name));
    }

    @GetMapping("/search/by-source")
    public ResponseEntity<List<SpellDTO>> getAllBySourceNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(spellService.getAllBySourceNameContainingIgnoreCase(name));
    }

}
