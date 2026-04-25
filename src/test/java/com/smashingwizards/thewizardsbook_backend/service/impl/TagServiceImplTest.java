package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.TagDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.TagMapper;
import com.smashingwizards.thewizardsbook_backend.model.Tag;
import com.smashingwizards.thewizardsbook_backend.repository.TagRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    @Mock
    private TagRepository tagRepositoryMock;
    @Mock
    private TagMapper tagMapperMock;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void getAllTags_shouldReturnLstOfConditionDTOs() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Test Tag");
        tag.setType("Test Type");

        TagDTO dto = new TagDTO(1L, "Test Tag", "Test Type");

        when(tagRepositoryMock.findAll()).thenReturn(List.of(tag));
        when(tagMapperMock.tagToTagDTO(tag)).thenReturn(dto);

        List<TagDTO> result = tagService.getAllTags();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Tag", result.get(0).getName());
        assertEquals("Test Type", result.get(0).getType());

        verify(tagRepositoryMock).findAll();
        verify(tagMapperMock).tagToTagDTO(tag);
    }

    @Test
    void getTagById_shouldReturnTagDTO() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Test Tag");
        tag.setType("Test Type");

        TagDTO dto = new TagDTO(1L, "Test Tag", "Test Type");

        when(tagRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(tag));
        when(tagMapperMock.tagToTagDTO(tag)).thenReturn(dto);

        TagDTO result = tagService.getTagById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Tag", result.getName());
        assertEquals("Test Type", result.getType());

        verify(tagRepositoryMock).findById(1L);
        verify(tagMapperMock).tagToTagDTO(tag);
    }

    @Test
    void getTagById_shouldThrowException_whenNotFound() {
        when(tagRepositoryMock.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> tagService.getTagById(1L));

        assertEquals("Tag not found", exception.getMessage());
        verify(tagRepositoryMock).findById(1L);
        verify(tagMapperMock, never()).tagToTagDTO(any());
    }

    @Test
    void createTag_shouldSaveAndReturnTagDTO() {
        TagDTO dto = new TagDTO(null, "Test Tag", "Test Type");
        Tag tag = new Tag();
        tag.setName("Test Tag");
        tag.setType("Test Type");

        Tag savedTag = new Tag();
        savedTag.setId(5L);
        savedTag.setName("Test Tag");
        savedTag.setType("Test Type");

        TagDTO savedDto = new TagDTO(5L, "Test Tag", "Test Type");

        when(tagMapperMock.tagDTOToTag(dto)).thenReturn(tag);
        when(tagRepositoryMock.save(tag)).thenReturn(savedTag);
        when(tagMapperMock.tagToTagDTO(savedTag)).thenReturn(savedDto);

        TagDTO result = tagService.createTag(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test Tag", result.getName());
        assertEquals("Test Type", result.getType());

        verify(tagMapperMock).tagDTOToTag(dto);
        verify(tagRepositoryMock).save(tag);
        verify(tagMapperMock).tagToTagDTO(savedTag);
    }

    @Test
    void updateTag_shouldUpdateAndReturnTagDTO_whenFound() {
        Tag existingTag = new Tag();
        existingTag.setId(1L);
        existingTag.setName("Old Name");
        existingTag.setType("Old Type");

        TagDTO updateDto = new TagDTO(null, "New Name", "New Type");

        Tag updatedTag = new Tag();
        updatedTag.setId(1L);
        updatedTag.setName("New Name");
        updatedTag.setType("New Type");

        TagDTO updatedDto = new TagDTO(1L, "New Name", "New Type");

        when(tagRepositoryMock.findById(1L)).thenReturn(Optional.of(existingTag));
        when(tagRepositoryMock.save(existingTag)).thenReturn(updatedTag);
        when(tagMapperMock.tagToTagDTO(updatedTag)).thenReturn(updatedDto);

        TagDTO result = tagService.updateTag(1L, updateDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("New Type", result.getType());

        verify(tagRepositoryMock).findById(1L);
        verify(tagRepositoryMock).save(existingTag);
        verify(tagMapperMock).tagToTagDTO(updatedTag);
    }

    @Test
    void updateTag_shouldThrowException_whenNotFound() {
        TagDTO updateDto = new TagDTO(null, "New Name", "New Type");

        when(tagRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> tagService.updateTag(1L, updateDto));

        assertEquals("Tag not found", exception.getMessage());

        verify(tagRepositoryMock).findById(1L);
        verify(tagRepositoryMock, never()).save(any());
    }

    @Test
    void deleteTag_shouldCallRepositoryDeleteById() {
        tagService.deleteTag(1L);

        verify(tagRepositoryMock).deleteById(1L);
    }

    /** ADDs */
    @Test
    @DisplayName("getAllByNameContainingIgnoreCase() should return matching TagDTOs")
    void getAllByNameContainingIgnoreCase_shouldReturnMatchingTagDTOs(){
        TagRepository tagRepository = mock(TagRepository.class);
        TagMapper tagMapper = mock(TagMapper.class);

        tagService = new TagServiceImpl(tagRepository, tagMapper);

        Tag tag1 = new Tag("Test Name 1", "Test Type 1");
        Tag tag2 = new Tag("Test Name 2", "Test Type 2");

        TagDTO tagDTO1 = new TagDTO(1L, "Test Name 1", "Test Type 1");
        TagDTO tagDTO2 = new TagDTO(2L, "Test Name 2", "Test Type 2");

        when(tagRepository.findAllByNameContainingIgnoreCase("Test")).thenReturn(List.of(tag1, tag2));
        when(tagMapper.tagToTagDTO(tag1)).thenReturn(tagDTO1);
        when(tagMapper.tagToTagDTO(tag2)).thenReturn(tagDTO2);

        List<TagDTO> result = tagService.getAllByNameContainingIgnoreCase("Test");

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Test Name 1", result.get(0).getName());
        assertEquals("Test Name 2", result.get(1).getName());

        verify(tagRepository).findAllByNameContainingIgnoreCase("Test");
        verify(tagMapper).tagToTagDTO(tag1);
        verify(tagMapper).tagToTagDTO(tag2);
    }
}
