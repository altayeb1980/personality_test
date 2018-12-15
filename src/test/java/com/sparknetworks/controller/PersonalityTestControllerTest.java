package com.sparknetworks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.html.HtmlTagBuilderService;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.model.User;
import com.sparknetworks.service.AbstractPersonalityTest;
import com.sparknetworks.service.CategoryService;
import com.sparknetworks.service.QuestionService;
import com.sparknetworks.service.UserService;

@WebAppConfiguration
public class PersonalityTestControllerTest extends AbstractPersonalityTest {

	@Autowired
	private WebApplicationContext wac;

	@MockBean
	private HtmlTagBuilderService htmlTagBuilderServiceMock;
	@MockBean
	private QuestionService questionServiceMock;
	@MockBean 
	private CategoryService categoryServiceMock;
//	@MockBean
//	private UserService userServiceMock;
//	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		Mockito.reset(categoryServiceMock, questionServiceMock, htmlTagBuilderServiceMock);
	}

	@Test
	public void renderIndexViewWithCategories() throws Exception {
		when(categoryServiceMock.findAll()).thenReturn(new ArrayList<>(categories.values()));
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attributeExists("categories"))
				.andExpect(model().attribute("categories", hasSize(4)));
		verify(categoryServiceMock, times(1)).findAll();
	}

	@Test
	public void findAllQuestionsAndRenderQuestionsToViewAsHtmlTags() throws Exception {
		when(categoryServiceMock.findByCategory(Mockito.anyString())).thenReturn(new QuestionCategory("hard_fact"));
		List<Question> questions = getQuestions(false);
		String htmlTag = getHtmlTag(questions);
		
		when(questionServiceMock.findAllWithParentIdNull(Mockito.any())).thenReturn(questions);
		when(htmlTagBuilderServiceMock.buildHtmlTag(Mockito.any())).thenReturn(htmlTag.toString());

		mockMvc.perform(get("/questions")).andExpect(status().isOk()).andExpect(view().name("questions"))
				.andExpect(model().attributeExists("questionsMap"))
				.andExpect(model().attribute("questionsMap", IsMapContaining.hasValue(htmlTag)));

		verify(questionServiceMock, times(1)).findAllWithParentIdNull(Mockito.any());
		verify(htmlTagBuilderServiceMock, times(1)).buildHtmlTag(Mockito.any());
		verifyNoMoreInteractions(htmlTagBuilderServiceMock);
	}
	
	
	@Test
	public void shouldCreateUserAnswersAndReturnHttp201Created() throws Exception {
		List<Question> questions = getQuestions(false);
		Question firstQuestion = questions.get(0);
		Map<Long, String> userAnswers = new HashMap<>();
		String firstOption = getFirstOption(firstQuestion);
		userAnswers.put(firstQuestion.getId(), firstOption);
		User user = new User("spark_user@gmail.com");
		user.setAnswers(userAnswers);
		//when the user email answer first time.
		//when(userServiceMock.findByEmail("spark_user@gmail.com")).thenReturn(null);
		mockMvc.perform(post("/answers").
				contentType(MediaType.APPLICATION_JSON).
				content(new ObjectMapper().writeValueAsString(user))).
				andExpect(status().isCreated()).
				andExpect(jsonPath("$.id", is(notNullValue()))).
				andExpect(jsonPath("$.email", is(user.getEmail()))).
				andExpect(jsonPath("$.answers", IsMapContaining.hasValue(firstOption)));
	}
	
	

	private String getHtmlTag(List<Question> questions) {
		StringBuilder htmlTag = new StringBuilder();
		for (Question question : questions) {
			htmlTag.append("<div class='parent-questions-div' style='display:show'>");
			htmlTag.append("<span class=\"d-block\">");
			htmlTag.append("<label for=" + StringEscapeUtils.escapeHtml(question.getCategory().getCategory()) + ">"
					+ StringEscapeUtils.escapeHtml(question.getCategory().getCategory()) + "</label>");
			htmlTag.append("/");
			htmlTag.append("&nbsp;");
			htmlTag.append("<label for=" + StringEscapeUtils.escapeHtml(question.getText()) + ">"
					+ StringEscapeUtils.escapeHtml(question.getText()) + "</label>");
			htmlTag.append("</span>");

			for (String option : question.getQuestionType().getOptions().split(",")) {
				htmlTag.append("<div class='fields-div'>");
				htmlTag.append("&nbsp;<input type=radio name='" + question.getId() + "' value='"
						+ StringEscapeUtils.escapeHtml(option) + "' > ");
				htmlTag.append("&nbsp;<label for='" + question.getId() + "'>" + StringEscapeUtils.escapeHtml(option)
						+ "</label>");
				htmlTag.append("</div>");
			}

			htmlTag.append("</div>");
		}
		return htmlTag.toString();
	}

}
