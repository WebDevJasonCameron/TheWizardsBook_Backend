package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class TagDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String type;

    // CONs
    public TagDTO() {}
    public TagDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public TagDTO(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // OVRs
    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
