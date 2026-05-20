package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import com.smashingwizards.thewizardsbook_backend.model.SpellSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellSourceRepository extends JpaRepository<SpellSource, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

    /** ADDs */
    List<SpellSource> findAllBySource_NameContainingIgnoreCase(String name);

    List<SpellSource> findAllBySpellId(Long spellId);
    void deleteAllBySpellId(Long spellId);


}
