package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellImageUrlMapper;
import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellImageUrl;
import com.smashingwizards.thewizardsbook_backend.repository.ImageUrlRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellImageUrlRepository;
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
public class SpellImageUrlServiceImplTest {


    @Mock
    private SpellImageUrlRepository spellImageUrlRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private ImageUrlRepository imageUrlRepository;
    @Mock
    private SpellImageUrlMapper spellImageUrlMapper;

    @InjectMocks
    private SpellImageUrlServiceImpl spellImageUrlService;

    @Test
    void getAllSpellImageUrls_shouldReturnLstOfSpellImageUrlDTOs() {
        SpellImageUrl spellImageUrl = new SpellImageUrl();
        spellImageUrl.setId(1L);

        SpellImageUrlDTO dto = new SpellImageUrlDTO(1L, 10L, 20L);

        when(spellImageUrlRepository.findAll()).thenReturn(List.of(spellImageUrl));
        when(spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(spellImageUrl)).thenReturn(dto);

        List<SpellImageUrlDTO> result = spellImageUrlService.getAllSpellImageUrls();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getImageUrlId());

        verify(spellImageUrlRepository).findAll();
        verify(spellImageUrlMapper).spellImageUrlToSpellImageUrlDTO(spellImageUrl);
    }

    @Test
    void getSpellImageUrlById_shouldReturnDTO_whenFound() {
        SpellImageUrl spellImageUrl = new SpellImageUrl();
        spellImageUrl.setId(1L);

        SpellImageUrlDTO dto = new SpellImageUrlDTO(1L, 10L, 20L);

        when(spellImageUrlRepository.findById(1L)).thenReturn(java.util.Optional.of(spellImageUrl));
        when(spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(spellImageUrl)).thenReturn(dto);

        SpellImageUrlDTO result = spellImageUrlService.getSpellImageUrlById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getImageUrlId());

        verify(spellImageUrlRepository).findById(1L);
        verify(spellImageUrlMapper).spellImageUrlToSpellImageUrlDTO(spellImageUrl);
    }

    @Test
    void getSpellImageUrlById_shouldThrowException_whenNotFound() {
        when(spellImageUrlRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellImageUrlService.getSpellImageUrlById(1L);
        });

        assertEquals("SpellImageUrl not found", exception.getMessage());
        verify(spellImageUrlRepository).findById(1L);
        verify(spellImageUrlMapper, never()).spellImageUrlToSpellImageUrlDTO(any());
    }

    @Test
    void createSpellImageUrl_shouldSaveAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setId(20L);

        SpellImageUrl savedSpellImageUrl = new SpellImageUrl();
        savedSpellImageUrl.setId(1L);
        savedSpellImageUrl.setSpell(spell);
        savedSpellImageUrl.setImageUrl(imageUrl);

        SpellImageUrlDTO inputDto = new SpellImageUrlDTO(10L, 20L);
        SpellImageUrlDTO resultDto = new SpellImageUrlDTO(1L, 10L, 20L);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(imageUrlRepository.getReferenceById(20L)).thenReturn(imageUrl);
        when(spellImageUrlRepository.save(any(SpellImageUrl.class))).thenReturn(savedSpellImageUrl);
        when(spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(savedSpellImageUrl)).thenReturn(resultDto);

        SpellImageUrlDTO result = spellImageUrlService.createSpellImageUrl(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getImageUrlId());

        ArgumentCaptor<SpellImageUrl> captor = ArgumentCaptor.forClass(SpellImageUrl.class);
        verify(spellImageUrlRepository).save(captor.capture());

        SpellImageUrl captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(imageUrl, captured.getImageUrl());

        verify(spellRepository).getReferenceById(10L);
        verify(imageUrlRepository).getReferenceById(20L);
        verify(spellImageUrlMapper).spellImageUrlToSpellImageUrlDTO(savedSpellImageUrl);
    }

    @Test
    void updateSpellImageUrl_shouldUpdateAndReturnDTO(){
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        ImageUrl oldImageUrl = new ImageUrl();
        oldImageUrl.setId(2L);

        SpellImageUrl existingSpellImageUrl = new SpellImageUrl();
        existingSpellImageUrl.setId(100L);
        existingSpellImageUrl.setSpell(oldSpell);
        existingSpellImageUrl.setImageUrl(oldImageUrl);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        ImageUrl newImageUrl = new ImageUrl();
        newImageUrl.setId(20L);

        SpellImageUrlDTO updateDto = new SpellImageUrlDTO(10L, 20L);
        SpellImageUrlDTO resultDto = new SpellImageUrlDTO(100L, 10L, 20L);

        when(spellImageUrlRepository.findById(100L)).thenReturn(java.util.Optional.of(existingSpellImageUrl));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(imageUrlRepository.getReferenceById(20L)).thenReturn(newImageUrl);
        when(spellImageUrlRepository.save(existingSpellImageUrl)).thenReturn(existingSpellImageUrl);
        when(spellImageUrlMapper.spellImageUrlToSpellImageUrlDTO(existingSpellImageUrl)).thenReturn(resultDto);

        SpellImageUrlDTO result = spellImageUrlService.updateSpellImageUrl(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getImageUrlId());

        assertEquals(newSpell, existingSpellImageUrl.getSpell());
        assertEquals(newImageUrl, existingSpellImageUrl.getImageUrl());

        verify(spellImageUrlRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(imageUrlRepository).getReferenceById(20L);
        verify(spellImageUrlRepository).save(existingSpellImageUrl);
        verify(spellImageUrlMapper).spellImageUrlToSpellImageUrlDTO(existingSpellImageUrl);
    }

    @Test
    void updateSpellImageUrl_shouldThrowException_whenNotFound() {
        SpellImageUrlDTO updateDto = new SpellImageUrlDTO(10L, 20L);

        when(spellImageUrlRepository.findById(100L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellImageUrlService.updateSpellImageUrl(100L, updateDto);
        });

        assertEquals("SpellImageUrl not found", exception.getMessage());

        verify(spellImageUrlRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(imageUrlRepository, never()).getReferenceById(anyLong());
        verify(spellImageUrlRepository, never()).save(any());
    }

    @Test
    void deleteSpellImageUrl_shouldCallDeleteById() {
        spellImageUrlService.deleteSpellImageUrl(1L);

        verify(spellImageUrlRepository).deleteById(1L);
    }
}
