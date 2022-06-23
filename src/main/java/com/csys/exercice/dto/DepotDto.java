package com.csys.exercice.dto;

import java.io.Serializable;

public class DepotDto   {
	private Long id;
	private String nom_depot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom_depot;
	}

	public void setNom(String nom) {
		this.nom_depot = nom;
	}

	@Override
	public String toString() {
		return "DepotDto [id=" + id + ", nom_depot=" + nom_depot + "]";
	}

}
