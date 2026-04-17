package com.smashingwizards.thewizardsbook_backend.controller;

import com.smashingwizards.thewizardsbook_backend.dto.ImageUrlDTO;
import com.smashingwizards.thewizardsbook_backend.service.ImageUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imageurls")
public class ImageUrlController {

    private final ImageUrlService imageUrlService;

    public ImageUrlController(ImageUrlService imageUrlService) {
        this.imageUrlService = imageUrlService;
    }

    @GetMapping
    public ResponseEntity<List<ImageUrlDTO>> getAllImageUrls() {
        return ResponseEntity.ok(imageUrlService.getAllImageUrls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageUrlDTO> getImageUrlById(@PathVariable Long id) {
        return ResponseEntity.ok(imageUrlService.getImageUrlById(id));
    }

    @PostMapping
    public ResponseEntity<ImageUrlDTO> createImageUrl(@RequestBody ImageUrlDTO imageUrlDTO) {
        ImageUrlDTO createdImageUrl = imageUrlService.createImageUrl(imageUrlDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdImageUrl);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageUrlDTO> updateImageUrl(@PathVariable Long id, @RequestBody ImageUrlDTO imageUrlDTO) {
        ImageUrlDTO updatedImageUrl = imageUrlService.updateImageUrl(id, imageUrlDTO);
        return ResponseEntity.ok(updatedImageUrl);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImageUrl(@PathVariable Long id) {
        imageUrlService.deleteImageUrl(id);
        return ResponseEntity.noContent().build();
    }
}
