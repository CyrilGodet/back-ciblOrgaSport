package com.glop.cibl_orga_sport.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType;

public class CompetitionPhaseTypeDto {
    private String value;
    private String label;

    public CompetitionPhaseTypeDto() {
    }

    @JsonCreator
    public static CompetitionPhaseTypeDto fromString(String input) {
        for (CompetitionPhaseType type : CompetitionPhaseType.values()) {
            if (type.getLabel().equalsIgnoreCase(input) || type.name().equalsIgnoreCase(input)) {
                return new CompetitionPhaseTypeDto(type.name(), type.getLabel());
            }
        }
        return new CompetitionPhaseTypeDto(input, input);
    }

    public CompetitionPhaseTypeDto(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
