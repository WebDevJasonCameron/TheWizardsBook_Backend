package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.service.TtrpgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ttrpgs")
public class TtrpgController {


    private final TtrpgService ttrpgService;

    public TtrpgController(TtrpgService ttrpgService) {
        this.ttrpgService = ttrpgService;
    }

    @GetMapping
    public ResponseEntity<List<TtrpgDTO>> getAllTtrpgs() {
        return ResponseEntity.ok(ttrpgService.getAllTtrpgs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TtrpgDTO> getTtrpgById(@PathVariable Long id) {
        return ResponseEntity.ok(ttrpgService.getTtrpgById(id));
    }

    @PostMapping
    public ResponseEntity<TtrpgDTO> createTtrpg(@RequestBody TtrpgDTO ttrpgDTO) {
        TtrpgDTO createdTtrpg = ttrpgService.createTtrpg(ttrpgDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTtrpg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TtrpgDTO> updateTtrpg(@PathVariable Long id, @RequestBody TtrpgDTO ttrpgDTO) {
        TtrpgDTO updatedTtrpg = ttrpgService.updateTtrpg(id, ttrpgDTO);
        return ResponseEntity.ok(updatedTtrpg);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTtrpg(@PathVariable Long id) {
        ttrpgService.deleteTtrpg(id);
        return ResponseEntity.noContent().build();
    }

    /** ADDs */
    @GetMapping("/search")
    public ResponseEntity<List<TtrpgDTO>> getAllByNameContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(ttrpgService.getAllByNameContainingIgnoreCase(name));
    }
}
