package com.sparknetworks.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;

import com.sparknetworks.AbstractApplicationTest;
import com.sparknetworks.model.db.Question;
import com.sparknetworks.model.db.QuestionType;

public class JsonFileReaderTest extends AbstractApplicationTest {
	
	
	@Autowired JsonFileReader jsonFileReader;
	
	@Test
	public void testReadJsonData() throws Exception {
		List<Question> questions = jsonFileReader.readJsonData();
		assertNotNull(questions);
		
		Question question = questions.get(0);
		QuestionType questionType = question.getQuestionType();
		assertEquals("What is your gender?", question.getQuestion());
		assertEquals("hard_fact", question.getQuestionCategories().hard_fact.name());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("male", "female", "other")), is(questionType.getOptions()));

		question = questions.get(1);
		questionType = question.getQuestionType();
		assertEquals("How important is the gender of your partner?", question.getQuestion());
		assertEquals("hard_fact", question.getQuestionCategories().hard_fact.name());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("not important", "important", "very important")), is(questionType.getOptions()));

		question = questions.get(2);
		questionType = question.getQuestionType();
		assertEquals("How important is the age of your partner to you?", question.getQuestion());
		assertEquals("hard_fact", question.getQuestionCategories().hard_fact.name());
		assertEquals("single_choice_conditional", questionType.getType());
		assertThat(String.join(",", Arrays.asList("not important", "important", "very important")), is(questionType.getOptions()));

		question = questions.get(3);
		questionType = question.getQuestionType();
		assertEquals("Do any children under the age of 18 live with you?", question.getQuestion());
		assertEquals("hard_fact", question.getQuestionCategories().hard_fact.name());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("yes", "sometimes", "no")), is(questionType.getOptions()));

		question = questions.get(4);
		questionType = question.getQuestionType();
		assertEquals("How should your potential partner respond to this question?", question.getQuestion());
		assertEquals("lifestyle", question.getQuestionCategories().lifestyle.name());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("yes", "sometimes", "no")), is(questionType.getOptions()));
		
	}
	
	
	@Test(expected=FileNotFoundException.class)
	public void testJsonFileNotExisists() throws Exception {
		jsonFileReader.setResource(new InputStreamResource(new FileInputStream(new File("d://personality_test.json"))));
	}

}
