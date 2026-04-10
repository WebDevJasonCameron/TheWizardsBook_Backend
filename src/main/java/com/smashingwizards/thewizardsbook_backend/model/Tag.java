package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tags")
public class Tag {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "tag_name")
    private String name;

    @Getter @Setter
    @Column(name = "tag_type")
    private String type;

    // CONs
    public Tag() {}
    public Tag(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // OVRs
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
