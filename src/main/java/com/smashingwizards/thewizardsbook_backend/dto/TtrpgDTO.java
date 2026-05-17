package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TtrpgDTO {

    private Long id;
    private String name;
    private String version;

    // CONs
    public TtrpgDTO() {}
    public TtrpgDTO(String name, String version) {}
    public TtrpgDTO(Long id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    // OVRs
    @Override
    public String toString() {
        return "TtrpgDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
