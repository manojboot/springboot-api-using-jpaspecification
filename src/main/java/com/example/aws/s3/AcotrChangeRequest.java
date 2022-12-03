package com.example.aws.s3;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcotrChangeRequest {

	private int actorId;
	private boolean firstNameChnaged;
	private boolean lastNameChanged;
	private boolean lastUpdatedDateChanged;
}
