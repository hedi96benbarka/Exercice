package com.csys.exercice.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.csys.exercice.domain.Depot;
import com.csys.exercice.dto.DepotDto;
import com.csys.exercice.service.DepotService;
import com.csys.exercice.util.RestPreconditions;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/api/depots")
public class DepotResource {
	@Autowired
	DepotService depotservice;
	private static final String ENTITY_NAME = "Depot";

	@GetMapping("/{id}")
	public DepotDto findOne(@PathVariable Long id) {
		DepotDto depot = depotservice.findOne(id);
		RestPreconditions.checkFound(depot, ENTITY_NAME + "depot not found");
		return depot;
	}

	@GetMapping
	public Collection<DepotDto> findAll() {
		return depotservice.findAll();
	}

	@PostMapping
	public ResponseEntity<DepotDto> addDepot(@Valid @RequestBody DepotDto depot) throws URISyntaxException {
		DepotDto result = depotservice.add(depot);
		return ResponseEntity.created(new URI("/api/depots/add" + result.getNom())).body(result);
	}

	@PutMapping
	public ResponseEntity<DepotDto> updateDepot(@Valid @RequestBody DepotDto depot) throws MethodArgumentNotValidException {

		DepotDto result = depotservice.add(depot);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepot(@PathVariable Long id) {
		depotservice.deleteDepot(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getAllDepot")
	public List<Depot>getdepot(){
		return depotservice.listerAll(); 
	}
	
	@PostMapping("/addDepot")
	public DepotDto addDepotDto(@Valid @RequestBody DepotDto depot) throws URISyntaxException {
		DepotDto result = depotservice.add(depot);
		return result;
	}


}
