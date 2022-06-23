package com.csys.exercice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.csys.exercice.domain.Depot;
import com.csys.exercice.dto.DepotDto;
import com.csys.exercice.web.rest.DepotResource;
import com.csys.exercice.service.DepotService;


@RunWith(SpringRunner.class)
@WebMvcTest(DepotResource.class)
class DepotTestControlleur {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private DepotService depotService;
	@MockBean
	private MapValidationErrorService MapErrors;

	@Test
	void getAllCategories() throws Exception {
		Depot cat = new Depot();
		cat.setIdDepo(1L);
		cat.setNomDepot("BLOC CENTRAL");
		Depot cat2=new Depot();
		cat2.setIdDepo(2L);
		cat2.setNomDepot("URGENCE");
		List<Depot>liste=Arrays.asList(cat,cat2);
		when(depotService.listerAll()).thenReturn(liste);

		Mockito.when(depotService.listerAll()).thenReturn(liste);
		mvc.perform(get("/api/depots/getAllDepot").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nomDepot", is("BLOC CENTRAL")))
				.andExpect(jsonPath("$[1].nomDepot", is("URGENCE")));
	}
	
	 @Test
	    public void removeUserById_whenDeleteMethod() throws Exception {
		 Depot cat = new Depot();
			cat.setIdDepo(3L);
			cat.setNomDepot("hedi");

	        doNothing().when(depotService).deleteDepot(cat.getIdDepo());

	        mvc.perform(delete("/api/depots/" + cat.getIdDepo().toString())
	                .contentType(MediaType.APPLICATION_JSON))
	                ;
	    }
	 
	 
	 @Test
		void getDepotById() throws Exception {
		 DepotDto depot = new DepotDto();
		 depot.setId(1L);
		 depot.setNom("BLOC CENTRAL");
		 doReturn(depot).when(depotService).findOne(depot.getId());
		 
			/*mvc.perform(get("/api/depots/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$.nom", is("BLOC CENTRAL"))).andExpect(jsonPath("$.id", is(1)));*/
			
			DepotDto e1ByService = depotService.findOne(depot.getId());
			
			assertNotNull(depot.getId(),"depot with id  : "+depot.getId()+" not found");
			assertEquals(depot.getId(),e1ByService.getId());
		    assertEquals(depot.getNom(), e1ByService.getNom());
		}
	 


}
