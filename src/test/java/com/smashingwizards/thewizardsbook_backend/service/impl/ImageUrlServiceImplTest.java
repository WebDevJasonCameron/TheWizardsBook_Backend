package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.ImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.ImageUrlMapper;
import com.smashingwizards.thewizardsbook_backend.model.ImageUrl;
import com.smashingwizards.thewizardsbook_backend.repository.ImageUrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageUrlServiceImplTest {

    @Mock
    private ImageUrlRepository imageUrlRepositoryMock;
    @Mock
    private ImageUrlMapper imageUrlMapperMock;

    @InjectMocks
    private ImageUrlServiceImpl imageUrlService;

    @Test
    void getAllConditionTypes_shouldReturnLstOfConditionDTOs() {
        Instant instant = Instant.now();
        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setId(1L);
        imageUrl.setUrl("Test URL");
        imageUrl.setType("Test Type");
        imageUrl.setHash("Test Hash");
        imageUrl.setCreatedAt(instant);

        ImageUrlDTO dto = new ImageUrlDTO(1L, "Test URL", "Test Type", "Test Hash", instant);

        when(imageUrlRepositoryMock.findAll()).thenReturn(List.of(imageUrl));
        when(imageUrlMapperMock.imageUrlToImageUrlDTO(imageUrl)).thenReturn(dto);

        List<ImageUrlDTO> result = imageUrlService.getAllImageUrls();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test URL", result.get(0).getUrl());
        assertEquals("Test Type", result.get(0).getType());
        assertEquals("Test Hash", result.get(0).getHash());
        assertEquals(instant, result.get(0).getCreatedAt());

        Mockito.verify(imageUrlRepositoryMock).findAll();
        Mockito.verify(imageUrlMapperMock).imageUrlToImageUrlDTO(imageUrl);
    }

    @Test
    void getImageUrlById_shouldReturnImageUrlDTO() {
        Instant instant = Instant.now();

        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setId(1L);
        imageUrl.setUrl("Test URL");
        imageUrl.setType("Test Type");
        imageUrl.setHash("Test Hash");
        imageUrl.setCreatedAt(instant);

        ImageUrlDTO dto = new ImageUrlDTO(1L, "Test URL", "Test Type", "Test Hash", instant);

        when(imageUrlRepositoryMock.findById(1L)).thenReturn(Optional.of(imageUrl));
        when(imageUrlMapperMock.imageUrlToImageUrlDTO(imageUrl)).thenReturn(dto);

        ImageUrlDTO result = imageUrlService.getImageUrlById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test URL", result.getUrl());
        assertEquals("Test Type", result.getType());
        assertEquals("Test Hash", result.getHash());
        assertEquals(instant, result.getCreatedAt());

        Mockito.verify(imageUrlRepositoryMock).findById(1L);
        Mockito.verify(imageUrlMapperMock).imageUrlToImageUrlDTO(imageUrl);
    }

    @Test
    void getImageUrlById_shouldThrowException_whenNotFound() {
        when(imageUrlRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> imageUrlService.getImageUrlById(1L));

        assertEquals("Image not found", exception.getMessage());
        Mockito.verify(imageUrlRepositoryMock).findById(1L);
        Mockito.verify(imageUrlMapperMock, never()).imageUrlToImageUrlDTO(Mockito.any());
    }

    @Test
    void createImageUrl_shouldSaveAndReturnImageUrlDTO() {
        Instant instant = Instant.now();

        ImageUrlDTO dto = new ImageUrlDTO(null, "Test URL", "Test Type", "Test Hash", instant);
        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setUrl("Test URL");
        imageUrl.setType("Test Type");
        imageUrl.setHash("Test Hash");
        imageUrl.setCreatedAt(instant);

        ImageUrl savedImageUrl = new ImageUrl();
        savedImageUrl.setId(1L);
        savedImageUrl.setUrl("Test URL");
        savedImageUrl.setType("Test Type");
        savedImageUrl.setHash("Test Hash");
        savedImageUrl.setCreatedAt(instant);

        ImageUrlDTO savedDto = new ImageUrlDTO(1L, "Test URL", "Test Type", "Test Hash", instant);

        when(imageUrlMapperMock.imageUrlDTOToImageUrl(dto)).thenReturn(imageUrl);
        when(imageUrlRepositoryMock.save(imageUrl)).thenReturn(savedImageUrl);
        when(imageUrlMapperMock.imageUrlToImageUrlDTO(savedImageUrl)).thenReturn(savedDto);

        ImageUrlDTO result = imageUrlService.createImageUrl(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test URL", result.getUrl());
        assertEquals("Test Type", result.getType());
        assertEquals("Test Hash", result.getHash());
        assertEquals(instant, result.getCreatedAt());

        Mockito.verify(imageUrlMapperMock).imageUrlDTOToImageUrl(dto);
        Mockito.verify(imageUrlRepositoryMock).save(imageUrl);
        Mockito.verify(imageUrlMapperMock).imageUrlToImageUrlDTO(savedImageUrl);
    }

    @Test
    void updateImageUrl_shouldUpdateAndReturnImageUrlDTO_whenFound() {
        Instant instant = Instant.now();

        ImageUrl existingImageUrl = new ImageUrl();
        existingImageUrl.setId(1L);
        existingImageUrl.setUrl("Old URL");
        existingImageUrl.setType("Old Type");
        existingImageUrl.setHash("Old Hash");
        existingImageUrl.setCreatedAt(instant);

        ImageUrlDTO updateDto = new ImageUrlDTO(null, "New URL", "New Type", "New Hash", instant);

        ImageUrl updatedImageUrl = new ImageUrl();
        updatedImageUrl.setId(1L);
        updatedImageUrl.setUrl("New URL");
        updatedImageUrl.setType("New Type");
        updatedImageUrl.setHash("New Hash");
        updatedImageUrl.setCreatedAt(instant);

        ImageUrlDTO updatedDto = new ImageUrlDTO(1L, "New URL", "New Type", "New Hash", instant);

        when(imageUrlRepositoryMock.findById(1L)).thenReturn(Optional.of(existingImageUrl));
        when(imageUrlRepositoryMock.save(existingImageUrl)).thenReturn(updatedImageUrl);
        when(imageUrlMapperMock.imageUrlToImageUrlDTO(updatedImageUrl)).thenReturn(updatedDto);

        ImageUrlDTO result = imageUrlService.updateImageUrl(1L, updateDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New URL", result.getUrl());
        assertEquals("New Type", result.getType());
        assertEquals("New Hash", result.getHash());
        assertEquals(instant, result.getCreatedAt());

        Mockito.verify(imageUrlRepositoryMock).findById(1L);
        Mockito.verify(imageUrlRepositoryMock).save(existingImageUrl);
        Mockito.verify(imageUrlMapperMock).imageUrlToImageUrlDTO(updatedImageUrl);
    }

    @Test
    void updateImageUrl_shouldThrowException_whenNotFound() {
        Instant instant = Instant.now();
        ImageUrlDTO updateDto = new ImageUrlDTO(null, "New URL", "New Type", "New Hash", instant);

        when(imageUrlRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> imageUrlService.updateImageUrl(1L, updateDto));

        assertEquals("Image not found", exception.getMessage());

        Mockito.verify(imageUrlRepositoryMock).findById(1L);
        Mockito.verify(imageUrlRepositoryMock, never()).save(Mockito.any());
    }

    @Test
    void deleteImageUrl_shouldCallRepositoryDeleteById() {
        imageUrlService.deleteImageUrl(1L);

        Mockito.verify(imageUrlRepositoryMock).deleteById(1L);
    }
}
