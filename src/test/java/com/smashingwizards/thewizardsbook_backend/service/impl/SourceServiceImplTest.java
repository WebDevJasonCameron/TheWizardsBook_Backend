package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SourceDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SourceMapper;
import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.repository.SourceRepository;
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
public class SourceServiceImplTest {

    @Mock
    private SourceRepository sourceRepositoryMock;
    @Mock
    private SourceMapper sourceMapperMock;

    @InjectMocks
    private SourceServiceImpl sourceService;

    @Test
    void getAllSources_shouldReturnLstOfConditionDTOs() {
        Source source = new Source();
        source.setId(1L);
        source.setName("Test Source");
        source.setPublishDate("Test PublishDate");
        source.setPublisher("Test Publisher");

        SourceDTO dto = new SourceDTO(1L, "Test Source", "Test PublishDate", "Test Publisher");

        when(sourceRepositoryMock.findAll()).thenReturn(List.of(source));
        when(sourceMapperMock.sourceToSourceDTO(source)).thenReturn(dto);

        List<SourceDTO> result = sourceService.getAllSources();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Source", result.get(0).getName());
        assertEquals("Test PublishDate", result.get(0).getPublishDate());
        assertEquals("Test Publisher", result.get(0).getPublisher());

        verify(sourceRepositoryMock).findAll();
        verify(sourceMapperMock).sourceToSourceDTO(source);
    }

    @Test
    void getSourceById_shouldReturnSourceDTO() {
        Source source = new Source();
        source.setId(1L);
        source.setName("Test Source");
        source.setPublishDate("Test PublishDate");
        source.setPublisher("Test Publisher");

        SourceDTO dto = new SourceDTO(1L, "Test Source", "Test PublishDate", "Test Publisher");

        when(sourceRepositoryMock.findById(1L)).thenReturn(Optional.of(source));
        when(sourceMapperMock.sourceToSourceDTO(source)).thenReturn(dto);

        SourceDTO result = sourceService.getSourceById(1L);

        assertNotNull(result);
        assertEquals("Test Source", result.getName());
        assertEquals("Test PublishDate", result.getPublishDate());
        assertEquals("Test Publisher", result.getPublisher());

        verify(sourceRepositoryMock).findById(1L);
        verify(sourceMapperMock).sourceToSourceDTO(source);
    }

    @Test
    void getSourceById_shouldThrowException_whenNotFound() {
        when(sourceRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class, () -> sourceService.getSourceById(1L));

        assertEquals("Source not found", e.getMessage());
        verify(sourceRepositoryMock).findById(1L);
        verify(sourceMapperMock, never()).sourceToSourceDTO(any());
    }

    @Test
    void createSource_shouldSaveAndReturnSourceDTO() {
        SourceDTO dto = new SourceDTO(null, "Test Source", "Test PublishDate", "Test Publisher");

        Source source = new Source();
        source.setName("Test Source");
        source.setPublishDate("Test PublishDate");
        source.setPublisher("Test Publisher");

        Source savedSource = new Source();
        savedSource.setId(5L);
        savedSource.setName("Test Source");
        savedSource.setPublishDate("Test PublishDate");
        savedSource.setPublisher("Test Publisher");

        SourceDTO savedDto = new SourceDTO(5L, "Test Source", "Test PublishDate", "Test Publisher");

        when(sourceMapperMock.sourceDTOToSource(dto)).thenReturn(source);
        when(sourceRepositoryMock.save(source)).thenReturn(savedSource);
        when(sourceMapperMock.sourceToSourceDTO(savedSource)).thenReturn(savedDto);

        SourceDTO result = sourceService.createSource(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test Source", result.getName());
        assertEquals("Test PublishDate", result.getPublishDate());
        assertEquals("Test Publisher", result.getPublisher());

        Mockito.verify(sourceMapperMock).sourceDTOToSource(dto);
        Mockito.verify(sourceRepositoryMock).save(source);
        Mockito.verify(sourceMapperMock).sourceToSourceDTO(savedSource);
    }

    @Test
    void updateSource_ShouldUpdateAndReturnSourceDTO_WhenFound() {
        Source existingSource = new Source();
        existingSource.setId(1L);
        existingSource.setName("Old Name");
        existingSource.setPublishDate("Old PublishDate");
        existingSource.setPublisher("Old Publisher");

        SourceDTO updateDto = new SourceDTO(null, "New Name", "New PublishDate", "New Publisher");

        Source updatedSource = new Source();
        updatedSource.setId(1L);
        updatedSource.setName("New Name");
        updatedSource.setPublishDate("New PublishDate");
        updatedSource.setPublisher("New Publisher");

        SourceDTO updatedDto = new SourceDTO(1L, "New Name", "New PublishDate", "New Publisher");

        when(sourceRepositoryMock.findById(1L)).thenReturn(Optional.of(existingSource));
        when(sourceRepositoryMock.save(existingSource)).thenReturn(updatedSource);
        when(sourceMapperMock.sourceToSourceDTO(updatedSource)).thenReturn(updatedDto);

        SourceDTO result = sourceService.updateSource(1L, updateDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Name", result.getName());
        assertEquals("New PublishDate", result.getPublishDate());
        assertEquals("New Publisher", result.getPublisher());

        Mockito.verify(sourceRepositoryMock).findById(1L);
        Mockito.verify(sourceRepositoryMock).save(existingSource);
        Mockito.verify(sourceMapperMock).sourceToSourceDTO(updatedSource);
    }

    @Test
    void updateSource_ShouldThrowException_WhenNotFound() {
        SourceDTO updateDto = new SourceDTO(null, "New Name", "New PublishDate", "New Publisher");

        when(sourceRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> sourceService.updateSource(1L, updateDto));

        assertEquals("Source not found", exception.getMessage());

        verify(sourceRepositoryMock).findById(1L);
        verify(sourceRepositoryMock, never()).save(any());
    }

    @Test
    void deleteSource_shouldCallRepositoryDeleteById() {
        sourceService.deleteSource(1L);

        Mockito.verify(sourceRepositoryMock).deleteById(1L);
    }

}
