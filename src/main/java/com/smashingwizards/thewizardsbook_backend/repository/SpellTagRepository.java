package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.SpellTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellTagRepository extends JpaRepository<SpellTag, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);
}
