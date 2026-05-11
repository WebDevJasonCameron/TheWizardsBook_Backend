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

        AND (:concentrationEmpty = true OR s.concentration = :concentration)
        AND (:ritualEmpty = true OR s.ritual = :ritual)
        AND (:componentVisualEmpty = true OR s.componentVisual = :componentVisual)
        AND (:componentSemanticEmpty = true OR s.componentSemantic = :componentSemantic)
        AND (:componentMaterialEmpty = true OR s.componentMaterial = :componentMaterial)

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

        AND (:sourceIdsEmpty = true OR EXISTS (
            SELECT ss
            FROM SpellSource ss
            WHERE ss.spell.id = s.id
            AND ss.source.id IN :sourceIds
        ))

        AND (:tagIdsEmpty = true OR EXISTS (
            SELECT stag
            FROM SpellTag stag
            WHERE stag.spell.id = s.id
            AND stag.tag.id IN :tagIds
        ))
        """)
    List<Spell> searchSpells(
            @Param("name") String name,

            @Param("levels") List<String> levels,
            @Param("levelsEmpty") boolean levelsEmpty,

            @Param("concentration") Boolean concentration,
            @Param("concentrationEmpty") boolean concentrationEmpty,

            @Param("ritual") Boolean ritual,
            @Param("ritualEmpty") boolean ritualEmpty,

            @Param("componentVisual") Boolean componentVisual,
            @Param("componentVisualEmpty") boolean componentVisualEmpty,

            @Param("componentSemantic") Boolean componentSemantic,
            @Param("componentSemanticEmpty") boolean componentSemanticEmpty,

            @Param("componentMaterial") Boolean componentMaterial,
            @Param("componentMaterialEmpty") boolean componentMaterialEmpty,

            @Param("classIds") List<Long> classIds,
            @Param("classIdsEmpty") boolean classIdsEmpty,

            @Param("ttrpgIds") List<Long> ttrpgIds,
            @Param("ttrpgIdsEmpty") boolean ttrpgIdsEmpty,

            @Param("sourceIds") List<Long> sourceIds,
            @Param("sourceIdsEmpty") boolean sourceIdsEmpty,

            @Param("tagIds") List<Long> tagIds,
            @Param("tagIdsEmpty") boolean tagIdsEmpty
    );

}
