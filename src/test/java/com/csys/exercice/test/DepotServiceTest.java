package com.csys.exercice.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.csys.exercice.dao.DeoptRepository;
import com.csys.exercice.domain.Depot;
import com.csys.exercice.service.DepotService;

@ExtendWith(MockitoExtension.class)
class DepotServiceTest {
	@Mock
	private DeoptRepository depotRep;
	
	@InjectMocks
	private DepotService depotService;
	
	
	@Test
	void findAllDepot() {
		Depot cat = new Depot();
		cat.setIdDepo(1L);
		cat.setNomDepot("BLOC CENTRAL");
		Depot cat2=new Depot();
		cat2.setIdDepo(2L);
		cat2.setNomDepot("URGENCE");
		List<Depot>liste=Arrays.asList(cat,cat2);
		when(depotRep.findAll()).thenReturn(liste);
		List<Depot> resulat = (List<Depot>) depotService.findAllDepot();
		assertThat(resulat.size()).isGreaterThan(1);
		assertThat(resulat.get(0).getIdDepo()).isEqualTo(1);
	}
	
	
	/*@Test
	void addDepot() {
		DepotDto cat = new DepotDto();
		cat.setId(1L);
		cat.setNom("BLOC CENTRAL");
		when(depotRep.save(any())).then(returnsFirstArg();
		DepotDto dep = depotService.add(cat);
		assertThat(dep.getId()).isEqualTo(1);
		assertThat(dep.getNom()).isEqualTo("cat1");
		
	}*/


	
	/*@Test
	void findById() {
		Depot cat = new Depot();
		cat.setIdDepo(1L);
		cat.setNomDepot("BLOC CENTRAL");
		when(depotRep.findById(any())).thenReturn(Optional.of(cat));
		Depot depot = depotService.findbyId(1L);
		assertThat(depot.getIdDepo()).isEqualTo(cat.getIdDepo());
		assertThat(depot.getNomDepot()).isEqualTo("BLOC CENTRAL");
	}
*/


}
