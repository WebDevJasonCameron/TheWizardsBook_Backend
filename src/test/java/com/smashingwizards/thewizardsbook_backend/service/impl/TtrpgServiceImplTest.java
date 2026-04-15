package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.TtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.TtrpgMapper;
import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import com.smashingwizards.thewizardsbook_backend.repository.TtrpgRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TtrpgServiceImplTest {

    @Mock
    private TtrpgRepository ttrpgRepositoryMock;
    @Mock
    private TtrpgMapper ttrpgMapperMock;

    @InjectMocks
    private TtrpgServiceImpl ttrpgService;

    @Test
    void getAllTtrpgs_shouldReturnLstOfTtrpgDTOs() {
        Ttrpg ttrpg = new Ttrpg();
        ttrpg.setId(1L);
        ttrpg.setName("Test TTRPG");
        ttrpg.setVersion("Test Version");

        TtrpgDTO dto = new TtrpgDTO(1L, "Test TTRPG", "Test Version");

        when(ttrpgRepositoryMock.findAll()).thenReturn(List.of(ttrpg));
        when(ttrpgMapperMock.ttrpgToTtrpgDTO(ttrpg)).thenReturn(dto);

        List<TtrpgDTO> result = ttrpgService.getAllTtrpgs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test TTRPG", result.get(0).getName());
        assertEquals("Test Version", result.get(0).getVersion());

        verify(ttrpgRepositoryMock).findAll();
        verify(ttrpgMapperMock).ttrpgToTtrpgDTO(ttrpg);
    }

    @Test
    void getTtrpgById_shouldReturnTtrpgDTO() {
        Ttrpg ttrpg = new Ttrpg();
        ttrpg.setId(1L);
        ttrpg.setName("Test TTRPG");
        ttrpg.setVersion("Test Version");

        TtrpgDTO dto = new TtrpgDTO(1L, "Test TTRPG", "Test Version");

        when(ttrpgRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(ttrpg));
        when(ttrpgMapperMock.ttrpgToTtrpgDTO(ttrpg)).thenReturn(dto);

        TtrpgDTO result = ttrpgService.getTtrpgById(1L);

        assertNotNull(result);
        assertEquals("Test TTRPG", result.getName());
        assertEquals("Test Version", result.getVersion());

        verify(ttrpgRepositoryMock).findById(1L);
        verify(ttrpgMapperMock).ttrpgToTtrpgDTO(ttrpg);
    }

    @Test
    void getTtrpgById_shouldThrowException_whenNotFound() {
        when(ttrpgRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ttrpgService.getTtrpgById(1L));

        assertEquals("Ttrpg not found", exception.getMessage());
        verify(ttrpgRepositoryMock).findById(1L);
        verify(ttrpgMapperMock, never()).ttrpgToTtrpgDTO(any());
    }

    @Test
    void createTtrpg_shouldSaveAndReturnTtrpgDTO() {
        TtrpgDTO dto = new TtrpgDTO(null, "Test TTRPG", "Test Version");
        Ttrpg ttrpg = new Ttrpg();
        ttrpg.setName("Test TTRPG");
        ttrpg.setVersion("Test Version");

        Ttrpg savedTtrpg = new Ttrpg();
        savedTtrpg.setId(5L);
        savedTtrpg.setName("Test TTRPG");
        savedTtrpg.setVersion("Test Version");

        TtrpgDTO savedDto = new TtrpgDTO(5L, "Test TTRPG", "Test Version");

        when(ttrpgMapperMock.ttrpgDTOToTtrpg(dto)).thenReturn(ttrpg);
        when(ttrpgRepositoryMock.save(ttrpg)).thenReturn(savedTtrpg);
        when(ttrpgMapperMock.ttrpgToTtrpgDTO(savedTtrpg)).thenReturn(savedDto);

        TtrpgDTO result = ttrpgService.createTtrpg(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test TTRPG", result.getName());
        assertEquals("Test Version", result.getVersion());

        verify(ttrpgMapperMock).ttrpgDTOToTtrpg(dto);
        verify(ttrpgRepositoryMock).save(ttrpg);
        verify(ttrpgMapperMock).ttrpgToTtrpgDTO(savedTtrpg);
    }

    @Test
    void updateTtrpg_shouldUpdateAndReturnTtrpgDTO_whenFound() {
        Ttrpg existingTtrpg = new Ttrpg();
        existingTtrpg.setId(1L);
        existingTtrpg.setName("Old Name");
        existingTtrpg.setVersion("Old Version");

        TtrpgDTO updateDto = new TtrpgDTO(null, "New Name", "New Version");

        Ttrpg updatedTtrpg = new Ttrpg();
        updatedTtrpg.setId(1L);
        updatedTtrpg.setName("New Name");
        updatedTtrpg.setVersion("New Version");

        TtrpgDTO updatedDto = new TtrpgDTO(1L, "New Name", "New Version");

        when(ttrpgRepositoryMock.findById(1L)).thenReturn(Optional.of(existingTtrpg));
        when(ttrpgRepositoryMock.save(existingTtrpg)).thenReturn(updatedTtrpg);
        when(ttrpgMapperMock.ttrpgToTtrpgDTO(updatedTtrpg)).thenReturn(updatedDto);

        TtrpgDTO result = ttrpgService.updateTtrpg(1L, updateDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("New Version", result.getVersion());

        verify(ttrpgRepositoryMock).findById(1L);
        verify(ttrpgRepositoryMock).save(existingTtrpg);
        verify(ttrpgMapperMock).ttrpgToTtrpgDTO(updatedTtrpg);
    }

    @Test
    void updateTtrpg_shouldThrowException_whenNotFound() {
        TtrpgDTO updateDto = new TtrpgDTO(null, "New Name", "New Version");

        when(ttrpgRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ttrpgService.updateTtrpg(1L, updateDto));

        assertEquals("Ttrpg not found", exception.getMessage());

        verify(ttrpgRepositoryMock).findById(1L);
        verify(ttrpgRepositoryMock, never()).save(any());
        verify(ttrpgMapperMock, never()).ttrpgToTtrpgDTO(any());
    }

    @Test
    void deleteTtrpg_shouldCallRepositoryDeleteById() {
        ttrpgService.deleteTtrpg(1L);

        verify(ttrpgRepositoryMock).deleteById(1L);
    }
}
