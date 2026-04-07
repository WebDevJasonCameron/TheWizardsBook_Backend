package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.service.RpgClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rpgClasses")
public class RpgClassController {

    // ATTs
    private final RpgClassService rpgClassService;

    // CONs
    public RpgClassController(RpgClassService rpgClassService) {
        this.rpgClassService = rpgClassService;
    }

    // CRUDs
    @GetMapping
    public List<RpgClassDTO> getRpgClasses() {
        return rpgClassService.getRpgClasses();
    }

    @GetMapping("/{id}")
    public RpgClassDTO getRpgClassById(@PathVariable Long id) {
        return rpgClassService.getRpgClassById(id);
    }

    @PostMapping
    public RpgClassDTO createRpgClass(@RequestBody RpgClassDTO rpgClassDTO) {
        return rpgClassService.createRpgClass(rpgClassDTO);
    }

    @PutMapping("/{id}")
    public RpgClassDTO updateRpgClass(@PathVariable Long id, @RequestBody RpgClassDTO rpgClassDTO) {
        return rpgClassService.updateRpgClass(id, rpgClassDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRpgClass(@PathVariable Long id) {
        rpgClassService.deleteRpgClass(id);
    }

}
