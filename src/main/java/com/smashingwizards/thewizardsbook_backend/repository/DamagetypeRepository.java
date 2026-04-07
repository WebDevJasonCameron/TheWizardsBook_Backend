package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbag_backend.model.Damagetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagetypeRepository extends JpaRepository<Damagetype, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

}
