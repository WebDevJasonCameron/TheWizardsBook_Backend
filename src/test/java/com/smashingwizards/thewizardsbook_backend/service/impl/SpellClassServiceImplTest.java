package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellClassDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellClassMapper;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import com.smashingwizards.thewizardsbook_backend.repository.RpgClassRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellClassRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpellClassServiceImplTest {

    @Mock
    private SpellClassRepository spellClassRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private RpgClassRepository rpgClassRepository;
    @Mock
    private SpellClassMapper spellClassMapper;

    @InjectMocks
    private SpellClassServiceImpl spellClassService;

    @Test
    void getAllSpellClasses_shouldReturnListOfSpellClassDTOs() {
        SpellClass spellClass = new SpellClass();
        spellClass.setId(1L);

        SpellClassDTO dto = new SpellClassDTO(1L, 10L, 20L);

        when(spellClassRepository.findAll()).thenReturn(List.of(spellClass));
        when(spellClassMapper.spellClassToSpellClassDTO(spellClass)).thenReturn(dto);

        List<SpellClassDTO> result = spellClassService.getAllSpellClasses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getRpgClassId());

        verify(spellClassRepository).findAll();
        verify(spellClassMapper).spellClassToSpellClassDTO(spellClass);
    }

    @Test
    void getSpellClassById_shouldReturnDTO_whenFound() {
        SpellClass spellClass = new SpellClass();
        spellClass.setId(1L);

        SpellClassDTO dto = new SpellClassDTO(1L, 10L, 20L);

        when(spellClassRepository.findById(1L)).thenReturn(Optional.of(spellClass));
        when(spellClassMapper.spellClassToSpellClassDTO(spellClass)).thenReturn(dto);

        SpellClassDTO result = spellClassService.getSpellClassById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getRpgClassId());

        verify(spellClassRepository).findById(1L);
        verify(spellClassMapper).spellClassToSpellClassDTO(spellClass);
    }

    @Test
    void getSpellClassById_shouldThrowException_whenNotFound() {
        when(spellClassRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> spellClassService.getSpellClassById(1L));

        assertEquals("SpellClass not found", ex.getMessage());
        verify(spellClassRepository).findById(1L);
        verify(spellClassMapper, never()).spellClassToSpellClassDTO(any());
    }

    @Test
    void createSpellClass_shouldCreateAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        RpgClass rpgClass = new RpgClass();
        rpgClass.setId(20L);

        SpellClass savedSpellClass = new SpellClass();
        savedSpellClass.setId(1L);
        savedSpellClass.setSpell(spell);
        savedSpellClass.setRpgClass(rpgClass);

        SpellClassDTO inputDto = new SpellClassDTO(10L, 20L);
        SpellClassDTO resultDto = new SpellClassDTO(1L, 10L, 20L);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(rpgClassRepository.getReferenceById(20L)).thenReturn(rpgClass);
        when(spellClassRepository.save(any(SpellClass.class))).thenReturn(savedSpellClass);
        when(spellClassMapper.spellClassToSpellClassDTO(savedSpellClass)).thenReturn(resultDto);

        SpellClassDTO result = spellClassService.createSpellClass(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getRpgClassId());

        ArgumentCaptor<SpellClass> captor = ArgumentCaptor.forClass(SpellClass.class);
        verify(spellClassRepository).save(captor.capture());

        SpellClass captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(rpgClass, captured.getRpgClass());

        verify(spellRepository).getReferenceById(10L);
        verify(rpgClassRepository).getReferenceById(20L);
        verify(spellClassMapper).spellClassToSpellClassDTO(savedSpellClass);
    }

    @Test
    void updateSpellClass_shouldUpdateAndReturnDTO() {
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        RpgClass oldRpgClass = new RpgClass();
        oldRpgClass.setId(2L);

        SpellClass existingSpellClass = new SpellClass();
        existingSpellClass.setId(100L);
        existingSpellClass.setSpell(oldSpell);
        existingSpellClass.setRpgClass(oldRpgClass);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        RpgClass newRpgClass = new RpgClass();
        newRpgClass.setId(20L);

        SpellClassDTO updateDto = new SpellClassDTO(10L, 20L);
        SpellClassDTO resultDto = new SpellClassDTO(100L, 10L, 20L);

        when(spellClassRepository.findById(100L)).thenReturn(Optional.of(existingSpellClass));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(rpgClassRepository.getReferenceById(20L)).thenReturn(newRpgClass);
        when(spellClassRepository.save(existingSpellClass)).thenReturn(existingSpellClass);
        when(spellClassMapper.spellClassToSpellClassDTO(existingSpellClass)).thenReturn(resultDto);

        SpellClassDTO result = spellClassService.updateSpellClass(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getRpgClassId());

        assertEquals(newSpell, existingSpellClass.getSpell());
        assertEquals(newRpgClass, existingSpellClass.getRpgClass());

        verify(spellClassRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(rpgClassRepository).getReferenceById(20L);
        verify(spellClassRepository).save(existingSpellClass);
        verify(spellClassMapper).spellClassToSpellClassDTO(existingSpellClass);
    }

    @Test
    void updateSpellClass_shouldThrowException_whenNotFound() {
        SpellClassDTO updateDto = new SpellClassDTO(10L, 20L);

        when(spellClassRepository.findById(100L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> spellClassService.updateSpellClass(100L, updateDto));

        assertEquals("SpellClass not found", ex.getMessage());

        verify(spellClassRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(rpgClassRepository, never()).getReferenceById(anyLong());
        verify(spellClassRepository, never()).save(any());
    }

    @Test
    void deleteSpellClass_shouldCallDeleteById() {
        spellClassService.deleteSpellClass(1L);

        verify(spellClassRepository).deleteById(1L);
    }
}