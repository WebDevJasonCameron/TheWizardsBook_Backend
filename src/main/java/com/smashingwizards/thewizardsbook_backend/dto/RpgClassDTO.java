package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RpgClassDTO {

    private Long id;
    private String name;
    private String subClassName;
    private String description;

    // CONs
    public RpgClassDTO() {}
    public RpgClassDTO(String name, String subClassName, String description) {}
    public RpgClassDTO(Long id, String name, String subClassName, String description) {
        this.id = id;
        this.name = name;
        this.subClassName = subClassName;
        this.description = description;
    }

    // OVRs
    @Override
    public String toString() {
        return "RpgClassDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subClassName='" + subClassName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
