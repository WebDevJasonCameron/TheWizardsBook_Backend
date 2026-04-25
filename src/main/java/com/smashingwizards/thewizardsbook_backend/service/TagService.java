package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> getAllTags();
    TagDTO getTagById(Long id);
    TagDTO createTag(TagDTO tagDTO);
    TagDTO updateTag(Long id, TagDTO tagDTO);
    void deleteTag(Long id);

    /** ADDs */
    List<TagDTO> getAllByNameContainingIgnoreCase(String name);
}
