package com.crud.crudapp.CatFact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/catfact")
public class CatFactController {

	private final CatFactQueryHandler catFactQueryHandler;
	public CatFactController(CatFactQueryHandler catFactQueryHandler) {
		this.catFactQueryHandler = catFactQueryHandler;
	}

	@GetMapping
	public ResponseEntity<CatFactDTO> getCatFact() {
		return catFactQueryHandler.execute(null);
	}
}
