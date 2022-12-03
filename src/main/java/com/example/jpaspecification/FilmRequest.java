package com.example.jpaspecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmRequest extends FilmPageRequest{

	private String filmTitle;
	private String description;
}
