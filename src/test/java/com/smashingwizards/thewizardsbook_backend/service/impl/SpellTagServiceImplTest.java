package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTagDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellTagMapper;
import com.smashingwizards.thewizardsbook_backend.model.Tag;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellTag;
import com.smashingwizards.thewizardsbook_backend.repository.TagRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellTagRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SpellTagServiceImplTest {

    @Mock
    private SpellTagRepository spellTagRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private SpellTagMapper spellTagMapper;

    @InjectMocks
    private SpellTagServiceImpl spellTagService;

    @Test
    void getAllSpellTags_shouldReturnLstOfSpellTagDTOs() {
        SpellTag spellTag = new SpellTag();
        spellTag.setId(1L);

        SpellTagDTO dto = new SpellTagDTO(1L, 10L, 20L);

        when(spellTagRepository.findAll()).thenReturn(List.of(spellTag));
        when(spellTagMapper.spellTagToSpellTagDTO(spellTag)).thenReturn(dto);

        List<SpellTagDTO> result = spellTagService.getAllSpellTags();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getTagId());

        verify(spellTagRepository).findAll();
        verify(spellTagMapper).spellTagToSpellTagDTO(spellTag);
    }

    @Test
    void getSpellTagById_shouldReturnDTO_whenFound() {
        SpellTag spellTag = new SpellTag();
        spellTag.setId(1L);

        SpellTagDTO dto = new SpellTagDTO(1L, 10L, 20L);

        when(spellTagRepository.findById(1L)).thenReturn(java.util.Optional.of(spellTag));
        when(spellTagMapper.spellTagToSpellTagDTO(spellTag)).thenReturn(dto);

        SpellTagDTO result = spellTagService.getSpellTagById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getTagId());

        verify(spellTagRepository).findById(1L);
        verify(spellTagMapper).spellTagToSpellTagDTO(spellTag);
    }

    @Test
    void getSpellTagById_shouldThrowException_whenNotFound() {
        when(spellTagRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellTagService.getSpellTagById(1L);
        });

        assertEquals("SpellTag not found", exception.getMessage());
        verify(spellTagRepository).findById(1L);
        verify(spellTagMapper, never()).spellTagToSpellTagDTO(any());
    }

    @Test
    void createSpellTag_shouldSaveAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        Tag tag = new Tag();
        tag.setId(20L);

        SpellTag savedSpellTag = new SpellTag();
        savedSpellTag.setId(1L);
        savedSpellTag.setSpell(spell);
        savedSpellTag.setTag(tag);

        SpellTagDTO inputDto = new SpellTagDTO(10L, 20L);
        SpellTagDTO resultDto = new SpellTagDTO(1L, 10L, 20L);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(tagRepository.getReferenceById(20L)).thenReturn(tag);
        when(spellTagRepository.save(any(SpellTag.class))).thenReturn(savedSpellTag);
        when(spellTagMapper.spellTagToSpellTagDTO(savedSpellTag)).thenReturn(resultDto);

        SpellTagDTO result = spellTagService.createSpellTag(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getTagId());

        ArgumentCaptor<SpellTag> captor = ArgumentCaptor.forClass(SpellTag.class);
        verify(spellTagRepository).save(captor.capture());

        SpellTag captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(tag, captured.getTag());

        verify(spellRepository).getReferenceById(10L);
        verify(tagRepository).getReferenceById(20L);
        verify(spellTagMapper).spellTagToSpellTagDTO(savedSpellTag);
    }

    @Test
    void updateSpellTag_shouldUpdateAndReturnDTO(){
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        Tag oldTag = new Tag();
        oldTag.setId(2L);

        SpellTag existingSpellTag = new SpellTag();
        existingSpellTag.setId(100L);
        existingSpellTag.setSpell(oldSpell);
        existingSpellTag.setTag(oldTag);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        Tag newTag = new Tag();
        newTag.setId(20L);

        SpellTagDTO updateDto = new SpellTagDTO(10L, 20L);
        SpellTagDTO resultDto = new SpellTagDTO(100L, 10L, 20L);

        when(spellTagRepository.findById(100L)).thenReturn(java.util.Optional.of(existingSpellTag));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(tagRepository.getReferenceById(20L)).thenReturn(newTag);
        when(spellTagRepository.save(existingSpellTag)).thenReturn(existingSpellTag);
        when(spellTagMapper.spellTagToSpellTagDTO(existingSpellTag)).thenReturn(resultDto);

        SpellTagDTO result = spellTagService.updateSpellTag(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getTagId());

        assertEquals(newSpell, existingSpellTag.getSpell());
        assertEquals(newTag, existingSpellTag.getTag());

        verify(spellTagRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(tagRepository).getReferenceById(20L);
        verify(spellTagRepository).save(existingSpellTag);
        verify(spellTagMapper).spellTagToSpellTagDTO(existingSpellTag);
    }

    @Test
    void updateSpellTag_shouldThrowException_whenNotFound() {
        SpellTagDTO updateDto = new SpellTagDTO(10L, 20L);

        when(spellTagRepository.findById(100L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellTagService.updateSpellTag(100L, updateDto);
        });

        assertEquals("SpellTag not found", exception.getMessage());

        verify(spellTagRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(tagRepository, never()).getReferenceById(anyLong());
        verify(spellTagRepository, never()).save(any());
    }

    @Test
    void deleteSpellTag_shouldCallDeleteById() {
        spellTagService.deleteSpellTag(1L);

        verify(spellTagRepository).deleteById(1L);
    }
}
