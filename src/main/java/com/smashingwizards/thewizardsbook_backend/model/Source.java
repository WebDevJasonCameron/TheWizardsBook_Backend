package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sources")
public class Source {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "source_name")
    private String name;

    @Getter @Setter
    @Column(name = "source_publish_date")
    private String publishData;

    @Getter @Setter
    @Column(name = "source_publisher")
    private String publisher;

    @Getter @Setter
    @Column(name = "source_ttrpg")
    private Long ttrpg;

    // CONs
    public Source() {}
    public Source(String name, String publishData, String publisher, Long ttrpg) {
        this.name = name;
        this.publishData = publishData;
        this.publisher = publisher;
        this.ttrpg = ttrpg;
    }

    // OVRs
    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishData='" + publishData + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ttrpg=" + ttrpg +
                '}';
    }
}
