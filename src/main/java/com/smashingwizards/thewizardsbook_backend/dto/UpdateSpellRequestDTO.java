package com.smashingwizards.thewizardsbook_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UpdateSpellRequestDTO {
    private SpellDTO spell;
    private List<Long> rpgClassIds;
    private List<Long> tagIds;
    private List<SourceAssignmentDTO> sources;
    private List<Long> ttrpgIds;
}
