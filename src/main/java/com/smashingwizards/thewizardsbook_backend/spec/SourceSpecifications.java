package com.smashingwizards.thewizardsbook_backend.spec;

import com.smashingwizards.thewizardsbook_backend.model.Source;
import org.springframework.data.jpa.domain.Specification;

public class SourceSpecifications {

    // CONs
    private SourceSpecifications() {}

    // METHs
    private static String esc(String string) {
        // escape %, _ and \ for LIKE
        return string.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_").toLowerCase();
    }

    public static Specification<Source> nameContains(String term) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + esc(term) + "%", '\\');
    }
}
