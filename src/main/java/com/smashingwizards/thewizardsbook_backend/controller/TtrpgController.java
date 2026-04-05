package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.service.TtrpgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ttrpgs")
public class TtrpgController {

    // ATTs
    private final TtrpgService ttrpgService;

    // CONs
    @Autowired
    public TtrpgController(TtrpgService ttrpgService) {
        this.ttrpgService = ttrpgService;
    }

    // CRUDs
    @RequestMapping
    public Object getTtrpgs() {
        return ttrpgService.getTtrpgs();
    }

    @GetMapping("/{id}")
    public Object getTtrpgById(@PathVariable Long id) {
        return ttrpgService.getTtrpgById(id);
    }

    @PostMapping
    public TtrpgDTO createTtrpg(@RequestBody TtrpgDTO ttrpgDTO) {
        return ttrpgService.createTtrpg(ttrpgDTO);
    }

    @PutMapping("/{id}")
    public TtrpgDTO updateTtrpg(@PathVariable Long id, @RequestBody TtrpgDTO ttrpgDTO) {
        return ttrpgService.updateTtrpg(id, ttrpgDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTtrpg(@PathVariable Long id) {
        ttrpgService.deleteTtrpg(id);
    }

}
