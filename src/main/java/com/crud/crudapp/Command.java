package com.crud.crudapp;

import org.springframework.http.ResponseEntity;

public interface Command<E, T> {

	ResponseEntity<T> execute(E entity);
}
