package com.sparknetworks;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparknetworks.model.db.Question;
import com.sparknetworks.model.db.QuestionCategories;
import com.sparknetworks.model.db.QuestionType;



@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractApplicationTest {


	//protected PersonalityTest personalityTest;

	@Before
	public void init() throws IOException {
		//personalityTest = jsonFileReaderService.getPersonalityTest();
	}
	
	
	public Question createQuestion() {
		return new Question("What is your gender?", QuestionCategories.hard_fact,new QuestionType("single_choice", String.join(",", Arrays.asList("male", "female", "other")), null, null));
	}

}
