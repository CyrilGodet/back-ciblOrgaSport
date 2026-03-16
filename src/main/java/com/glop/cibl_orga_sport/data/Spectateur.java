package com.glop.cibl_orga_sport.data;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SPECTATEUR")
public class Spectateur extends Utilisateur {

	@OneToMany(mappedBy = "spectateur", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
	private List<Billet> billets = new ArrayList<>();

	public Spectateur() {
		super();
	}

	public Spectateur(String nom, String prenom, String email, int age, Lieu lieu) {
		super(nom, prenom, email, age, lieu);
	}

	public Long getIdSpectateur() {
		return getIdUtilisateur();
	}

	public void setIdSpectateur(Long idSpectateur) {
		setIdUtilisateur(idSpectateur);
	}

	public List<Billet> getBillets() {
		return billets;
	}

	public void setBillets(List<Billet> billets) {
		this.billets = billets;
	}

}
