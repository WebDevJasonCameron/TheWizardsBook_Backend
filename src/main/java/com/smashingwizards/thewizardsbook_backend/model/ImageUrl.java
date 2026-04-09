package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "image_urls")
public class ImageUrl {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "image_url")
    private String url;

    @Getter @Setter
    @Column(name = "image_type")
    private String type;

    @Getter @Setter
    @Column(name = "image_hash")
    private String hash;

    @Getter @Setter
    @Column(name = "image_created_at")
    private Instant createdAt;

    // CONs
    public ImageUrl() {}
    public ImageUrl(String url, String type, String hash, Instant createdAt) {
        this.url = url;
        this.type = type;
        this.hash = hash;
        this.createdAt = createdAt;
    }

    // OVRs
    @Override
    public String toString() {
        return "ImageUrl{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", hash='" + hash + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
