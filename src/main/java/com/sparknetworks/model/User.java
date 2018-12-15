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
	@CollectionTable(name = "question_answers")
	@MapKeyColumn(name = "question_id")
	private Map<Long, String> answers = new HashMap<>();

	private User() {
	}

	public User(final String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Long, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Long, String> answers) {
		this.answers = answers;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return id == user.getId() && Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}

}
