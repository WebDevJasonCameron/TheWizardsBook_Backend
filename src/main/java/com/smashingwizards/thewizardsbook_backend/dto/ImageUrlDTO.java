package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

public class ImageUrlDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String url;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private String hash;

    @Getter @Setter
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
