package com.example.aws.s3;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/aws")
@RestController
public class ActorController {
	
	private ActorService actorService;
	private CreatePdfService createPdfService;

	public ActorController(ActorService actorService,CreatePdfService createPdfService) {
		super();
		this.actorService = actorService;
		this.createPdfService = createPdfService;
	}


	@GetMapping("/hello")
	public String hello() {
		
		return "Hello World";
	}
	
	@GetMapping("/actor/pdf/list")
	public ResponseEntity<CommonResponse>  createPdf() throws IOException {
		CommonResponse response = createPdfService.createPdf();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/allactors")
	public ResponseEntity<ActorsResponse> getAllActors(){
		ActorsResponse response = actorService.getAllActors();
		return ResponseEntity.ok(response);
	}
}
