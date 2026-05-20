package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import com.smashingwizards.thewizardsbook_backend.model.SpellSource;
import com.smashingwizards.thewizardsbook_backend.model.SpellTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellTagRepository extends JpaRepository<SpellTag, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

    /** ADDs */
    List<SpellTag> findAllByTag_NameContainingIgnoreCase(String name);

    List<SpellTag> findAllBySpellId(Long spellId);
    void deleteAllBySpellId(Long spellId);


}
