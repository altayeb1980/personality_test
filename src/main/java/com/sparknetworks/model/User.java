package com.sparknetworks.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String email;	
	

	@ElementCollection
	@CollectionTable(name="question_answers")
	@MapKeyColumn(name="question_id")
	private Map<Long, String> choices = new HashMap<>();

	private User() {
	}

	public User(final String email,final Map<Long, String> choices) {
		this.email = email;
		this.choices = choices;
	}

	public Long getId() {
		return id;
	}

	public Map<Long, String> getChoices() {
		return choices;
	}

	public String getEmail() {
		return email;
	}



	
}