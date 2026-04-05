package com.smashingwizards.thewizardsbook_backend.dto;

public class SourceDTO {

    // ATTs
    private Long id;
    private String name;
    private String publishDate;
    private String publisher;
    private Long ttrpg;

    // CONs
    public SourceDTO() {
    }
    public SourceDTO(String name, String publishDate, String publisher, Long ttrpg) {
        this.name = name;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.ttrpg = ttrpg;
    }

    // GETs & SETs
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getTtrpg() {
        return ttrpg;
    }
    public void setTtrpg(Long ttrpg) {
        this.ttrpg = ttrpg;
    }
}
