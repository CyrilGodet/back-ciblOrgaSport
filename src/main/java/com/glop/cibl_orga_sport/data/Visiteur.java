package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.DiscriminatorValue;

import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("VISITEUR")
public class Visiteur extends Utilisateur {

	@OneToMany(mappedBy = "visiteur", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
	private List<Billet> billets = new ArrayList<>();


    public Visiteur() {
        super();
    }

    public Visiteur(String nom, String prenom, String email, int age, Lieu lieu) {
        super(nom, prenom, email, age, lieu);
    }

    public List<Billet> getBillets() {
		return billets;
	}

	public void setBillets(List<Billet> billets) {
		this.billets = billets;
	}

    public Long getIdVisiteur() {
		return getIdUtilisateur();
	}

	public void setIdVisiteur(Long idVisiteur) {
		setIdUtilisateur(idVisiteur);
	}


}
