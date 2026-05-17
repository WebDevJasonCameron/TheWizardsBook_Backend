package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TagDTO {

    private Long id;
    private String name;
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
