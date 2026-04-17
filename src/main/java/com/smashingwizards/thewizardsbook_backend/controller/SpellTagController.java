package com.smashingwizards.thewizardsbook_backend.controller;


import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spelltags")
public class SpellTagController {


    private final SpellTagService spellTagService;

    public SpellTagController(SpellTagService spellTagService) {
        this.spellTagService = spellTagService;
    }

    @GetMapping
    public ResponseEntity<List<SpellTagDTO>> getAllSpellTags() {
        return ResponseEntity.ok(spellTagService.getAllSpellTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellTagDTO> getSpellTagById(@PathVariable Long id) {
        return ResponseEntity.ok(spellTagService.getSpellTagById(id));
    }

    @PostMapping
    public ResponseEntity<SpellTagDTO> createSpellTag(@RequestBody SpellTagDTO spellTagDTO) {
        SpellTagDTO createdSpellTag = spellTagService.createSpellTag(spellTagDTO);

        return ResponseEntity
                .created(URI.create("/api/spelltags/" + createdSpellTag.getId()))
                .body(createdSpellTag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellTagDTO> updateSpellTag(@PathVariable Long id,
                                                                  @RequestBody SpellTagDTO spellTagDTO) {
        return ResponseEntity.ok(spellTagService.updateSpellTag(id, spellTagDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpellTag(@PathVariable Long id) {
        spellTagService.deleteSpellTag(id);
        return ResponseEntity.noContent().build();
    }
}
