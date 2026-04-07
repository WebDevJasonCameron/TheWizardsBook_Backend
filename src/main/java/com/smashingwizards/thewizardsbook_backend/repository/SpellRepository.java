package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> , JpaSpecificationExecutor<Spell> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

    // Simple list (no pagination)
    List<Spell> findByNameContainingIgnoreCase(String name);
    List<Spell> findAllByTtrpgId(Long ttrpgId);
    List<Spell> findAllByLevel(String level);
    List<Spell> findAllByConcentration(boolean concentration);
    List<Spell> findAllByRitual(boolean ritual);
    List<Spell> findAllBySchool(String school);
    List<Spell> findAllBySourceId(Long sourceId);
}
