package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sources")
public class Source {

    // ATTs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_name")
    private String name;
    @Column(name = "source_publish_date")
    private String publishDate;
    @Column(name = "source_publisher")
    private String publisher;
    @Column(name = "source_ttrpg")
    private Long ttrpg;

    // CONs
    public Source() {
    }
    public Source(String name, String publishDate, String publisher, Long ttrpg) {
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

    // OVRs
    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ttrpg=" + ttrpg +
                '}';
    }
}
