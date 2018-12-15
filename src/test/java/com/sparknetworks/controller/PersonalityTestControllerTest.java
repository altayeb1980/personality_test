package com.sparknetworks.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sparknetworks.html.HtmlTagBuilderService;
import com.sparknetworks.model.Question;
import com.sparknetworks.service.AbstractPersonalityTest;
import com.sparknetworks.service.QuestionService;

@WebAppConfiguration
public class PersonalityTestControllerTest extends AbstractPersonalityTest {

	@Autowired
	private WebApplicationContext wac;

	@MockBean
	private HtmlTagBuilderService htmlTagBuilderServiceMock;

	@MockBean
	private QuestionService questionServiceMock;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void renderIndexViewWithCategories() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).
		andExpect(view().name("questions")).
		andExpect(model().attributeExists("questionsMap")).
	    andExpect(model().attribute("questionsMap", IsMapContaining.hasValue(htmlTag)));

	verify(questionServiceMock, times(1)).findAllWithParentIdNull(Mockito.any());
	verify(htmlTagBuilderServiceMock, times(1)).buildHtmlTag(Mockito.any());
	verifyNoMoreInteractions(htmlTagBuilderServiceMock);
	
	}
	
	
	@Test
	public void findAllQuestionsAndRenderQuestionsToViewAsHtmlTags() throws Exception {
		List<Question> questions = getQuestions(false);
		String htmlTag = getHtmlTag(questions);

		when(questionServiceMock.findAllWithParentIdNull(Mockito.any())).thenReturn(questions);
		when(htmlTagBuilderServiceMock.buildHtmlTag(Mockito.any())).thenReturn(htmlTag.toString());

		mockMvc.perform(get("/questions")).andExpect(status().isOk()).
			andExpect(view().name("questions")).
			andExpect(model().attributeExists("questionsMap")).
		    andExpect(model().attribute("questionsMap", IsMapContaining.hasValue(htmlTag)));

		verify(questionServiceMock, times(1)).findAllWithParentIdNull(Mockito.any());
		verify(htmlTagBuilderServiceMock, times(1)).buildHtmlTag(Mockito.any());
		verifyNoMoreInteractions(htmlTagBuilderServiceMock);
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

	// private Question createQuestion() {
	// Question question = new Question();
	// QuestionType questionType = new QuestionType();
	// question.setQuestion("What is your gender?");
	// question.setCategory("hard_fact");
	// questionType.setType("single_choice");
	// questionType.setOptions(Arrays.asList("male", "female", "other"));
	// question.setQuestionType(questionType);
	// return question;
	// }

}
