package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.DamagetypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/damagetypes")
public class DamagetypeController {

    private final DamagetypeService damagetypeService;

    public DamagetypeController(DamagetypeService damagetypeService) {
        this.damagetypeService = damagetypeService;
    }

    @GetMapping
    public ResponseEntity<List<DamagetypeDTO>> getAllDamagetypes() {
        return ResponseEntity.ok(damagetypeService.getAllDamagetypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DamagetypeDTO> getDamagetypeById(@PathVariable Long id) {
        return ResponseEntity.ok(damagetypeService.getDamagetypeById(id));
    }

    @PostMapping
    public ResponseEntity<DamagetypeDTO> createDamagetype(@RequestBody DamagetypeDTO damagetypeDTO) {
        DamagetypeDTO createdDamagetype = damagetypeService.createDamagetype(damagetypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDamagetype);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DamagetypeDTO> updateDamagetype(@PathVariable Long id, @RequestBody DamagetypeDTO damagetypeDTO) {
        DamagetypeDTO updatedDamagetype = damagetypeService.updateDamagetype(id, damagetypeDTO);
        return ResponseEntity.ok(updatedDamagetype);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDamagetype(@PathVariable Long id) {
        damagetypeService.deleteDamagetype(id);
        return ResponseEntity.noContent().build();
    }
}
