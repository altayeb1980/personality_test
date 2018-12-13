package com.sparknetworks.service; 

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.model.QuestionType;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonFileReaderTest{
	
	
	@Autowired 
	private JsonFileReader jsonFileReader;
	
	
	private Map<String,QuestionCategory> categories = new HashMap<>();
	@Before
	public void init() throws IOException {
		categories.put("hard_fact", new QuestionCategory("hard_fact"));
		categories.put("lifestyle", new QuestionCategory("lifestyle"));
		categories.put("introversion", new QuestionCategory("introversion"));
		categories.put("passion", new QuestionCategory("passion"));
	}
	
	
	
	@Test
	public void testReadCategories(){
		List<QuestionCategory> actualCategories = jsonFileReader.readCategories();
		assertNotNull(actualCategories);
		assertThat(categories.values().size(), equalTo(actualCategories.size()));
		assertThat(actualCategories, contains(new QuestionCategory("hard_fact"),new QuestionCategory("lifestyle"),new QuestionCategory("introversion"),new QuestionCategory("passion")));
	}
	
	
	@Test
	public void testReadJsonData() throws Exception {
		List<Question> questions = jsonFileReader.readJsonFile(categories);
		assertNotNull(questions);
		
		Question question = questions.get(0);
		QuestionType questionType = question.getQuestionType();
		assertEquals("What is your gender?", question.getText());
		assertEquals("hard_fact", question.getCategory().getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("male", "female", "other")), equalTo(questionType.getOptions()));

		
		question = questions.get(1);
		questionType = question.getQuestionType();
		assertEquals("How important is the gender of your partner?", question.getText());
		assertEquals("hard_fact", question.getCategory().getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("not important", "important", "very important")), equalTo(questionType.getOptions()));

		question = questions.get(2);
		questionType = question.getQuestionType();
		assertEquals("How important is the age of your partner to you?", question.getText());
		assertEquals("hard_fact", question.getCategory().getCategory());
		assertEquals("single_choice_conditional", questionType.getType());
		assertThat(String.join(",", Arrays.asList("not important", "important", "very important")), equalTo(questionType.getOptions()));

		question = questions.get(3);
		questionType = question.getQuestionType();
		assertEquals("Do any children under the age of 18 live with you?", question.getText());
		assertEquals("hard_fact", question.getCategory().getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("yes", "sometimes", "no")), equalTo(questionType.getOptions()));

		question = questions.get(4);
		questionType = question.getQuestionType();
		assertEquals("How should your potential partner respond to this question?", question.getText());
		assertEquals("lifestyle", question.getCategory().getCategory());
		assertEquals("single_choice", questionType.getType());
		assertThat(String.join(",", Arrays.asList("yes", "sometimes", "no")), equalTo(questionType.getOptions()));
		
	}
	
	
	@Test(expected=FileNotFoundException.class)
	public void testJsonFileNotExisists() throws Exception {
		jsonFileReader.setResource(new InputStreamResource(new FileInputStream(new File("d://personality_test.json"))));
	}

}
