package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbook_backend.service.EffectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/effects")
public class EffectController {

    private final EffectService effectService;

    public EffectController(EffectService effectService) {
        this.effectService = effectService;
    }

    @GetMapping
    public ResponseEntity<List<EffectDTO>> getAllEffects() {
        return ResponseEntity.ok(effectService.getAllEffects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EffectDTO> getEffectById(@PathVariable Long id) {
        return ResponseEntity.ok(effectService.getEffectById(id));
    }

    @PostMapping
    public ResponseEntity<EffectDTO> createEffect(@RequestBody EffectDTO effectDTO) {
        EffectDTO createdEffect = effectService.createEffect(effectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEffect);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EffectDTO> updateEffect(@PathVariable Long id, @RequestBody EffectDTO effectDTO) {
        EffectDTO updatedEffect = effectService.updateEffect(id, effectDTO);
        return ResponseEntity.ok(updatedEffect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEffect(@PathVariable Long id) {
        effectService.deleteEffect(id);
        return ResponseEntity.noContent().build();
    }
}
