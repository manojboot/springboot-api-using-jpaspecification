package com.example.jpaspecification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommomResponse {

	private String statusCode;
	private String status;
	private String statusDescription;
	public CommomResponse(String statusCode, String status, String statusDescription) {
		super();
		this.statusCode = "Success";
		this.status = "200";
		this.statusDescription = "Data Retrieved Successfully";
	}
	
	
	
}
