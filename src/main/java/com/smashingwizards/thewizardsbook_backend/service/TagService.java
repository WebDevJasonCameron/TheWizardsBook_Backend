package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<TagDTO> getTags();
    TagDTO getTagById(Long id);
    TagDTO createTag(TagDTO tagDto);
    TagDTO updateTag(Long id, TagDTO tagDto);
    void deleteTag(Long id);
}
