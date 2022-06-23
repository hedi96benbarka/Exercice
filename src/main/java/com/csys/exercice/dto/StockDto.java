package com.csys.exercice.dto;

import java.io.Serializable;
import java.util.Date;
//LocalDate
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class StockDto  {
	private Long idStock;
	private Long idArticle;
	private String designation;
	private Long idDepot;
	private String depot;
	private int qte;
	@JsonSerialize(as =Date.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date datePeremption;

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}


	public Long getIdArticle() {
		return idArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public String getDepot() {
		return depot;
	}






	public Date getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Long getIdStock() {
		return idStock;
	}

	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}


	@Override
	public String toString() {
		return "StockDto{" +
				"idStock=" + idStock +
				", idArticle=" + idArticle +
				", designation='" + designation + '\'' +
				", idDepot=" + idDepot +
				", depot='" + depot + '\'' +
				", qte=" + qte +
				", datePeremption=" + datePeremption +
				'}';
	}
}
