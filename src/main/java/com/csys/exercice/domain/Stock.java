package com.csys.exercice.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@NamedEntityGraph(
		name = "article-depot-graph",
		attributeNodes = {
				@NamedAttributeNode("depot")
		}

)

public class Stock {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long idStock;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_article",referencedColumnName = "id_article")
	private Article article;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_deopt",referencedColumnName = "id_depo")
	private Depot depot;

	private int qte;
 
	

	private Date datePeremption;

	public Long getIdStock() {
		return idStock;
	}

	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Date getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}

	@Override
	public String toString() {
		return "Stock [idStock=" + idStock + ", article=" + article + ", depot=" + depot + ", qte=" + qte
				+ ", datePeremption=" + datePeremption + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stock stock = (Stock) o;
		return idStock.equals(stock.idStock);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idStock);
	}
}
