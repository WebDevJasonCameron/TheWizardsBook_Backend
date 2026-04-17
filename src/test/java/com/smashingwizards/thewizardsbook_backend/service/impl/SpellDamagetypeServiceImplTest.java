package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellDamagetypeMapper;
import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellDamagetype;
import com.smashingwizards.thewizardsbook_backend.repository.DamagetypeRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellDamagetypeRepository;
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
public class SpellDamagetypeServiceImplTest {
    @Mock
    private SpellDamagetypeRepository spellDamagetypeRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private DamagetypeRepository damagetypeRepository;
    @Mock
    private SpellDamagetypeMapper spellDamagetypeMapper;

    @InjectMocks
    private SpellDamagetypeServiceImpl spellDamagetypeService;

    @Test
    void getAllSpellDamagetypes_shouldReturnLstOfSpellDamagetypeDTOs() {
        SpellDamagetype spellDamagetype = new SpellDamagetype();
        spellDamagetype.setId(1L);

        SpellDamagetypeDTO dto = new SpellDamagetypeDTO(1L, 10L, 20L);

        when(spellDamagetypeRepository.findAll()).thenReturn(List.of(spellDamagetype));
        when(spellDamagetypeMapper.spellDamagetypeToSpellDamagetypeDTO(spellDamagetype)).thenReturn(dto);

        List<SpellDamagetypeDTO> result = spellDamagetypeService.getAllSpellDamagetypes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getDamagetypeId());

        verify(spellDamagetypeRepository).findAll();
        verify(spellDamagetypeMapper).spellDamagetypeToSpellDamagetypeDTO(spellDamagetype);
    }

    @Test
    void getSpellDamagetypeById_shouldReturnDTO_whenFound() {
        SpellDamagetype spellDamagetype = new SpellDamagetype();
        spellDamagetype.setId(1L);

        SpellDamagetypeDTO dto = new SpellDamagetypeDTO(1L, 10L, 20L);

        when(spellDamagetypeRepository.findById(1L)).thenReturn(java.util.Optional.of(spellDamagetype));
        when(spellDamagetypeMapper.spellDamagetypeToSpellDamagetypeDTO(spellDamagetype)).thenReturn(dto);

        SpellDamagetypeDTO result = spellDamagetypeService.getSpellDamagetypeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getDamagetypeId());

        verify(spellDamagetypeRepository).findById(1L);
        verify(spellDamagetypeMapper).spellDamagetypeToSpellDamagetypeDTO(spellDamagetype);
    }

    @Test
    void getSpellDamagetypeById_shouldThrowException_whenNotFound() {
        when(spellDamagetypeRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellDamagetypeService.getSpellDamagetypeById(1L);
        });

        assertEquals("SpellDamagetype not found", exception.getMessage());
        verify(spellDamagetypeRepository).findById(1L);
        verify(spellDamagetypeMapper, never()).spellDamagetypeToSpellDamagetypeDTO(any());
    }

    @Test
    void createSpellDamagetype_shouldSaveAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        Damagetype damagetype = new Damagetype();
        damagetype.setId(20L);

        SpellDamagetype savedSpellDamagetype = new SpellDamagetype();
        savedSpellDamagetype.setId(1L);
        savedSpellDamagetype.setSpell(spell);
        savedSpellDamagetype.setDamagetype(damagetype);

        SpellDamagetypeDTO inputDto = new SpellDamagetypeDTO(10L, 20L);
        SpellDamagetypeDTO resultDto = new SpellDamagetypeDTO(1L, 10L, 20L);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(damagetypeRepository.getReferenceById(20L)).thenReturn(damagetype);
        when(spellDamagetypeRepository.save(any(SpellDamagetype.class))).thenReturn(savedSpellDamagetype);
        when(spellDamagetypeMapper.spellDamagetypeToSpellDamagetypeDTO(savedSpellDamagetype)).thenReturn(resultDto);

        SpellDamagetypeDTO result = spellDamagetypeService.createSpellDamagetype(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getDamagetypeId());

        ArgumentCaptor<SpellDamagetype> captor = ArgumentCaptor.forClass(SpellDamagetype.class);
        verify(spellDamagetypeRepository).save(captor.capture());

        SpellDamagetype captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(damagetype, captured.getDamagetype());

        verify(spellRepository).getReferenceById(10L);
        verify(damagetypeRepository).getReferenceById(20L);
        verify(spellDamagetypeMapper).spellDamagetypeToSpellDamagetypeDTO(savedSpellDamagetype);
    }

    @Test
    void updateSpellDamagetype_shouldUpdateAndReturnDTO(){
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        Damagetype oldDamagetype = new Damagetype();
        oldDamagetype.setId(2L);

        SpellDamagetype existingSpellDamagetype = new SpellDamagetype();
        existingSpellDamagetype.setId(100L);
        existingSpellDamagetype.setSpell(oldSpell);
        existingSpellDamagetype.setDamagetype(oldDamagetype);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        Damagetype newDamagetype = new Damagetype();
        newDamagetype.setId(20L);

        SpellDamagetypeDTO updateDto = new SpellDamagetypeDTO(10L, 20L);
        SpellDamagetypeDTO resultDto = new SpellDamagetypeDTO(100L, 10L, 20L);

        when(spellDamagetypeRepository.findById(100L)).thenReturn(java.util.Optional.of(existingSpellDamagetype));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(damagetypeRepository.getReferenceById(20L)).thenReturn(newDamagetype);
        when(spellDamagetypeRepository.save(existingSpellDamagetype)).thenReturn(existingSpellDamagetype);
        when(spellDamagetypeMapper.spellDamagetypeToSpellDamagetypeDTO(existingSpellDamagetype)).thenReturn(resultDto);

        SpellDamagetypeDTO result = spellDamagetypeService.updateSpellDamagetype(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getDamagetypeId());

        assertEquals(newSpell, existingSpellDamagetype.getSpell());
        assertEquals(newDamagetype, existingSpellDamagetype.getDamagetype());

        verify(spellDamagetypeRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(damagetypeRepository).getReferenceById(20L);
        verify(spellDamagetypeRepository).save(existingSpellDamagetype);
        verify(spellDamagetypeMapper).spellDamagetypeToSpellDamagetypeDTO(existingSpellDamagetype);
    }

    @Test
    void updateSpellDamagetype_shouldThrowException_whenNotFound() {
        SpellDamagetypeDTO updateDto = new SpellDamagetypeDTO(10L, 20L);

        when(spellDamagetypeRepository.findById(100L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellDamagetypeService.updateSpellDamagetype(100L, updateDto);
        });

        assertEquals("SpellDamagetype not found", exception.getMessage());

        verify(spellDamagetypeRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(damagetypeRepository, never()).getReferenceById(anyLong());
        verify(spellDamagetypeRepository, never()).save(any());
    }

    @Test
    void deleteSpellDamagetype_shouldCallDeleteById() {
        spellDamagetypeService.deleteSpellDamagetype(1L);

        verify(spellDamagetypeRepository).deleteById(1L);
    }
}
