package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellSourceDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellSourceMapper;
import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellSource;
import com.smashingwizards.thewizardsbook_backend.repository.SourceRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellSourceRepository;
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
public class SpellSourceServiceImplTest {

    @Mock
    private SpellSourceRepository spellSourceRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private SourceRepository sourceRepository;
    @Mock
    private SpellSourceMapper spellSourceMapper;

    @InjectMocks
    private SpellSourceServiceImpl spellSourceService;

    @Test
    void getAllSpellSources_shouldReturnLstOfSpellSourceDTOs() {
        SpellSource spellSource = new SpellSource();
        spellSource.setId(1L);
        String page = "Test Page";

        SpellSourceDTO dto = new SpellSourceDTO(1L, 10L, 20L, page);

        when(spellSourceRepository.findAll()).thenReturn(List.of(spellSource));
        when(spellSourceMapper.spellSourceToSpellSourceDTO(spellSource)).thenReturn(dto);

        List<SpellSourceDTO> result = spellSourceService.getAllSpellSources();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getSourceId());

        verify(spellSourceRepository).findAll();
        verify(spellSourceMapper).spellSourceToSpellSourceDTO(spellSource);
    }

    @Test
    void getSpellSourceById_shouldReturnDTO_whenFound() {
        SpellSource spellSource = new SpellSource();
        spellSource.setId(1L);
        String page = "Test Page";


        SpellSourceDTO dto = new SpellSourceDTO(1L, 10L, 20L, page);

        when(spellSourceRepository.findById(1L)).thenReturn(java.util.Optional.of(spellSource));
        when(spellSourceMapper.spellSourceToSpellSourceDTO(spellSource)).thenReturn(dto);

        SpellSourceDTO result = spellSourceService.getSpellSourceById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getSourceId());

        verify(spellSourceRepository).findById(1L);
        verify(spellSourceMapper).spellSourceToSpellSourceDTO(spellSource);
    }

    @Test
    void getSpellSourceById_shouldThrowException_whenNotFound() {
        when(spellSourceRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellSourceService.getSpellSourceById(1L);
        });

        assertEquals("SpellSource not found", exception.getMessage());
        verify(spellSourceRepository).findById(1L);
        verify(spellSourceMapper, never()).spellSourceToSpellSourceDTO(any());
    }

    @Test
    void createSpellSource_shouldSaveAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        Source source = new Source();
        source.setId(20L);

        String page = "Test Page";

        SpellSource savedSpellSource = new SpellSource();
        savedSpellSource.setId(1L);
        savedSpellSource.setSpell(spell);
        savedSpellSource.setSource(source);

        SpellSourceDTO inputDto = new SpellSourceDTO(10L, 20L, page);
        SpellSourceDTO resultDto = new SpellSourceDTO(1L, 10L, 20L, page);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(sourceRepository.getReferenceById(20L)).thenReturn(source);
        when(spellSourceRepository.save(any(SpellSource.class))).thenReturn(savedSpellSource);
        when(spellSourceMapper.spellSourceToSpellSourceDTO(savedSpellSource)).thenReturn(resultDto);

        SpellSourceDTO result = spellSourceService.createSpellSource(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getSourceId());

        ArgumentCaptor<SpellSource> captor = ArgumentCaptor.forClass(SpellSource.class);
        verify(spellSourceRepository).save(captor.capture());

        SpellSource captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(source, captured.getSource());

        verify(spellRepository).getReferenceById(10L);
        verify(sourceRepository).getReferenceById(20L);
        verify(spellSourceMapper).spellSourceToSpellSourceDTO(savedSpellSource);
    }

    @Test
    void updateSpellSource_shouldUpdateAndReturnDTO(){
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        Source oldSource = new Source();
        oldSource.setId(2L);

        SpellSource existingSpellSource = new SpellSource();
        existingSpellSource.setId(100L);
        existingSpellSource.setSpell(oldSpell);
        existingSpellSource.setSource(oldSource);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        Source newSource = new Source();
        newSource.setId(20L);

        String page = "Test Page";

        SpellSourceDTO updateDto = new SpellSourceDTO(10L, 20L, page);
        SpellSourceDTO resultDto = new SpellSourceDTO(100L, 10L, 20L, page);

        when(spellSourceRepository.findById(100L)).thenReturn(java.util.Optional.of(existingSpellSource));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(sourceRepository.getReferenceById(20L)).thenReturn(newSource);
        when(spellSourceRepository.save(existingSpellSource)).thenReturn(existingSpellSource);
        when(spellSourceMapper.spellSourceToSpellSourceDTO(existingSpellSource)).thenReturn(resultDto);

        SpellSourceDTO result = spellSourceService.updateSpellSource(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getSourceId());

        assertEquals(newSpell, existingSpellSource.getSpell());
        assertEquals(newSource, existingSpellSource.getSource());

        verify(spellSourceRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(sourceRepository).getReferenceById(20L);
        verify(spellSourceRepository).save(existingSpellSource);
        verify(spellSourceMapper).spellSourceToSpellSourceDTO(existingSpellSource);
    }

    @Test
    void updateSpellSource_shouldThrowException_whenNotFound() {
        String page = "Test Page";

        SpellSourceDTO updateDto = new SpellSourceDTO(10L, 20L, page);

        when(spellSourceRepository.findById(100L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellSourceService.updateSpellSource(100L, updateDto);
        });

        assertEquals("SpellSource not found", exception.getMessage());

        verify(spellSourceRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(sourceRepository, never()).getReferenceById(anyLong());
        verify(spellSourceRepository, never()).save(any());
    }

    @Test
    void deleteSpellSource_shouldCallDeleteById() {
        spellSourceService.deleteSpellSource(1L);

        verify(spellSourceRepository).deleteById(1L);
    }

}
