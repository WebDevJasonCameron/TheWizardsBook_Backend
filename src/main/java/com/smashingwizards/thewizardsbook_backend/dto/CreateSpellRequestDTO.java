package com.smashingwizards.thewizardsbook_backend.dto;

import java.util.List;

public class CreateSpellRequestDTO {
    private SpellDTO spell;

    private List<Long> rpgClassIds;
    private List<Long> tagIds;
    private List<Long> sources;
    private List<Long> ttrpgIds;

}
