package com.smashingwizards.thewizardsbook_backend.dto;

import com.smashingwizards.thewizardsbook_backend.model.Damagetype;
import lombok.Getter;
import lombok.Setter;

public class DamagetypeDTO {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    // CONs
    public DamagetypeDTO(){}
    public DamagetypeDTO(String name){
        this.name = name;
    }

    // OVRs
    @Override
    public String toString() {
        return "DamagetypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
