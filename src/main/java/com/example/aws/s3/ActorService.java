package com.example.aws.s3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ActorService {

	private ActorRepository actorRepository;
	public ActorService(ActorRepository actorRepository) {
		super();
		this.actorRepository = actorRepository;
	}

	public ActorsResponse getAllActors(){
		ActorsResponse response = new ActorsResponse();
		List<Actor> actors = actorRepository.findAll();
		response.setActors(actors);
		return response;
	}
}
