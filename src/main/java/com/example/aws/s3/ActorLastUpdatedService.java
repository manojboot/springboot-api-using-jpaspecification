package com.example.aws.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ActorLastUpdatedService {
	
	@Autowired
	protected ApplicationEventPublisher publisher; 
	
	public ActorRepository actorRepository;

	
	protected void publishActorUpdatedEvent(int actorId,AcotrChangeRequest acotrChangeRequest) {
		publisher.publishEvent(new ActorUpdatedEvent(actorId,acotrChangeRequest));
	}
	
	@EventListener
	public void saveActorLastUpdated(AcotrChangeRequest acotrChangeRequest) {
		
		Actor actor = new Actor();
		actor.setFirstName("Manoj");
		actor.setLastName("patelia");
		actorRepository.save(actor);
	}
}
