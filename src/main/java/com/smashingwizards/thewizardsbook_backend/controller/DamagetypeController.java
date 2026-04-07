package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.service.DamagetypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/damagetypes")
public class DamagetypeController {

    // ATTs
    private final DamagetypeService damagetypeService;

    // CONs
    public DamagetypeController(DamagetypeService damagetypeService) {
        this.damagetypeService = damagetypeService;
    }

    // CRUDs
    @GetMapping()
    public List<DamagetypeDTO> getDamagetypes() {
        return damagetypeService.getDamagetypes();
    }

    @GetMapping("/{id}")
    public DamagetypeDTO getDamagetypeById(@PathVariable Long id) {
        return damagetypeService.getDamagetypeById(id);
    }

    @PostMapping
    public DamagetypeDTO createDamagetype(@RequestBody DamagetypeDTO damagetypeDTO) {
        return damagetypeService.createDamagetype(damagetypeDTO);
    }

    @PutMapping("/{id}")
    public DamagetypeDTO updateDamagetype(@PathVariable Long id, @RequestBody DamagetypeDTO damagetypeDTO) {
        return damagetypeService.updateDamagetype(id, damagetypeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDamagetype(@PathVariable Long id) {
        damagetypeService.deleteDamagetype(id);
    }

}
