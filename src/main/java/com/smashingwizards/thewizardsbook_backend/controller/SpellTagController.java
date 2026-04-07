package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/spellTags")
public class SpellTagController {

    // ATTs
    private final SpellTagService spellTagService;

    // CONs
    public SpellTagController(SpellTagService spellTagService) {
        this.spellTagService = spellTagService;
    }

    // CRUDs
    @GetMapping
    public Iterable<SpellTagDTO> getSpellTags() {
        return spellTagService.getSpellTags();
    }

    @GetMapping("/{id}")
    public SpellTagDTO getSpellTagById(@PathVariable Long id) {
        return spellTagService.getSpellTagById(id);
    }

    @PostMapping
    public ResponseEntity<SpellTagDTO> createSpellTag(@RequestBody SpellTagDTO spellTagDTO) {
        SpellTagDTO createdSpellTag = spellTagService.createSpellTag(spellTagDTO);
        // If your LikeDTO carries 'id', build a location header

        return ResponseEntity
                .created(URI.create("/api/spellTags" + createdSpellTag.getId()))
                .body(createdSpellTag);
    }

    @PutMapping("/{id}")
    public SpellTagDTO updateSpellTag(@PathVariable Long id, @RequestBody SpellTagDTO spellTagDTO) {
        return spellTagService.updateSpellTag(id, spellTagDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSpellTag(@PathVariable Long id) {
        spellTagService.deleteSpellTag(id);
    }

}
