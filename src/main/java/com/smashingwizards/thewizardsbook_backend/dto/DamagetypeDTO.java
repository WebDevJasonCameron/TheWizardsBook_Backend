package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class DamagetypeDTO {

    private Long id;
    private String name;

    // CONs
    public DamagetypeDTO(){}
    public DamagetypeDTO(String name){
        this.name = name;
    }
    public DamagetypeDTO(Long id, String name){
        this.id = id;
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
