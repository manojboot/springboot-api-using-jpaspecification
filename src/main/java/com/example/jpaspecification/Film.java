package com.example.jpaspecification;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "film")
public class Film {

	@Id
	@Column(name = "film_id")
	private int filmId;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "release_year")
	private int releaseYear;
	@Column(name = "length")
	private int length;
	@Column(name = "rental_rate")
	private double rentalRate;
	@Column(name = "last_update")
	private Date lastUpdateDate;
}
