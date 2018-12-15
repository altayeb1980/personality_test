package com.sparknetworks.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.User;

public class UserServiceTest extends AbstractPersonalityTest{

	@Autowired
	private UserService userService;
	
	private User user;

	
	@Override
	public void init() {
		user = createUser();
	}

	@Override
	public void destroy() {
		userService.delete(user);
	}

	@Test
	public void shouldCreateUserWithAnswers() {
		
		for (Question q : questions) {
			String userAnswer = getUserAnswer(q);
			Question savedQuestion = questionService.persist(q);
			user.getAnswers().put(savedQuestion.getId(), userAnswer);
			User savedUser = userService.persist(user);
			assertThat(savedUser.getId(), notNullValue());
			assertThat(savedUser.getEmail(), equalTo("xyz@gmail.com"));
			assertThat(savedUser.getAnswers().get(savedQuestion.getId()), equalTo(userAnswer));
			
		}
		
	}

	private String getUserAnswer(Question q) {
		String userAnswer = "";
		if(q.getQuestionType().getOptions() != null && q.getQuestionType().getOptions().split(",").length > 1) {
			userAnswer = q.getQuestionType().getOptions().split(",")[1];
		}
		return userAnswer;
	}

	private User createUser() {
		return new User("xyz@gmail.com");
	}
	
}
