package com.example.jpaspecification;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageImpl<T> {

	private List<T> content;
	private int numberOfElements;
	private int totalElements;
	private int totalPages;
	private int size;
}
