package com.sparknetworks.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.model.QuestionType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {

	@Autowired
	private QuestionService questionService;
	
	
	private List<Question> questions;

	@Before
	public void setUp() {
		questions = Arrays.asList(createQuestion());
	}

	@After
	@Transactional
	public void destroy() {
		for (Question q : questions) {
			questionService.delete(q);
		}
	}

	@Test
	@Transactional
	public void shouldCreateQuestionWithCategoryAndTypeWithOptions() {
		for (Question q : questions) {
			Question savedQuestion = questionService.persist(q);
			assertThat(savedQuestion.getId(), notNullValue());
			assertThat(savedQuestion.getText(), equalTo("How important is the age of your partner to you?"));
			assertThat(savedQuestion.getQuestionType().getType(), equalTo("single_choice_conditional"));

			assertThat(savedQuestion.getCondValue(), equalTo("very important"));
			assertThat(savedQuestion.getChildren().get(0).getText(),
					equalTo("What age should your potential partner be?"));
			assertThat(savedQuestion.getChildren().get(0).getQuestionType().getType(), equalTo("number_range"));

		}
	}

	private Question createQuestion() {

		Question parentQuestion = new Question.Builder().withText("How important is the age of your partner to you?")
				.withQuestionCategory(new QuestionCategory("hard_fact"))
				.withQuestionType(new QuestionType("single_choice_conditional",
						String.join(",", Arrays.asList("not important", "important", "very important")), "")).withEmptyChildren()
				.build();

		Question childQuestion = new Question.Builder().withQuestionCategory(new QuestionCategory("hard_fact"))
				.withQuestionType(new QuestionType("number_range", "", "18,44"))
				.withText("What age should your potential partner be?").withParent(parentQuestion)
				.withConditionExactValue("very important").build();
		parentQuestion.getChildren().add(childQuestion);
		return parentQuestion;

	}

}
