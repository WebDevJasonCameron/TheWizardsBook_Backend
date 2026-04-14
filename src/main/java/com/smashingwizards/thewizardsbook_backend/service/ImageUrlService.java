package com.smashingwizards.thewizardsbook_backend.service;

import com.smashingwizards.thewizardsbook_backend.dto.ImageUrlDTO;

import java.util.List;

public interface ImageUrlService {
    List<ImageUrlDTO> getAllImageUrls();
    ImageUrlDTO getImageUrlById(Long id);
    ImageUrlDTO createImageUrl(ImageUrlDTO imageUrlDTO);
    ImageUrlDTO updateImageUrl(Long id, ImageUrlDTO imageUrlDTO);
    void deleteImageUrl(Long id);
}
