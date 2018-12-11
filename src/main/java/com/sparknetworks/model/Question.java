package com.sparknetworks.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String text;
	@Enumerated(EnumType.STRING)
	private QuestionCategories questionCategories;
	@OneToOne(cascade = CascadeType.ALL)
	private QuestionType questionType;
	private String condValue;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "parent_id")
	private Question parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> children;

	@Transient
	private Long parent_id;

	// Makes Hibernate happy
	private Question() {
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public QuestionCategories getQuestionCategories() {
		return questionCategories;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setQuestionCategories(QuestionCategories questionCategories) {
		this.questionCategories = questionCategories;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public void setCondValue(String condValue) {
		this.condValue = condValue;
	}

	public void setParent(Question parent) {
		this.parent = parent;
	}

	public void setChildren(List<Question> children) {
		this.children = children;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public static class Builder {
		private String text;
		private QuestionCategories questionCategories;
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

		public Builder withQuestionCategories(QuestionCategories questionCategories) {
			this.questionCategories = questionCategories;
			return this;
		}

		public Builder withQuestionType(QuestionType questionType) {
			this.questionType = questionType;
			return this;
		}

		public Builder withcondValue(String condValue) {
			this.condValue = condValue;
			return this;
		}

		public Question build() {
			Question question = new Question();
			question.parent = this.parent;
			question.children = this.children;
			question.text = this.text;
			question.questionCategories = this.questionCategories;
			question.questionType = this.questionType;
			question.condValue = this.condValue;
			return question;
		}
	}
}
