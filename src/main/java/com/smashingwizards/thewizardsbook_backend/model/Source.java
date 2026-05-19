package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_name")
    private String name;

    @Column(name = "source_publish_date")
    private String publishDate;

    @Column(name = "source_publisher")
    private String publisher;

    // CONs
    public Source() {}
    public Source(String name, String publishDate, String publisher) {
        this.name = name;
        this.publishDate = publishDate;
        this.publisher = publisher;
    }

    // OVRs
    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
