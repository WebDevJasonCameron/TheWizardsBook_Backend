package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Source;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SpellSourceRepository {

    @Autowired
    public SpellSourceRepository underTest;
    @Autowired
    public SpellRepository spellRepository;
    @Autowired
    public SourceRepository sourceRepository;

    @Test
    @DisplayName("Repository loads successfully")
    void repositoryLoads() { assertNotNull(underTest); }

    @Test
    @DisplayName("save() should persist a SpellSource")
    void save_shouldPersistSpellSource() {
        Spell spell = new Spell("name", "level", "casting time", "range", false, false, false, "", "duration", false, false, "school", "description", 1L);
        Source source = new Source("source name", "publish date", "source publisher", 1L, "1");
    }
}
