package com.sparknetworks.model.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String question;
	@Enumerated(EnumType.STRING)
	private QuestionCategories questionCategories;
	@OneToOne(cascade=CascadeType.ALL)
	private QuestionType questionType;

	// Makes Hibernate happy
	private Question() {
	}

	public Question(String question, QuestionCategories questionCategories, QuestionType questionType) {
		this.question = question;
		this.questionCategories = questionCategories;
		this.questionType = questionType;
	}

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public QuestionCategories getQuestionCategories() {
		return questionCategories;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", questionCategories=" + questionCategories
				+ ", questionType=" + questionType + "]";
	}

}
