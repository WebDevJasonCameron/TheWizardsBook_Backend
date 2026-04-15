package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.RpgClassDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.RpgClassMapper;
import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import com.smashingwizards.thewizardsbook_backend.repository.RpgClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RpgClassServiceImplTest {

    @Mock
    private RpgClassRepository rpgClassRepositoryMock;
    @Mock
    private RpgClassMapper rpgClassMapperMock;

    @InjectMocks
    private RpgClassServiceImpl rpgClassService;

    @Test
    void getAllRpgClasses_shouldReturnAllRpgClasses() {
        RpgClass rpgClass = new RpgClass();
        rpgClass.setId(1L);
        rpgClass.setName("Test Class");
        rpgClass.setSubClassName("Test SubClass");
        rpgClass.setDescription("Test Description");

        RpgClassDTO dto = new RpgClassDTO(1L, "Test Class", "Test SubClass", "Test Description");

        when(rpgClassRepositoryMock.findAll()).thenReturn(List.of(rpgClass));
        when(rpgClassMapperMock.rpgClassToRpgClassDTO(rpgClass)).thenReturn(dto);

        List<RpgClassDTO> result = rpgClassService.getAllRpgClasses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Class", result.get(0).getName());
        assertEquals("Test SubClass", result.get(0).getSubClassName());
        assertEquals("Test Description", result.get(0).getDescription());

        Mockito.verify(rpgClassRepositoryMock).findAll();
        Mockito.verify(rpgClassMapperMock).rpgClassToRpgClassDTO(rpgClass);
    }

    @Test
    void getRpgClassById_shouldReturnRpgClassDTO() {
        RpgClass rpgClass = new RpgClass();
        rpgClass.setId(1L);
        rpgClass.setName("Test Class");
        rpgClass.setSubClassName("Test SubClass");
        rpgClass.setDescription("Test Description");

        RpgClassDTO dto = new RpgClassDTO(1L, "Test Class", "Test SubClass", "Test Description");

        when(rpgClassRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(rpgClass));
        when(rpgClassMapperMock.rpgClassToRpgClassDTO(rpgClass)).thenReturn(dto);

        RpgClassDTO result = rpgClassService.getRpgClassById(1L);

        assertNotNull(result);
        assertEquals("Test Class", result.getName());
        assertEquals("Test SubClass", result.getSubClassName());
        assertEquals("Test Description", result.getDescription());

        Mockito.verify(rpgClassRepositoryMock).findById(1L);
        Mockito.verify(rpgClassMapperMock).rpgClassToRpgClassDTO(rpgClass);
    }

    @Test
    void getRpgClassById_shouldThrowException_whenNotFound() {
        when(rpgClassRepositoryMock.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> rpgClassService.getRpgClassById(1L));

        assertEquals("RpgClass not found", exception.getMessage());
        Mockito.verify(rpgClassRepositoryMock).findById(1L);
        Mockito.verifyNoInteractions(rpgClassMapperMock);
    }

    @Test
    void createRpgClass_shouldSaveAndReturnRpgClassDTO() {
        RpgClassDTO dto = new RpgClassDTO(null, "Test Class", "Test SubClass", "Test Description");
        RpgClass rpgClass = new RpgClass();
        rpgClass.setName("Test Class");
        rpgClass.setSubClassName("Test SubClass");
        rpgClass.setDescription("Test Description");

        RpgClass savedRpgClass = new RpgClass();
        savedRpgClass.setId(5L);
        savedRpgClass.setName("Test Class");
        savedRpgClass.setSubClassName("Test SubClass");
        savedRpgClass.setDescription("Test Description");

        RpgClassDTO savedDto = new RpgClassDTO(5L, "Test Class", "Test SubClass", "Test Description");

        when(rpgClassMapperMock.rpgClassDTOToRpgClass(dto)).thenReturn(rpgClass);
        when(rpgClassRepositoryMock.save(rpgClass)).thenReturn(savedRpgClass);
        when(rpgClassMapperMock.rpgClassToRpgClassDTO(savedRpgClass)).thenReturn(savedDto);

        RpgClassDTO result = rpgClassService.createRpgClass(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test Class", result.getName());
        assertEquals("Test SubClass", result.getSubClassName());
        assertEquals("Test Description", result.getDescription());

        Mockito.verify(rpgClassRepositoryMock).save(rpgClass);
        Mockito.verify(rpgClassMapperMock).rpgClassDTOToRpgClass(dto);
        Mockito.verify(rpgClassMapperMock).rpgClassToRpgClassDTO(savedRpgClass);
        Mockito.verifyNoMoreInteractions(rpgClassRepositoryMock, rpgClassMapperMock);
    }

    @Test
    void updateRpgClass_shouldUpdateAndReturnRpgClassDTO_whenFound() {
        RpgClass existingRpgClass = new RpgClass();
        existingRpgClass.setId(1L);
        existingRpgClass.setName("Old Name");
        existingRpgClass.setSubClassName("Old SubClass");
        existingRpgClass.setDescription("Old Description");

        RpgClassDTO updateDto = new RpgClassDTO(null, "New Name", "New SubClass", "New Description");

        RpgClass updatedRpgClass = new RpgClass();
        updatedRpgClass.setId(1L);
        updatedRpgClass.setName("New Name");
        updatedRpgClass.setSubClassName("New SubClass");
        updatedRpgClass.setDescription("New Description");

        RpgClassDTO updatedDto = new RpgClassDTO(1L, "New Name", "New SubClass", "New Description");

        when(rpgClassRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(existingRpgClass));
        when(rpgClassRepositoryMock.save(existingRpgClass)).thenReturn(updatedRpgClass);
        when(rpgClassMapperMock.rpgClassToRpgClassDTO(updatedRpgClass)).thenReturn(updatedDto);

        RpgClassDTO result = rpgClassService.updateRpgClass(1L, updateDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Name", result.getName());
        assertEquals("New SubClass", result.getSubClassName());
        assertEquals("New Description", result.getDescription());

        Mockito.verify(rpgClassRepositoryMock).findById(1L);
        Mockito.verify(rpgClassRepositoryMock).save(existingRpgClass);
        Mockito.verify(rpgClassMapperMock).rpgClassToRpgClassDTO(updatedRpgClass);
    }

    @Test
    void updateRpgClass_shouldThrowException_whenNotFound() {
        RpgClassDTO updateDto = new RpgClassDTO(null, "New Name", "New SubClass", "New Description");

        when(rpgClassRepositoryMock.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> rpgClassService.updateRpgClass(1L, updateDto));

        assertEquals("RpgClass not found", exception.getMessage());

        Mockito.verify(rpgClassRepositoryMock).findById(1L);
        Mockito.verify(rpgClassRepositoryMock, never()).save(any());
    }

    @Test
    void deleteRpgClass_shouldCallRepositoryDeleteById() {
        rpgClassService.deleteRpgClass(1L);

        Mockito.verify(rpgClassRepositoryMock).deleteById(1L);
    }
}
