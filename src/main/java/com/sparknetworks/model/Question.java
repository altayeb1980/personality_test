package com.sparknetworks.model;

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

	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "parent_id")
	private Question parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Question> children;
	
	@Transient
	private Long parent_id;

	// Makes Hibernate happy
	private Question() {
	}

	public Question(List<Question> children) {
		this.children = children;
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

	
}
