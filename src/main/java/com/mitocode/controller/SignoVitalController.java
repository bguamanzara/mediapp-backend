package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.SignoVital;
import com.mitocode.service.ISignoVitalService;

@RestController
@RequestMapping("/signosvitales")
public class SignoVitalController {

	@Autowired
	private ISignoVitalService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SignoVital>> listar() {
		List<SignoVital> signoVitals = new ArrayList<>();

		signoVitals = service.listar();

		return new ResponseEntity<List<SignoVital>>(signoVitals, HttpStatus.OK);
	}
	
	@GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<SignoVital>> listarPageable(Pageable pageable) {
		Page<SignoVital> signoVitals = null;

		signoVitals = service.listarPageable(pageable);

		return new ResponseEntity<Page<SignoVital>>(signoVitals, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<SignoVital> listarId(@PathVariable("id") Integer id) {
		SignoVital signoVital = new SignoVital();
		signoVital = service.listarId(id);
		if (signoVital == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}

		Resource<SignoVital> resource = new Resource<SignoVital>(signoVital);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listar());
		resource.add(linkTo.withRel("all-signoVitals"));
		return resource;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody SignoVital signoVital) {
		SignoVital pac = new SignoVital();
		pac = service.registrar(signoVital);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pac.getIdSignoVital()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody SignoVital signoVital) {		
		service.modificar(signoVital);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		SignoVital pac = service.listarId(id);
		if (pac == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			service.eliminar(id);
		}
	}
}
