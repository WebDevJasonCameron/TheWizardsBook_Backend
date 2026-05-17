package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConditionDTO {

    private Long id;
    private String name;
    private String description;

    // CONs
    public ConditionDTO() {}
    public ConditionDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // OVRs
    @Override
    public String toString() {
        return "ConditionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
