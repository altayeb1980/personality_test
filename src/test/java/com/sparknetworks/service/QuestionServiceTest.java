package com.sparknetworks.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.sparknetworks.model.Question;

public class QuestionServiceTest extends AbstractPersonalityTest{

	@Test
	public void shouldCreateQuestions() {
		for (Question q : questions) {
			Question savedQuestion = questionService.persist(q);
			assertThat(savedQuestion.getId(), notNullValue());
			assertThat(savedQuestion.getText(), equalTo("How important is the age of your partner to you?"));
			assertThat(savedQuestion.getQuestionType().getType(), equalTo("single_choice_conditional"));

			assertThat(savedQuestion.getCondValue(), equalTo("very important"));
			
			for(Question childQuestion:savedQuestion.getChildren()) {
				assertThat(childQuestion.getText(),
						equalTo("What age should your potential partner be?"));
				assertThat(childQuestion.getQuestionType().getType(), equalTo("number_range"));
				
			}
		}
	}

}
