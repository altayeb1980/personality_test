package com.sparknetworks.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class QuestionType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String options;
	private String type;
	@OneToOne(cascade = CascadeType.ALL)
	private QuestionCondition questionCondition;
	private String ranges;

	private QuestionType() {
	}

	public QuestionType(String type, String options, QuestionCondition questionCondition, String ranges) {
		this.type = type;
		this.options = options;
		this.questionCondition = questionCondition;
		this.ranges =ranges;
	}

	public String getOptions() {
		return options;
	}

	public String getType() {
		return type;
	}

	public QuestionCondition getQuestionCondition() {
		return questionCondition;
	}
	

	public String getRanges() {
		return ranges;
	}

	@Override
	public String toString() {
		return "QuestionType [id=" + id + ", options=" + options + ", type=" + type + ", questionCondition="
				+ questionCondition + "]";
	}

}
