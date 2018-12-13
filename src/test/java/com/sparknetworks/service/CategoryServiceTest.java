package com.sparknetworks.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparknetworks.model.QuestionCategory;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	
	private Map<String,QuestionCategory> categories = new HashMap<>();
	
	@Before
	public void init() throws IOException {
		categories.put("hard_fact", new QuestionCategory("hard_fact"));
		categories.put("lifestyle", new QuestionCategory("lifestyle"));
		categories.put("introversion", new QuestionCategory("introversion"));
		categories.put("passion", new QuestionCategory("passion"));
	}
	
	@After
	public void destroy() {
		for(QuestionCategory category: categories.values()) {
			categoryService.delete(category);
		}
	}

	

	@Test
	public void shouldPersistQuestionCategory() {
		for(QuestionCategory category: categories.values()) {
			QuestionCategory persistedCategory = categoryService.persist(category);
			assertThat(persistedCategory.getId(), notNullValue());
			assertThat(persistedCategory.getCategory(), equalTo(category.getCategory()));
		}
	}

}
