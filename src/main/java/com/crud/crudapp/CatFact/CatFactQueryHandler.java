package com.crud.crudapp.CatFact;

import com.crud.crudapp.Exceptions.ExternalCatFactsDownException;
import com.crud.crudapp.Exceptions.SimpleResponse;
import com.crud.crudapp.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactQueryHandler implements Query<Void, CatFactDTO> {

	private final RestTemplate restTemplate;

	public CatFactQueryHandler(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public ResponseEntity<CatFactDTO> execute(Void input) {

		try{
			CatFact catFact = restTemplate.getForObject("https://catfact.ninja/fact", CatFact.class);
			assert catFact != null;
			CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
			return ResponseEntity.ok(catFactDTO);
		} catch (Exception exception){
			throw new ExternalCatFactsDownException(HttpStatus.SERVICE_UNAVAILABLE, new SimpleResponse("External API is down. NOT MY FAULT"));
		}
	}
}