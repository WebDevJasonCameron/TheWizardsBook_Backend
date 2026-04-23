package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbook_backend.model.RpgClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RpgClassRepository extends JpaRepository<RpgClass, Long> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

    /** ADDs */
    List<RpgClass> findAllByNameContainingIgnoreCase(String name);
}
