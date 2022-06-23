package com.csys.exercice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csys.exercice.domain.Depot;

public interface DeoptRepository extends JpaRepository<Depot, Long> {
	
	Depot findByNomDepot(String nomDepot);
	Depot findByIdDepo (Long id);

}
