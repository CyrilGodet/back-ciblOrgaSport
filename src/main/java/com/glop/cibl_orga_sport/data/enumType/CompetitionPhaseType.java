package com.glop.cibl_orga_sport.data.enumType;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompetitionPhaseType {
    PRE_SELECTION("Pré-sélection"),
    SELECTION("Sélection"),
    OCTO_FINAL("Huitièmes de finale"),
    QUARTER_FINAL("Quarts de finale"),
    SEMI_FINAL("Demi-finales"),
    FINAL("Finale");

    private final String label;

    CompetitionPhaseType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
