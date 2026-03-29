package com.glop.cibl_orga_sport.data.enumType;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompetitionPhaseType {
    PRE_SELECTION("Pré-sélection"),
    SELECTION("Sélection"),
    HUITIEME("Huitièmes de finale"),
    QUART_DE_FINALE("Quarts de finale"),
    DEMI_FINALE("Demi-finales"),
    FINALE("Finale");

    private final String label;

    CompetitionPhaseType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
