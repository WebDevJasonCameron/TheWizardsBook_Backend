package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDetailsDTO;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

    /** ADDs */
    List<Spell> findAllByNameContainingIgnoreCase(String name);

    @Query("""
        SELECT DISTINCT s
        FROM Spell s
        WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:levelsEmpty = true OR s.level IN :levels)
        AND (:classIdsEmpty = true OR EXISTS (
            SELECT sc
            FROM SpellClass sc
            WHERE sc.spell.id = s.id
            AND sc.rpgClass.id IN :classIds
        ))
        AND (:ttrpgIdsEmpty = true OR EXISTS (
            SELECT st
            FROM SpellTtrpg st
            WHERE st.spell.id = s.id
            AND st.ttrpg.id IN :ttrpgIds
        ))
        """)
    List<Spell> searchSpells(
            @Param("name") String name,
            @Param("levels") List<String> levels,
            @Param("levelsEmpty") boolean levelsEmpty,
            @Param("classIds") List<Long> classIds,
            @Param("classIdsEmpty") boolean classIdsEmpty,
            @Param("ttrpgIds") List<Long> ttrpgIds,
            @Param("ttrpgIdsEmpty") boolean ttrpgIdsEmpty
    );
}
