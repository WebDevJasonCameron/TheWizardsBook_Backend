package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class SourceDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String publishDate;

    @Getter @Setter
    private String publisher;

    // CONs
    public SourceDTO() {}
    public SourceDTO(String name, String publishDate, String publisher) {
        this.name = name;
        this.publishDate = publishDate;
        this.publisher = publisher;
    }
    public SourceDTO(Long id, String name, String publishDate, String publisher) {
        this.id = id;
        this.name = name;
        this.publishDate = publishDate;
        this.publisher = publisher;
    }

    // OVRs
    @Override
    public String toString() {
        return "SourceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

}
