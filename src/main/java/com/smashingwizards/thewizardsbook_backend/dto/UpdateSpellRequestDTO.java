package com.smashingwizards.thewizardsbook_backend.dto;

import java.util.List;

public class UpdateSpellRequestDTO {
    private SpellDTO spell;
    private List<Long> rpgClassIds;
    private List<Long> tagIds;
    private List<SourceAssignmentDTO> sources;
    private List<Long> ttrpgIds;
}
