package com.crud.crudapp.CatFactEntity;

import com.crud.crudapp.CatFact.CatFact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_facts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatFactEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "catfactJSON")
	private String catfactJSON;

	public CatFactEntity(CatFact catfact) {
		this.catfactJSON = convertToJSON(catfact);
	}

	//Serialization
	private String convertToJSON(CatFact catFact) {
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(catFact);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	//Deserialization
	private CatFact convertToCatFact(CatFact catFact) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(catfactJSON, CatFact.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
