package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellDamagetypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spelldamagetypes")
public class SpellDamagetypeController {

    private final SpellDamagetypeService spellDamagetypeService;

    public SpellDamagetypeController(SpellDamagetypeService spellDamagetypeService) {
        this.spellDamagetypeService = spellDamagetypeService;
    }

    @GetMapping
    public ResponseEntity<List<SpellDamagetypeDTO>> getAllSpellDamagetypes() {
        return ResponseEntity.ok(spellDamagetypeService.getAllSpellDamagetypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellDamagetypeDTO> getSpellDamagetypeById(@PathVariable Long id) {
        return ResponseEntity.ok(spellDamagetypeService.getSpellDamagetypeById(id));
    }

    @PostMapping
    public ResponseEntity<SpellDamagetypeDTO> createSpellDamagetype(@RequestBody SpellDamagetypeDTO spellDamagetypeDTO) {
        SpellDamagetypeDTO createdSpellDamagetype = spellDamagetypeService.createSpellDamagetype(spellDamagetypeDTO);

        return ResponseEntity
                .created(URI.create("/api/spelldamagetypes/" + createdSpellDamagetype.getId()))
                .body(createdSpellDamagetype);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellDamagetypeDTO> updateSpellDamagetype(@PathVariable Long id,
                                                                  @RequestBody SpellDamagetypeDTO spellDamagetypeDTO) {
        return ResponseEntity.ok(spellDamagetypeService.updateSpellDamagetype(id, spellDamagetypeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellDamagetype(@PathVariable Long id) {
        spellDamagetypeService.deleteSpellDamagetype(id);
        return ResponseEntity.noContent().build();
    }
}
