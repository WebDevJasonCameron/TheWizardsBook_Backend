package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.SpellClass;
import com.smashingwizards.thewizardsbook_backend.model.SpellTag;
import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpellTtrpgRepository extends JpaRepository<SpellTtrpg, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

    /** ADDs */
    List<SpellTtrpg> findAllByTtrpg_NameContainingIgnoreCase(String name);

    List<SpellTtrpg> findAllBySpellId(Long spellId);

}
