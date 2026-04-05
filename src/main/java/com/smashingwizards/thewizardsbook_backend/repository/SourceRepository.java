package com.smashingwizards.thewizardsbook_backend.repository;

import com.smashingwizards.thewizardsbag_backend.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long>, JpaSpecificationExecutor<Source> {
    // You can add custom queries here, like:
    // Optional<User> findByEmail(String email);

}
