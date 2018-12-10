package com.sparknetworks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String options;
	private String type;
	private String ranges;

	private QuestionType() {
	}

	public QuestionType(String type, String options, String ranges) {
		this.type = type;
		this.options = options;

		this.ranges = ranges;
	}

	public String getOptions() {
		return options;
	}

	public String getType() {
		return type;
	}

	public String getRanges() {
		return ranges;
	}

}
