package com.example.aws.s3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorUpdatedEvent {

	private int actorId;
	private AcotrChangeRequest acotrChangeRequest;
	
	public ActorUpdatedEvent(int actorId, AcotrChangeRequest acotrChangeRequest) {
		super();
		this.actorId = actorId;
		this.acotrChangeRequest = acotrChangeRequest;
	}
	
}
