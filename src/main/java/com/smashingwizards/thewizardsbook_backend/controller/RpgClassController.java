package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.service.RpgClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rpgclasses")
public class RpgClassController {

    private final RpgClassService rpgClassService;

    public RpgClassController(RpgClassService rpgClassService) {
        this.rpgClassService = rpgClassService;
    }

    @GetMapping
    public ResponseEntity<List<RpgClassDTO>> getAllRpgClasses() {
        return ResponseEntity.ok(rpgClassService.getAllRpgClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RpgClassDTO> getRpgClassById(@PathVariable Long id) {
        return ResponseEntity.ok(rpgClassService.getRpgClassById(id));
    }

    @PostMapping
    public ResponseEntity<RpgClassDTO> createRpgClass(@RequestBody RpgClassDTO rpgClassDTO) {
        RpgClassDTO createdRpgClass = rpgClassService.createRpgClass(rpgClassDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRpgClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RpgClassDTO> updateRpgClass(@PathVariable Long id, @RequestBody RpgClassDTO rpgClassDTO) {
        RpgClassDTO updatedRpgClass = rpgClassService.updateRpgClass(id, rpgClassDTO);
        return ResponseEntity.ok(updatedRpgClass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRpgClass(@PathVariable Long id) {
        rpgClassService.deleteRpgClass(id);
        return ResponseEntity.noContent().build();
    }
}
