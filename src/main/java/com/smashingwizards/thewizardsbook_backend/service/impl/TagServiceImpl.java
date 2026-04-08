package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.TagMapper;
import com.smashingwizards.thewizardsbook_backend.model.Tag;
import com.smashingwizards.thewizardsbook_backend.repository.TagRepository;
import com.smashingwizards.thewizardsbook_backend.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    // ATTs
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    // CONs
    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    // CRUDs
    @Override
    public List<TagDTO> getTags() {
        return tagRepository.findAll()
                .stream()
                .map(tagMapper::tagToTagDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::tagToTagDTO)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @Override
    public TagDTO createTag(TagDTO tagDTO) {
        return tagMapper.tagToTagDTO(tagRepository
                .save(tagMapper.tagDTOToTag(tagDTO)));
    }

    @Override
    public TagDTO updateTag(Long id, TagDTO tagDTO) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if(!optionalTag.isPresent()) {
            throw new RuntimeException("Tag not found");
        }
        Tag existingTag = optionalTag.get();

        existingTag.setName(tagDTO.getName());
        existingTag.setType(tagDTO.getType());

        return tagMapper.tagToTagDTO(tagRepository.save(existingTag));
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
