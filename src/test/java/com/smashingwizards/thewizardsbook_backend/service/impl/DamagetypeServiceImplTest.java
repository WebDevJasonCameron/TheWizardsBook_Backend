package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.DamagetypeDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.DamagetypeMapper;
import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import com.smashingwizards.thewizardsbook_backend.repository.DamagetypeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DamagetypeServiceImplTest {

    @Mock
    private DamagetypeRepository damagetypeRepositoryMock;
    @Mock
    private DamagetypeMapper damagetypeMapperMock;

    @InjectMocks
    private DamagetypeServiceImpl damagetypeService;

    @Test
    void getAllDamagetypes_shouldReturnLstOfConditionDTOs() {
        Damagetype damagetype = new Damagetype();
        damagetype.setId(1L);
        damagetype.setName("Test Damagetype");

        DamagetypeDTO dto = new DamagetypeDTO("Test Damagetype");

        when(damagetypeRepositoryMock.findAll()).thenReturn(List.of(damagetype));
        when(damagetypeMapperMock.damagetypeToDamagetypeDTO(damagetype)).thenReturn(dto);

        List<DamagetypeDTO> result = damagetypeService.getAllDamagetypes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Damagetype", result.get(0).getName());

        Mockito.verify(damagetypeRepositoryMock).findAll();
        Mockito.verify(damagetypeMapperMock).damagetypeToDamagetypeDTO(damagetype);
    }

    @Test
    void getDamagetypeById_shouldReturnDamagetypeDTO() {
        Damagetype damagetype = new Damagetype();
        damagetype.setId(1L);
        damagetype.setName("Test Damagetype");

        DamagetypeDTO dto = new DamagetypeDTO(1L, "Test Damagetype");
        when(damagetypeRepositoryMock.findById(1L)).thenReturn(Optional.of(damagetype));
        when(damagetypeMapperMock.damagetypeToDamagetypeDTO(damagetype)).thenReturn(dto);

        DamagetypeDTO result = damagetypeService.getDamagetypeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Damagetype", result.getName());

        Mockito.verify(damagetypeRepositoryMock).findById(1L);
        Mockito.verify(damagetypeMapperMock).damagetypeToDamagetypeDTO(damagetype);
    }

    @Test
    void getDamagetypeById_shouldThrowException_whenNotFound() {
        when(damagetypeRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> damagetypeService.getDamagetypeById(1L));

        assertEquals("Damagetype not found", exception.getMessage());
        Mockito.verify(damagetypeRepositoryMock).findById(1L);
        Mockito.verifyNoInteractions(damagetypeMapperMock);
    }

    @Test
    void createDamageType_shouldSaveAndReturnDamagetypeDTO() {
        DamagetypeDTO dto = new DamagetypeDTO(null, "Test Damagetype");
        Damagetype damagetype = new Damagetype("Test Damagetype");
        damagetype.setName("Test Damagetype");

        Damagetype savedDamagetype = new Damagetype();
        savedDamagetype.setId(5L);
        savedDamagetype.setName("Test Damagetype");

        when(damagetypeMapperMock.damagetypeDTOToDamagetype(dto)).thenReturn(damagetype);
        when(damagetypeRepositoryMock.save(damagetype)).thenReturn(savedDamagetype);
        when(damagetypeMapperMock.damagetypeToDamagetypeDTO(savedDamagetype)).thenReturn(dto);

        DamagetypeDTO result = damagetypeService.createDamagetype(dto);

        assertNotNull(result);
        assertEquals(null, result.getId());
        assertEquals("Test Damagetype", result.getName());

        Mockito.verify(damagetypeMapperMock).damagetypeDTOToDamagetype(dto);
        Mockito.verify(damagetypeRepositoryMock).save(damagetype);
        Mockito.verify(damagetypeMapperMock).damagetypeToDamagetypeDTO(savedDamagetype);
    }

    @Test
    void updateDamagetype_shouldUpdateAndReturnDamagetypeDTO_whenFound() {
        Damagetype existingDamagetype = new Damagetype();
        existingDamagetype.setId(1L);
        existingDamagetype.setName("Old Name");

        DamagetypeDTO updateDto = new DamagetypeDTO(null, "New Name");

        Damagetype updatedDamagetype = new Damagetype();
        updatedDamagetype.setId(1L);
        updatedDamagetype.setName("New Name");

        DamagetypeDTO updatedDto = new DamagetypeDTO(1L, "New Name");

        when(damagetypeRepositoryMock.findById(1L)).thenReturn(Optional.of(existingDamagetype));
        when(damagetypeRepositoryMock.save(existingDamagetype)).thenReturn(updatedDamagetype);
        when(damagetypeMapperMock.damagetypeToDamagetypeDTO(updatedDamagetype)).thenReturn(updatedDto);

        DamagetypeDTO result = damagetypeService.updateDamagetype(1L, updateDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());

        Mockito.verify(damagetypeRepositoryMock).findById(1L);
        Mockito.verify(damagetypeRepositoryMock).save(existingDamagetype);
        Mockito.verify(damagetypeMapperMock).damagetypeToDamagetypeDTO(updatedDamagetype);
    }

    @Test
    void updateDamagetype_shouldThrowException_whenNotFound() {
        DamagetypeDTO updateDto = new DamagetypeDTO(null, "New Name");

        when(damagetypeRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> damagetypeService.updateDamagetype(1L, updateDto));

        assertEquals("Damagetype not found", exception.getMessage());

        Mockito.verify(damagetypeRepositoryMock).findById(1L);
        Mockito.verify(damagetypeRepositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deleteDamagetype_shouldCallRepositoryDeleteById() {
        damagetypeService.deleteDamagetype(1L);

        Mockito.verify(damagetypeRepositoryMock).deleteById(1L);
    }

    /** ADDs */
    @Test
    @DisplayName("getAllByNameContainingIgnoreCase() should return matching Damagetypes")
    void getAllByNameContainingIgnoreCase_shouldReturnMatchingDamagetypes() {
        DamagetypeRepository damagetypeRepository = mock(DamagetypeRepository.class);
        DamagetypeMapper damagetypeMapper = mock(DamagetypeMapper.class);

        damagetypeService = new DamagetypeServiceImpl(damagetypeRepository, damagetypeMapper);

        Damagetype damagetype1 = new Damagetype("Test Name 1");
        Damagetype damagetype2 = new Damagetype("Test Name 2");

        DamagetypeDTO dto1 = new DamagetypeDTO(1L, "Test Name 1");
        DamagetypeDTO dto2 = new DamagetypeDTO(2L, "Test Name 2");

        when(damagetypeRepository.findAllByNameContainingIgnoreCase("Test")).thenReturn(List.of(damagetype1, damagetype2));
        when(damagetypeMapper.damagetypeToDamagetypeDTO(damagetype1)).thenReturn(dto1);
        when(damagetypeMapper.damagetypeToDamagetypeDTO(damagetype2)).thenReturn(dto2);

        List<DamagetypeDTO> result = damagetypeService.getAllByNameContainingIgnoreCase("Test");

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Test Name 1", result.get(0).getName());
        assertEquals("Test Name 2", result.get(1).getName());

        verify(damagetypeRepository).findAllByNameContainingIgnoreCase("Test");
        verify(damagetypeMapper).damagetypeToDamagetypeDTO(damagetype1);
        verify(damagetypeMapper).damagetypeToDamagetypeDTO(damagetype2);
    }
}
