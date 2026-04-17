package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellImageUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellimageurls")
public class SpellImageUrlController {


    private final SpellImageUrlService spellImageUrlService;

    public SpellImageUrlController(SpellImageUrlService spellImageUrlService) {
        this.spellImageUrlService = spellImageUrlService;
    }

    @GetMapping
    public ResponseEntity<List<SpellImageUrlDTO>> getAllSpellImageUrls() {
        return ResponseEntity.ok(spellImageUrlService.getAllSpellImageUrls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellImageUrlDTO> getSpellImageUrlById(@PathVariable Long id) {
        return ResponseEntity.ok(spellImageUrlService.getSpellImageUrlById(id));
    }

    @PostMapping
    public ResponseEntity<SpellImageUrlDTO> createSpellImageUrl(@RequestBody SpellImageUrlDTO spellImageUrlDTO) {
        SpellImageUrlDTO createdSpellImageUrl = spellImageUrlService.createSpellImageUrl(spellImageUrlDTO);

        return ResponseEntity
                .created(URI.create("/api/spellimageurls/" + createdSpellImageUrl.getId()))
                .body(createdSpellImageUrl);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellImageUrlDTO> updateSpellImageUrl(@PathVariable Long id,
                                                                  @RequestBody SpellImageUrlDTO spellImageUrlDTO) {
        return ResponseEntity.ok(spellImageUrlService.updateSpellImageUrl(id, spellImageUrlDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellImageUrl(@PathVariable Long id) {
        spellImageUrlService.deleteSpellImageUrl(id);
        return ResponseEntity.noContent().build();
    }
}
