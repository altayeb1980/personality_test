package com.sparknetworks.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.sparknetworks.model.Question;

public class QuestionServiceTest extends AbstractPersonalityTest{

	@Test
	public void shouldCreateQuestionsWithoutConditionQuestions() {
		questions = getQuestions(false);
		for (Question q : questions) {
			Question savedQuestion = questionService.persist(q);
			assertThat(savedQuestion.getId(), notNullValue());
			assertThat(savedQuestion.getQuestionType().getType(), not(equalTo("single_choice_conditional")));
			assertThat(savedQuestion.getCondValue(), isEmptyOrNullString());
			assertThat(savedQuestion.getChildren().size(), equalTo(0));
		}
	}
	
		
	
	@Test
	public void shouldCreateQuestionsWithConditionQuestions() {
		questions = getQuestions(true);
		for (Question q : questions) {
			Question savedQuestion = questionService.persist(q);
			assertThat(savedQuestion.getId(), notNullValue());
			assertThat(savedQuestion.getQuestionType().getType(), equalTo("single_choice_conditional"));
			assertThat(savedQuestion.getCondValue(), not(isEmptyOrNullString()));
			assertThat(savedQuestion.getChildren().size(), greaterThan(0));
		}
	}
	

}
