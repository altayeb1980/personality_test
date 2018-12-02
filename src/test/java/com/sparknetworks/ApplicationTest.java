package com.sparknetworks;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparknetworks.model.PersonalityTest;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;
import com.sparknetworks.service.PersonalityTestParserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
    private PersonalityTestParserService personalityTestParserService;
	
	private PersonalityTest personalityTest;
	@Before
	public void init() throws IOException {
		personalityTest = personalityTestParserService.parse();
	}
	
	@Test
	public void whenPersonalityTestParserServiceLoaded_thenJsonValuesLoadedIntoClassObject() {
	    assertNotNull(personalityTestParserService);
	    assertNotNull(personalityTest.getCategories());
	    assertNotNull(personalityTest.getQuestions());
	    assertEquals(4, personalityTest.getCategories().size());
	    assertEquals(25, personalityTest.getQuestions().size());
	}

	
	
	@Test
	public void whenPersonalityTestParserServiceLoaded_thenQuestionsLoadedCorrect() {
		Question question = personalityTest.getQuestions().get(0);
		QuestionType questionType = question.getQuestionType();
		assertEquals("What is your gender?", question.getQuestion());
		assertEquals("hard_fact", question.getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(Arrays.asList("male", "female", "other"), is(questionType.getOptions()));
		
		question = personalityTest.getQuestions().get(1);
		questionType = question.getQuestionType();
		assertEquals("How important is the gender of your partner?", question.getQuestion());
		assertEquals("hard_fact", question.getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(Arrays.asList("not important", "important", "very important"), is(questionType.getOptions()));
		
		question = personalityTest.getQuestions().get(2);
		questionType = question.getQuestionType();
		assertEquals("How important is the age of your partner to you?", question.getQuestion());
		assertEquals("hard_fact", question.getCategory());
		assertEquals("single_choice_conditional", questionType.getType());
		assertThat(Arrays.asList("not important", "important", "very important"), is(questionType.getOptions()));
		
		
		question = personalityTest.getQuestions().get(3);
		questionType = question.getQuestionType();
		assertEquals("Do any children under the age of 18 live with you?", question.getQuestion());
		assertEquals("hard_fact", question.getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(Arrays.asList("yes", "sometimes", "no"), is(questionType.getOptions()));

		
		question = personalityTest.getQuestions().get(4);
		questionType = question.getQuestionType();
		assertEquals("How should your potential partner respond to this question?", question.getQuestion());
		assertEquals("lifestyle", question.getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(Arrays.asList("yes", "sometimes", "no"),is(questionType.getOptions()));
		
	}

	
	
	
	
}
