package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.SpellTtrpg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellTtrpgRepository extends JpaRepository<SpellTtrpg, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);
}
