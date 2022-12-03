package com.example.jpaspecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmPageRequest {

	private int pageNumber;
	private int size;
	private boolean list;
}
