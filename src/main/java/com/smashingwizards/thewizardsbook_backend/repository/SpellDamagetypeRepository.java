package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.SpellDamagetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellDamagetypeRepository  extends JpaRepository<SpellDamagetype, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

}
