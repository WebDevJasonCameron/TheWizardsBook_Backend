package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SpellDetailsDTO {

    private SpellDTO spell;
    private List<RpgClassDTO> rpgClasses;
    private List<TagDTO> tags;
    private List<SourceDTO> sources;
    private List<TtrpgDTO> ttrpgs;

    // CONs
    public SpellDetailsDTO() {}
    public SpellDetailsDTO(SpellDTO spell, List<RpgClassDTO> rpgClasses, List<TagDTO> tags, List<SourceDTO> sources, List<TtrpgDTO> ttrpgs) {
        this.spell = spell;
        this.rpgClasses = rpgClasses;
        this.tags = tags;
        this.sources = sources;
        this.ttrpgs = ttrpgs;
    }

    // OVRs
    @Override
    public String toString() {
        return "SpellDetailsDTO{" +
                "spell=" + spell +
                ", rpgClasses=" + rpgClasses +
                ", tags=" + tags +
                ", sources=" + sources +
                ", ttrpgs=" + ttrpgs +
                '}';
    }
}
