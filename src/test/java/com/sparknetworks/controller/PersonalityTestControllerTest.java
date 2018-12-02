package com.sparknetworks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;
import com.sparknetworks.service.QuestionService;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PersonalityTestControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@MockBean
	private QuestionService questionServiceMock;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void findAllQuestions_ShouldAddQuestionsToModelAndRenderQuestionsListView() throws Exception {
		Question question = createQuestion();
		when(questionServiceMock.listQuestions()).thenReturn(Arrays.asList(question));

		mockMvc.perform(get("/questions")).
		andExpect(status().isOk()).
		andExpect(view().name("questions")).
		andExpect(model().attribute("questions", hasSize(1))).
		andExpect(model().attribute("questions",hasItem(allOf(hasProperty("question", equalTo("What is your gender?")), hasProperty("category", equalTo("hard_fact")), hasProperty("questionType", hasProperty("type",equalTo("single_choice")))))));

		verify(questionServiceMock, times(1)).listQuestions();
		verifyNoMoreInteractions(questionServiceMock);
		
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
