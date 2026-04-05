package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellDamagetypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/spellDamagetypes")
public class SpellDamagetypeController {

    // ATTs
    private final SpellDamagetypeService spellDamagetypeService;

    // CONs
    public SpellDamagetypeController(SpellDamagetypeService spellDamagetypeService) {
        this.spellDamagetypeService = spellDamagetypeService;
    }

    // CRUDs
    @GetMapping
    public Iterable<SpellDamagetypeDTO> getSpellDamagetypes() {
        return spellDamagetypeService.getSpellDamagetypes();
    }

    @GetMapping("/{id}")
    public SpellDamagetypeDTO getSpellDamagetypeById(@PathVariable Long id) {
        return spellDamagetypeService.getSpellDamagetypeById(id);
    }

    @PostMapping
    public ResponseEntity<SpellDamagetypeDTO> createSpellDamagetype(@RequestBody SpellDamagetypeDTO spellDamagetypeDTO) {
        SpellDamagetypeDTO createdSpellDamagetype = spellDamagetypeService.createSpellDamagetype(spellDamagetypeDTO);
        // If your LikeDTO carries 'id', build a location header

        return ResponseEntity
                .created(URI.create("/api/spellDamagetypes" + createdSpellDamagetype.getId()))
                .body(createdSpellDamagetype);
    }

    @PutMapping("/{id}")
    public SpellDamagetypeDTO updateSpellDamagetype(@PathVariable Long id, @RequestBody SpellDamagetypeDTO spellDamagetypeDTO) {
        return spellDamagetypeService.updateSpellDamagetype(id, spellDamagetypeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSpellDamagetype(@PathVariable Long id) {}


}
