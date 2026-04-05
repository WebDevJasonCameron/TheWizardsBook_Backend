package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbook_backend.service.EffectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/effects")
public class EffectController {

    // ATTs
    private final EffectService effectService;

    // CONs
    public EffectController(EffectService effectService) {
        this.effectService = effectService;
    }

    // CRUDs
    @GetMapping()
    public List<EffectDTO> getEffects() {
        return effectService.getEffects();
    }

    @GetMapping("/{id}")
    public EffectDTO getEffectById(@PathVariable Long id) {
        return effectService.getEffectById(id);
    }

    @PostMapping
    public EffectDTO createEffect(@RequestBody EffectDTO effectDTO) {
        return effectService.createEffect(effectDTO);
    }

    @PutMapping("/{id}")
    public EffectDTO updateEffect(@PathVariable Long id, @RequestBody EffectDTO effectDTO) {
        return effectService.updateEffect(id, effectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEffect(@PathVariable Long id) {
        effectService.deleteEffect(id);
    }
}
