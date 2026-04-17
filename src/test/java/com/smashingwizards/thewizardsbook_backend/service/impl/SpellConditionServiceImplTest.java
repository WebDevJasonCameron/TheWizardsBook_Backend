package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellConditionDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellConditionMapper;
import com.smashingwizards.thewizardsbook_backend.model.Condition;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellCondition;
import com.smashingwizards.thewizardsbook_backend.repository.ConditionRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellConditionRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpellConditionServiceImplTest {

    @Mock
    private SpellConditionRepository spellConditionRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private ConditionRepository conditionRepository;
    @Mock
    private SpellConditionMapper spellConditionMapper;

    @InjectMocks
    private SpellConditionServiceImpl spellConditionService;

    @Test
    void getAllSpellConditions_shouldReturnLstOfSpellConditionDTOs() {
        SpellCondition spellCondition = new SpellCondition();
        spellCondition.setId(1L);

        SpellConditionDTO dto = new SpellConditionDTO(1L, 10L, 20L);

        when(spellConditionRepository.findAll()).thenReturn(List.of(spellCondition));
        when(spellConditionMapper.spellConditionToSpellConditionDTO(spellCondition)).thenReturn(dto);

        List<SpellConditionDTO> result = spellConditionService.getAllSpellConditions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getConditionId());

        verify(spellConditionRepository).findAll();
        verify(spellConditionMapper).spellConditionToSpellConditionDTO(spellCondition);
    }

    @Test
    void getSpellConditionById_shouldReturnDTO_whenFound() {
        SpellCondition spellCondition = new SpellCondition();
        spellCondition.setId(1L);

        SpellConditionDTO dto = new SpellConditionDTO(1L, 10L, 20L);

        when(spellConditionRepository.findById(1L)).thenReturn(java.util.Optional.of(spellCondition));
        when(spellConditionMapper.spellConditionToSpellConditionDTO(spellCondition)).thenReturn(dto);

        SpellConditionDTO result = spellConditionService.getSpellConditionById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getConditionId());

        verify(spellConditionRepository).findById(1L);
        verify(spellConditionMapper).spellConditionToSpellConditionDTO(spellCondition);
    }

    @Test
    void getSpellConditionById_shouldThrowException_whenNotFound() {
        when(spellConditionRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellConditionService.getSpellConditionById(1L);
        });

        assertEquals("SpellCondition not found", exception.getMessage());
        verify(spellConditionRepository).findById(1L);
        verify(spellConditionMapper, never()).spellConditionToSpellConditionDTO(any());
    }

    @Test
    void createSpellCondition_shouldSaveAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        Condition condition = new Condition();
        condition.setId(20L);

        SpellCondition savedSpellCondition = new SpellCondition();
        savedSpellCondition.setId(1L);
        savedSpellCondition.setSpell(spell);
        savedSpellCondition.setCondition(condition);

        SpellConditionDTO inputDto = new SpellConditionDTO(10L, 20L);
        SpellConditionDTO resultDto = new SpellConditionDTO(1L, 10L, 20L);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(conditionRepository.getReferenceById(20L)).thenReturn(condition);
        when(spellConditionRepository.save(any(SpellCondition.class))).thenReturn(savedSpellCondition);
        when(spellConditionMapper.spellConditionToSpellConditionDTO(savedSpellCondition)).thenReturn(resultDto);

        SpellConditionDTO result = spellConditionService.createSpellCondition(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getConditionId());

        ArgumentCaptor<SpellCondition> captor = ArgumentCaptor.forClass(SpellCondition.class);
        verify(spellConditionRepository).save(captor.capture());

        SpellCondition captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(condition, captured.getCondition());

        verify(spellRepository).getReferenceById(10L);
        verify(conditionRepository).getReferenceById(20L);
        verify(spellConditionMapper).spellConditionToSpellConditionDTO(savedSpellCondition);
    }

    @Test
    void updateSpellCondition_shouldUpdateAndReturnDTO(){
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        Condition oldCondition = new Condition();
        oldCondition.setId(2L);

        SpellCondition existingSpellCondition = new SpellCondition();
        existingSpellCondition.setId(100L);
        existingSpellCondition.setSpell(oldSpell);
        existingSpellCondition.setCondition(oldCondition);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        Condition newCondition = new Condition();
        newCondition.setId(20L);

        SpellConditionDTO updateDto = new SpellConditionDTO(10L, 20L);
        SpellConditionDTO resultDto = new SpellConditionDTO(100L, 10L, 20L);

        when(spellConditionRepository.findById(100L)).thenReturn(java.util.Optional.of(existingSpellCondition));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(conditionRepository.getReferenceById(20L)).thenReturn(newCondition);
        when(spellConditionRepository.save(existingSpellCondition)).thenReturn(existingSpellCondition);
        when(spellConditionMapper.spellConditionToSpellConditionDTO(existingSpellCondition)).thenReturn(resultDto);

        SpellConditionDTO result = spellConditionService.updateSpellCondition(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getConditionId());

        assertEquals(newSpell, existingSpellCondition.getSpell());
        assertEquals(newCondition, existingSpellCondition.getCondition());

        verify(spellConditionRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(conditionRepository).getReferenceById(20L);
        verify(spellConditionRepository).save(existingSpellCondition);
        verify(spellConditionMapper).spellConditionToSpellConditionDTO(existingSpellCondition);
    }

    @Test
    void updateSpellCondition_shouldThrowException_whenNotFound() {
        SpellConditionDTO updateDto = new SpellConditionDTO(10L, 20L);

        when(spellConditionRepository.findById(100L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellConditionService.updateSpellCondition(100L, updateDto);
        });

        assertEquals("SpellCondition not found", exception.getMessage());

        verify(spellConditionRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(conditionRepository, never()).getReferenceById(anyLong());
        verify(spellConditionRepository, never()).save(any());
    }

    @Test
    void deleteSpellCondition_shouldCallDeleteById() {
        spellConditionService.deleteSpellCondition(1L);

        verify(spellConditionRepository).deleteById(1L);
    }


}
