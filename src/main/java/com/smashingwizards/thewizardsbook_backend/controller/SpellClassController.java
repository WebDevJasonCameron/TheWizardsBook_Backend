package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbook_backend.service.SpellClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/spellClasses")
public class SpellClassController {

    // ATTs
    private final SpellClassService spellClassService;

    // CONs
    public SpellClassController(SpellClassService spellClassService) {
        this.spellClassService = spellClassService;
    }

    // CRUDs
    @GetMapping
    public List<SpellClassDTO> getSpellClasses() {
        return spellClassService.getSpellClasses();
    }

    @GetMapping("/{id}")
    public SpellClassDTO getSpellClassById(@PathVariable Long id) {
        return spellClassService.getSpellClassById(id);
    }

    @PostMapping
    public ResponseEntity<SpellClassDTO> createSpellClass(@RequestBody SpellClassDTO spellClassDTO) {
        SpellClassDTO createdSpellClass = spellClassService.createSpellClass(spellClassDTO);
        // If your LikeDTO carries 'id', build a location header
        return ResponseEntity
                .created(URI.create("/api/spellClasses/" + createdSpellClass.getId()))
                .body(createdSpellClass);
    }

}
