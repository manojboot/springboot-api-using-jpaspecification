package com.example.jpaspecification;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmInfoResponse<T> extends CommomResponse {

	private PageImpl<T> films;
}
