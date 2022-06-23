package com.csys.exercice.factory;

import java.util.ArrayList;
import java.util.Collection;

import com.csys.exercice.domain.Depot;
import com.csys.exercice.dto.DepotDto;

public class DepotFactory {

	public static Depot depotDTOTODepot(DepotDto depot) {
		Depot dep = new Depot();
		dep.setNomDepot(depot.getNom());
		dep.setIdDepo(depot.getId());
		return dep;
	}

	public static DepotDto depotTODepotDTO(Depot depot) {
		if (depot != null) {
			DepotDto depdto = new DepotDto();
			depdto.setId(depot.getIdDepo());
			depdto.setNom(depot.getNomDepot());
			;
			return depdto;
		} else {
			return null;
		}
	}

	public static Collection<DepotDto> depotToDepotDTOs(Collection<Depot> depots) {
		Collection<DepotDto> depdtos = new ArrayList<>();
		for (Depot dep : depots) {
			DepotDto deptdto = depotTODepotDTO(dep);
			depdtos.add(deptdto);
		}
		return depdtos;
	}

}
