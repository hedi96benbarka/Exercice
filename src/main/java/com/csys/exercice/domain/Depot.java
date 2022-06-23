package com.csys.exercice.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Depot  {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_depo")
	private Long idDepo;
	
	private String nomDepot;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "depot",fetch = FetchType.EAGER)
	private Collection<Stock> stocks;

	public Depot() {
	}

	public Collection<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Collection<Stock> stocks) {
		this.stocks = stocks;
	}

	public Long getIdDepo() {
		return idDepo;
	}

	public void setIdDepo(Long idDepo) {
		this.idDepo = idDepo;
	}

	public String getNomDepot() {
		return nomDepot;
	}

	public void setNomDepot(String nomDepot) {
		this.nomDepot = nomDepot;
	}

	@Override
	public String toString() {
		return "Depot [idDepo=" + idDepo + ", nomDepot=" + nomDepot + "]";
	}

}
