package com.crud.crudapp.CatFact;

import com.crud.crudapp.CatFactEntity.CatFactEntity;
import com.crud.crudapp.CatFactEntity.CatFactRepository;
import com.crud.crudapp.Exceptions.ExternalCatFactsDownException;
import com.crud.crudapp.Exceptions.SimpleResponse;
import com.crud.crudapp.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactQueryHandler implements Query<Void, CatFactDTO> {

	private final String CAT_FACT_URL = "https://catfact.ninja/fact";
	private final RestTemplate restTemplate;
	private final CatFactRepository catFactRepository;

	public CatFactQueryHandler(RestTemplate restTemplate, CatFactRepository catFactRepository) {
		this.restTemplate = restTemplate;
		this.catFactRepository = catFactRepository;
	}

	@Override
	public ResponseEntity<CatFactDTO> execute(Void input) {

		CatFact catFact = getCatFact();
		CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
		return ResponseEntity.ok(catFactDTO);
	}

	private CatFact getCatFact() {
		try {
			CatFact catFact = restTemplate.getForObject(CAT_FACT_URL, CatFact.class);
			catFactRepository.save(new CatFactEntity(catFact));
			return catFact;
		} catch (Exception exception){
			throw new ExternalCatFactsDownException(HttpStatus.SERVICE_UNAVAILABLE, new SimpleResponse("Service Unavailable. CatFacts API is DOWN"));
		}
	}
}