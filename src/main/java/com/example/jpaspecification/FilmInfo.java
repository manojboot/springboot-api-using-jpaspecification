package com.example.jpaspecification;

import java.sql.Date;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmInfo {

	private int filmId;
	private String title;
	private String description;
	private int releaseYear;
	private int length;
	private double rentalRate;
	private Date lastUpdateDate;
}
