package com.sparknetworks.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class QuestionCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String category;

	private QuestionCategory() {
	}

	public QuestionCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "QuestionCategory [id=" + id + ", category=" + category + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof QuestionCategory)) {
			return false;
		}
		QuestionCategory cat = (QuestionCategory) o;
		return id == cat.id && Objects.equals(category, cat.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,category);
	}
}
