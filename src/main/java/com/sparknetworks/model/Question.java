package com.sparknetworks.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String text;
	@ManyToOne(cascade = CascadeType.MERGE,optional = true)
	@JoinColumn(name = "category_id")
	private QuestionCategory category;
	@OneToOne(cascade = CascadeType.ALL)
	private QuestionType questionType;
	private String condValue;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "parent_id")
	private Question parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> children;

	// Makes Hibernate happy
	private Question() {
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public QuestionCategory getCategory() {
		return category;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public String getCondValue() {
		return condValue;
	}

	public Question getParent() {
		return parent;
	}

	public List<Question> getChildren() {
		return children;
	}

	public static class Builder {
		private String text;
		private QuestionCategory category;
		private QuestionType questionType;
		private String condValue;
		private Question parent;
		private List<Question> children;

		public Builder withParent(Question parent) {
			this.parent = parent;
			return this;
		}

		public Builder withEmptyChildren() {
			this.children = new ArrayList<>();
			return this;
		}

		public Builder withChildren(List<Question> children) {
			this.children = children;
			return this;
		}

		public Builder withText(String text) {
			this.text = text;
			return this;
		}

		public Builder withQuestionCategory(QuestionCategory category) {
			this.category = category;
			return this;
		}

		public Builder withQuestionType(QuestionType questionType) {
			this.questionType = questionType;
			return this;
		}

		public Builder withConditionExactValue(String condValue) {
			this.parent.condValue = condValue;
			return this;
		}

		public Question build() {
			Question question = new Question();
			question.parent = this.parent;
			question.children = this.children;
			question.text = this.text;
			question.category = this.category;
			question.questionType = this.questionType;
			question.condValue = this.condValue;
			return question;
		}
	}
}
