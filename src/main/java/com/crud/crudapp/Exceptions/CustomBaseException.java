package com.crud.crudapp.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CustomBaseException extends RuntimeException{

	private HttpStatus status;
	private SimpleResponse simpleResponse;
}
