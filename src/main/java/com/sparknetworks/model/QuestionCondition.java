package com.sparknetworks.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class QuestionCondition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String exactEquals;

	@OneToOne(cascade = CascadeType.ALL)
	private Question question;
	

	private QuestionCondition() {
	}

	
	public QuestionCondition(String exactEquals, Question question){
		this.exactEquals = exactEquals;
		this.question = question;
	}
	public Long getId() {
		return id;
	}

	public String getExactEquals() {
		return exactEquals;
	}

	public Question getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "QuestionCondition [id=" + id + ", exactEquals=" + exactEquals + ", question=" + question + "]";
	}

}
