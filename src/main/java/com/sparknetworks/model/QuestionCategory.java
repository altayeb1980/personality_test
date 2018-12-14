package com.sparknetworks.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class QuestionCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String category;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> questions;

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

	public List<Question> getQuestions() {
		return questions;
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
		return Objects.hash(id, category);
	}
}
