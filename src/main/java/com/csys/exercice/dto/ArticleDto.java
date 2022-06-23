package com.csys.exercice.dto;

import java.io.Serializable;

public class ArticleDto  {
	private Long idArticle;
	private String designation;

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
