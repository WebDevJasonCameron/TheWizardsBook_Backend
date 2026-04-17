package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellTtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellTtrpgMapper;
import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import com.smashingwizards.thewizardsbook_backend.repository.TtrpgRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellTtrpgRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SpellTtrpgServiceImplTest {

    @Mock
    private SpellTtrpgRepository spellTtrpgRepository;
    @Mock
    private SpellRepository spellRepository;
    @Mock
    private TtrpgRepository ttrpgRepository;
    @Mock
    private SpellTtrpgMapper spellTtrpgMapper;

    @InjectMocks
    private SpellTtrpgServiceImpl spellTtrpgService;

    @Test
    void getAllSpellTtrpgs_shouldReturnLstOfSpellTtrpgDTOs() {
        SpellTtrpg spellTtrpg = new SpellTtrpg();
        spellTtrpg.setId(1L);

        SpellTtrpgDTO dto = new SpellTtrpgDTO(1L, 10L, 20L);

        when(spellTtrpgRepository.findAll()).thenReturn(List.of(spellTtrpg));
        when(spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(spellTtrpg)).thenReturn(dto);

        List<SpellTtrpgDTO> result = spellTtrpgService.getAllSpellTtrpgs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(10L, result.get(0).getSpellId());
        assertEquals(20L, result.get(0).getTtrpgId());

        verify(spellTtrpgRepository).findAll();
        verify(spellTtrpgMapper).spellTtrpgToSpellTtrpgDTO(spellTtrpg);
    }

    @Test
    void getSpellTtrpgById_shouldReturnDTO_whenFound() {
        SpellTtrpg spellTtrpg = new SpellTtrpg();
        spellTtrpg.setId(1L);

        SpellTtrpgDTO dto = new SpellTtrpgDTO(1L, 10L, 20L);

        when(spellTtrpgRepository.findById(1L)).thenReturn(java.util.Optional.of(spellTtrpg));
        when(spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(spellTtrpg)).thenReturn(dto);

        SpellTtrpgDTO result = spellTtrpgService.getSpellTtrpgById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getTtrpgId());

        verify(spellTtrpgRepository).findById(1L);
        verify(spellTtrpgMapper).spellTtrpgToSpellTtrpgDTO(spellTtrpg);
    }

    @Test
    void getSpellTtrpgById_shouldThrowException_whenNotFound() {
        when(spellTtrpgRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellTtrpgService.getSpellTtrpgById(1L);
        });

        assertEquals("SpellTtrpg not found", exception.getMessage());
        verify(spellTtrpgRepository).findById(1L);
        verify(spellTtrpgMapper, never()).spellTtrpgToSpellTtrpgDTO(any());
    }

    @Test
    void createSpellTtrpg_shouldSaveAndReturnDTO() {
        Spell spell = new Spell();
        spell.setId(10L);

        Ttrpg ttrpg = new Ttrpg();
        ttrpg.setId(20L);

        SpellTtrpg savedSpellTtrpg = new SpellTtrpg();
        savedSpellTtrpg.setId(1L);
        savedSpellTtrpg.setSpell(spell);
        savedSpellTtrpg.setTtrpg(ttrpg);

        SpellTtrpgDTO inputDto = new SpellTtrpgDTO(10L, 20L);
        SpellTtrpgDTO resultDto = new SpellTtrpgDTO(1L, 10L, 20L);

        when(spellRepository.getReferenceById(10L)).thenReturn(spell);
        when(ttrpgRepository.getReferenceById(20L)).thenReturn(ttrpg);
        when(spellTtrpgRepository.save(any(SpellTtrpg.class))).thenReturn(savedSpellTtrpg);
        when(spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(savedSpellTtrpg)).thenReturn(resultDto);

        SpellTtrpgDTO result = spellTtrpgService.createSpellTtrpg(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getTtrpgId());

        ArgumentCaptor<SpellTtrpg> captor = ArgumentCaptor.forClass(SpellTtrpg.class);
        verify(spellTtrpgRepository).save(captor.capture());

        SpellTtrpg captured = captor.getValue();
        assertEquals(spell, captured.getSpell());
        assertEquals(ttrpg, captured.getTtrpg());

        verify(spellRepository).getReferenceById(10L);
        verify(ttrpgRepository).getReferenceById(20L);
        verify(spellTtrpgMapper).spellTtrpgToSpellTtrpgDTO(savedSpellTtrpg);
    }

    @Test
    void updateSpellTtrpg_shouldUpdateAndReturnDTO(){
        Spell oldSpell = new Spell();
        oldSpell.setId(1L);

        Ttrpg oldTtrpg = new Ttrpg();
        oldTtrpg.setId(2L);

        SpellTtrpg existingSpellTtrpg = new SpellTtrpg();
        existingSpellTtrpg.setId(100L);
        existingSpellTtrpg.setSpell(oldSpell);
        existingSpellTtrpg.setTtrpg(oldTtrpg);

        Spell newSpell = new Spell();
        newSpell.setId(10L);

        Ttrpg newTtrpg = new Ttrpg();
        newTtrpg.setId(20L);

        SpellTtrpgDTO updateDto = new SpellTtrpgDTO(10L, 20L);
        SpellTtrpgDTO resultDto = new SpellTtrpgDTO(100L, 10L, 20L);

        when(spellTtrpgRepository.findById(100L)).thenReturn(java.util.Optional.of(existingSpellTtrpg));
        when(spellRepository.getReferenceById(10L)).thenReturn(newSpell);
        when(ttrpgRepository.getReferenceById(20L)).thenReturn(newTtrpg);
        when(spellTtrpgRepository.save(existingSpellTtrpg)).thenReturn(existingSpellTtrpg);
        when(spellTtrpgMapper.spellTtrpgToSpellTtrpgDTO(existingSpellTtrpg)).thenReturn(resultDto);

        SpellTtrpgDTO result = spellTtrpgService.updateSpellTtrpg(100L, updateDto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(10L, result.getSpellId());
        assertEquals(20L, result.getTtrpgId());

        assertEquals(newSpell, existingSpellTtrpg.getSpell());
        assertEquals(newTtrpg, existingSpellTtrpg.getTtrpg());

        verify(spellTtrpgRepository).findById(100L);
        verify(spellRepository).getReferenceById(10L);
        verify(ttrpgRepository).getReferenceById(20L);
        verify(spellTtrpgRepository).save(existingSpellTtrpg);
        verify(spellTtrpgMapper).spellTtrpgToSpellTtrpgDTO(existingSpellTtrpg);
    }

    @Test
    void updateSpellTtrpg_shouldThrowException_whenNotFound() {
        SpellTtrpgDTO updateDto = new SpellTtrpgDTO(10L, 20L);

        when(spellTtrpgRepository.findById(100L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spellTtrpgService.updateSpellTtrpg(100L, updateDto);
        });

        assertEquals("SpellTtrpg not found", exception.getMessage());

        verify(spellTtrpgRepository).findById(100L);
        verify(spellRepository, never()).getReferenceById(anyLong());
        verify(ttrpgRepository, never()).getReferenceById(anyLong());
        verify(spellTtrpgRepository, never()).save(any());
    }

    @Test
    void deleteSpellTtrpg_shouldCallDeleteById() {
        spellTtrpgService.deleteSpellTtrpg(1L);

        verify(spellTtrpgRepository).deleteById(1L);
    }

}
