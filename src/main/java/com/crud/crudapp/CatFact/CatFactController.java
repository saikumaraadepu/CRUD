package com.crud.crudapp.CatFact;

import com.crud.crudapp.CatFactEntity.CatFactEntity;
import com.crud.crudapp.CatFactEntity.CatFactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/catfact")
public class CatFactController {

	private final CatFactQueryHandler catFactQueryHandler;
	private final CatFactRepository catFactRepository;
	public CatFactController(CatFactQueryHandler catFactQueryHandler, CatFactRepository catFactRepository) {
		this.catFactQueryHandler = catFactQueryHandler;
		this.catFactRepository = catFactRepository;
	}

	@GetMapping
	public ResponseEntity<CatFactDTO> getCatFact() {
		return catFactQueryHandler.execute(null);
	}

	@GetMapping(path = "/local")
	public ResponseEntity<List<CatFact>> getSavedCatFacts() {
		return ResponseEntity.ok(catFactRepository
				.findAll()
				.stream()
				.map(CatFactEntity::convertToCatFact)
				.collect(Collectors.toList()));
	}
}
