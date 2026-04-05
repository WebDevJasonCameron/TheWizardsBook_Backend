package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;
import com.smashingwizards.thewizardsbook_backend.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagsController {

    // ATTs
    private final TagService tagService;

    // CONs
    public TagsController(TagService tagService) {
        this.tagService = tagService;
    }
    // CRUDs
    @GetMapping
    public List<TagDTO> getTags() {
        return tagService.getTags();
    }

    @GetMapping("/{id}")
    public TagDTO getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping
    public TagDTO createTag(@RequestBody TagDTO tagDTO) {
        return tagService.createTag(tagDTO);
    }

    @PutMapping("/{id}")
    public TagDTO updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        return tagService.updateTag(id, tagDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
