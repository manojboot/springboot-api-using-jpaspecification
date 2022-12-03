package com.example.aws.s3;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor {
	
	@Id
	@Column(name = "actor_id")
	private int actorId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "last_update")
	private Timestamp lastUpdatedDate;
}
