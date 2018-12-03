package com.sparknetworks.model;

public class QuestionView {

	private final String question;
	private final String category;

	public QuestionView(final String question, final String category) {
		this.question = question;
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public String getCategory() {
		return category;
	}

}
