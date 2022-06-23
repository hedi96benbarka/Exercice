package com.csys.exercice.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

@Entity
public class Article  {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_article")
	private Long idArticle;
	@NotNull
	private String designation;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article",fetch = FetchType.EAGER)
	private Collection<Stock> stocks;

	public Article() {
	}

	public Collection<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Collection<Stock> stocks) {
		this.stocks = stocks;
	}

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getDesignation_article() {
		return designation;
	}

	public void setDesignation_article(String designation_article) {
		this.designation = designation_article;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", designation_article=" + designation + "]";
	}

}
