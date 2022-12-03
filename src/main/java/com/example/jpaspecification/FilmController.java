package com.example.jpaspecification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/film")
@RestController
public class FilmController {

	private FilmService filmService;
	
	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}

	@GetMapping("/list")
	public ResponseEntity<FilmResponse> getAllFilmDetails(){
		FilmResponse response = filmService.getAllFilmDetails();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/hello")
	public String hello() {
		
		return "Hello World";
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/movielist")
	public ResponseEntity<FilmInfoResponse> getFilmDetails(@RequestBody FilmRequest filmRequest){
		FilmInfoResponse response = filmService.getFilmDetails(filmRequest);
		return ResponseEntity.ok(response);
	}
}
