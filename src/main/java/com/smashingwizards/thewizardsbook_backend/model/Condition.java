package com.smashingwizards.thewizardsbook_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "conditions")
public class Condition {

    @Setter @Getter @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "condition_name")
    private String name;

    @Getter @Setter
    @Column(name = "condition_description", columnDefinition = "TEXT")
    private String description;

    // CONs
    public Condition() {}
    public Condition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // OVRs
    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
