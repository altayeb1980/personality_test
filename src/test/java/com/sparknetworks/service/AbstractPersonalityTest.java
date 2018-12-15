package com.sparknetworks.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.model.QuestionType;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractPersonalityTest {

	protected Map<String, QuestionCategory> categories = new HashMap<>();
	protected List<Question> questions = new ArrayList<>();

	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected QuestionService questionService;


	@Before
	public void init() throws IOException {
		categories = createCategories();
		questions = getQuestions(false);
	}

	@After
	public void destroy() {
		for (QuestionCategory category : categories.values()) {
			categoryService.delete(category);
		}

		for (Question q : questions) {
			questionService.delete(q);
		}
	}

	private Map<String, QuestionCategory> createCategories() {
		categories.put("hard_fact", new QuestionCategory("hard_fact"));
		categories.put("lifestyle", new QuestionCategory("lifestyle"));
		categories.put("introversion", new QuestionCategory("introversion"));
		categories.put("passion", new QuestionCategory("passion"));
		return categories;
	}

	private Question createQuestion(String textQuestion, QuestionCategory category, QuestionType questionType) {
		return new Question.Builder().withText(textQuestion).withQuestionCategory(category)
				.withQuestionType(questionType).withEmptyChildren().build();
	}

	private Question createChildQuestion(Question parentQuestion, QuestionCategory category, QuestionType questionType,
			String childTextQuestion) {
		return new Question.Builder().withQuestionCategory(category).withQuestionType(questionType)
				.withText(childTextQuestion).withParent(parentQuestion).withConditionExactValue("very important")
				.build();
	}

	
	protected List<Question> getQuestions(boolean withCondition) {
		List<Question> questions = new ArrayList<>();
		QuestionCategory category = categoryService.findByCategory("hard_fact");
		QuestionType questionType = new QuestionType(withCondition?"single_choice_conditional":"single_choice",
				String.join(",", Arrays.asList("not important", "important", "very important")), null);

		Question question = createQuestion("How important is the age of your partner to you?", category, questionType);
		question.setId(100L);

		if (withCondition) {
			Question childQuestion = createChildQuestion(question, category,
					new QuestionType("number_range", "", "18,44"), "What age should your potential partner be?");
			childQuestion.setId(101L);
			question.getChildren().add(childQuestion);
		}
		questions.add(question);

		return questions;
	}
	
	
	protected String getFirstOption(Question q) {
		String userAnswer = "";
		if(q.getQuestionType().getOptions() != null && q.getQuestionType().getOptions().split(",").length > 1) {
			userAnswer = q.getQuestionType().getOptions().split(",")[1];
		}
		return userAnswer;
	}

}
