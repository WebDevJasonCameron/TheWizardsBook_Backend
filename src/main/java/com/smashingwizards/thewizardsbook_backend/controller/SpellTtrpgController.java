package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellTtrpgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellttrpgs")
public class SpellTtrpgController {

    private final SpellTtrpgService spellTtrpgService;

    public SpellTtrpgController(SpellTtrpgService spellTtrpgService) {
        this.spellTtrpgService = spellTtrpgService;
    }

    @GetMapping
    public ResponseEntity<List<SpellTtrpgDTO>> getAllSpellTtrpgs() {
        return ResponseEntity.ok(spellTtrpgService.getAllSpellTtrpgs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellTtrpgDTO> getSpellTtrpgById(@PathVariable Long id) {
        return ResponseEntity.ok(spellTtrpgService.getSpellTtrpgById(id));
    }

    @PostMapping
    public ResponseEntity<SpellTtrpgDTO> createSpellTtrpg(@RequestBody SpellTtrpgDTO spellTtrpgDTO) {
        SpellTtrpgDTO createdSpellTtrpg = spellTtrpgService.createSpellTtrpg(spellTtrpgDTO);

        return ResponseEntity
                .created(URI.create("/api/spellttrpgs/" + createdSpellTtrpg.getId()))
                .body(createdSpellTtrpg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellTtrpgDTO> updateSpellTtrpg(@PathVariable Long id,
                                                                  @RequestBody SpellTtrpgDTO spellTtrpgDTO) {
        return ResponseEntity.ok(spellTtrpgService.updateSpellTtrpg(id, spellTtrpgDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellTtrpg(@PathVariable Long id) {
        spellTtrpgService.deleteSpellTtrpg(id);
        return ResponseEntity.noContent().build();
    }
}
