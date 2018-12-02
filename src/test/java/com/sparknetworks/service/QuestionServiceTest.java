package com.sparknetworks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sparknetworks.AbstractApplicationTest;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;

public class QuestionServiceTest extends AbstractApplicationTest {

	@Autowired
	private QuestionService questionService;

	@Test
	public void testListQuestionsHasItems() throws IOException {
		List<Question> actualQuestions = questionService.listQuestions();
		Question question = createQuestion();
		assertNotNull(actualQuestions);
		assertThat(actualQuestions, hasItems(question));
	}

	
	@Test
	public void testListQuestionsContainsCorrectQuestion() throws IOException {
		List<Question> actualQuestions = questionService.listQuestions();
		Question question = createQuestion();
		assertEquals(actualQuestions.get(0).getQuestion(), question.getQuestion());
	}

	
	private Question createQuestion() {
		Question question = new Question();
		QuestionType questionType = new QuestionType();
		question.setQuestion("What is your gender?");
		question.setCategory("hard_fact");
		questionType.setType("single_choice");
		questionType.setOptions(Arrays.asList("male", "female", "other"));
		question.setQuestionType(questionType);
		return question;
	}

}
