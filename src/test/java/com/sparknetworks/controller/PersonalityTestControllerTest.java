package com.sparknetworks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sparknetworks.html.HtmlInputTypes;
import com.sparknetworks.html.HtmlTagBuilderService;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PersonalityTestControllerTest {

	@Autowired
	private WebApplicationContext wac;
//	@MockBean
//	private QuestionService questionServiceMock;
	
	@MockBean
	private HtmlTagBuilderService htmlQuestionTypeAdapterMock;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void findAllQuestions_ShouldAddQuestionsToModelAndRenderQuestionsListView() throws Exception {
		Question question = null;//createQuestion();
//		QuestionView questionView = new QuestionView(question.getQuestion(), question.getCategory());
		
//		Map<QuestionView, String> map = new HashMap<>();
		StringBuilder builder = new StringBuilder();
		builder.append("<div>");
		builder.append("<input type="+HtmlInputTypes.single_choice.getHtmlInputType()+" id=male name=male value=male>");
		builder.append("<label for=male>male</label>");
		builder.append("</div>");
		
		
		builder.append("<div>");
		builder.append("<input type="+HtmlInputTypes.single_choice.getHtmlInputType()+" id=female name=female value=female>");
		builder.append("<label for=female>female</label>");
		builder.append("</div>");
		
		
		builder.append("<div>");
		builder.append("<input type="+HtmlInputTypes.single_choice.getHtmlInputType()+" id=other name=other value=other>");
		builder.append("<label for=other>other</label>");
		builder.append("</div>");
		
	//	map.put(questionView, builder.toString());
		//when(questionServiceMock.listQuestions()).thenReturn(Arrays.asList(question));
	//	when(htmlQuestionTypeAdapterMock.buildHtmlTag(Mockito.any())).thenReturn(map);
		

		mockMvc.perform(get("/questions")).
		andExpect(status().isOk()).
		//andExpect(view().name("questions")).
		andExpect(model().attribute("questionViewMap", hasSize(1)));
		//andExpect(model().attribute("questionViewMap",hasItem(allOf(hasProperty("question", equalTo("What is your gender?")), hasProperty("category", equalTo("hard_fact")), hasProperty("questionType", hasProperty("type",equalTo("single_choice")))))));

		//verify(questionServiceMock, times(1)).listQuestions();
		verify(htmlQuestionTypeAdapterMock, times(1)).buildHtmlTag(Mockito.any());
		//verifyNoMoreInteractions(questionServiceMock, htmlQuestionTypeAdapterMock);
		
	}

//	private Question createQuestion() {
//		Question question = new Question();
//		QuestionType questionType = new QuestionType();
//		question.setQuestion("What is your gender?");
//		question.setCategory("hard_fact");
//		questionType.setType("single_choice");
//		questionType.setOptions(Arrays.asList("male", "female", "other"));
//		question.setQuestionType(questionType);
//		return question;
//	}

}
