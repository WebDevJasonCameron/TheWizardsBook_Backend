package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TypeDTO {

    private Long id;
    private String name;
    private String subType;

    // CONs
    public TypeDTO() {}
    public TypeDTO(String name, String subType) {
        this.name = name;
        this.subType = subType;
    }
    public TypeDTO(Long id, String name, String subType) {
        this.id = id;
        this.name = name;
        this.subType = subType;
    }

    // OVRs
    @Override
    public String toString() {
        return "TypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subType='" + subType + '\'' +
                '}';
    }
}
