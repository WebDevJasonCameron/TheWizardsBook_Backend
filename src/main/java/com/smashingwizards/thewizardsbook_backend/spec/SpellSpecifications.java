package com.smashingwizards.thewizardsbook_backend.spec;

import com.smashingwizards.thewizardsbook_backend.model.Spell;
import org.springframework.data.jpa.domain.Specification;

public final class SpellSpecifications {

    // CONs
    private SpellSpecifications() {}

    // METHs
    private static String esc(String string) {
        // escape %, _ and \ for LIKE
        return string.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_").toLowerCase();
    }

    public static Specification<Spell> nameContains(String term) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + esc(term) + "%", '\\');
    }

    public static Specification<Spell> levelEquals(String level) {
        return (root, cq, cb) -> cb.equal(root.get("level"), level);
    }

    public static Specification<Spell> belongingToTtrpg(Long ttrpgId) {
        return (root, cq, cb) -> cb.equal(root.get("ttrpg").get("id"), ttrpgId);
    }

    public static Specification<Spell> concentrationEquals(Boolean concentration) {
        return (root, cq, cb) -> cb.equal(root.get("concentration"), concentration);
    }

    public static Specification<Spell> ritualEquals(Boolean ritual) {
        return (root, cq, cb) -> cb.equal(root.get("ritual"), ritual);
    }

    public static Specification<Spell> schoolEquals(String school) {
        return (root, cq, cb) -> cb.equal(root.get("school"), school);
    }

    public static Specification<Spell> spellIdEquals(Long spellId) {
        return (root, cq, cb) -> cb.equal(root.get("id"), spellId);
    }

}
