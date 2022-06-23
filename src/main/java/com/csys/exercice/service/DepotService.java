package com.csys.exercice.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csys.exercice.dao.DeoptRepository;
import com.csys.exercice.domain.Depot;
import com.csys.exercice.dto.DepotDto;
import com.csys.exercice.factory.DepotFactory;

@Service
public class DepotService {
	@Autowired
	DeoptRepository depotreprostory;

	@Transactional
	public DepotDto findOne(Long id) {
		Depot depot = depotreprostory.findByIdDepo(id);
		return DepotFactory.depotTODepotDTO(depot);
	}
	
	
	public Depot  findbyId(Long id) {
		return depotreprostory.findByIdDepo(id);
	}
	
	public Collection<Depot>findAllDepot(){
		return depotreprostory.findAll();
	}

	public Collection<DepotDto> findAll() {
		Collection<Depot> result = depotreprostory.findAll();
		return DepotFactory.depotToDepotDTOs(result);
	}

	public DepotDto add(DepotDto depotdto) {
		Depot depot = DepotFactory.depotDTOTODepot(depotdto);
		depot = depotreprostory.save(depot);
		return DepotFactory.depotTODepotDTO(depot);
	}

	public void deleteDepot(Long id) {
		depotreprostory.deleteById(id);

	}
	public List<Depot>listerAll(){
		return depotreprostory.findAll();
	}

}
