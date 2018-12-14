package com.sparknetworks.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

	public User(Long id,final String email,final Map<Long, String> choices) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return id == user.id && Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}


	
}
