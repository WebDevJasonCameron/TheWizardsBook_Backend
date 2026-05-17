package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class ImageUrlDTO {

    private Long id;
    private String url;
    private String type;
    private String hash;
    private Instant createdAt;

    // CONs
    public ImageUrlDTO() {}
    public ImageUrlDTO(String url, String type, String hash, Instant createdAt) {
        this.url = url;
        this.type = type;
        this.hash = hash;
        this.createdAt = createdAt;
    }
    public ImageUrlDTO(Long id, String url, String type, String hash, Instant createdAt) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.hash = hash;
        this.createdAt = createdAt;
    }

    // OVRs
    @Override
    public String toString() {
        return "ImageUrlDTO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", hash='" + hash + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}
