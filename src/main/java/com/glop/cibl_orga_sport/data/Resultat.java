package com.glop.cibl_orga_sport.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glop.cibl_orga_sport.data.enumType.ResultatStatusEnum;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultatStatusEnum status = ResultatStatusEnum.DRAFT;

    @OneToMany(mappedBy = "resultat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("resultat-details")
    private List<ResultatDetails> details = new ArrayList<>();

    public Resultat() {
    }

    public Resultat(ResultatStatusEnum status) {
        this.status = status;
        this.details = new ArrayList<>();
    }

    public Long getIdResultat() {
        return idResultat;
    }

    public void setIdResultat(Long idResultat) {
        this.idResultat = idResultat;
    }

    public ResultatStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResultatStatusEnum status) {
        this.status = status;
    }

    public List<ResultatDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ResultatDetails> details) {
        this.details = details;
    }

    public void addDetail(ResultatDetails detail) {
        details.add(detail);
        detail.setResultat(this);
    }

    public void removeDetail(ResultatDetails detail) {
        details.remove(detail);
        detail.setResultat(null);
    }
}
