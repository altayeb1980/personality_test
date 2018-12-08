package com.sparknetworks.service;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sparknetworks.AbstractApplicationTest;
import com.sparknetworks.model.db.Question;
import com.sparknetworks.model.db.QuestionCategories;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class QuestionPersistenceServiceTest extends AbstractApplicationTest {

	@Autowired
	private QuestionPersistenceService jsonPersistenceService;
	private List<Question> questions;

	@Before
	public void setUp() {
		questions = Arrays.asList(createQuestion());
	}

	@After
	public void destroy() {
		for (Question q : questions) {
			jsonPersistenceService.delete(q);
		}
	}

	@Test
	public void shouldCreateQuestionWithCategoryAndTypeWithOptions() {
		for (Question q : questions) {
			Question savedQuestion = jsonPersistenceService.persist(q);
			assertThat(savedQuestion.getId(), notNullValue());
			assertThat(savedQuestion.getQuestion(), equalTo("What is your gender?"));
			assertThat(savedQuestion.getQuestionCategories(), equalTo(QuestionCategories.hard_fact));	
			assertThat(savedQuestion.getQuestionType().getType(), equalTo("single_choice"));
			assertThat(savedQuestion.getQuestionType().getOptions(), equalTo("male,female,other"));
		}
	}
}
