package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class ConditionAge {

    @Column(nullable = false)
    private int ageMin;

    @Column(nullable = false)
    private int ageMax;

    public ConditionAge() {
    }

    public ConditionAge(int ageMin, int ageMax) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    
}
