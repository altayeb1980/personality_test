package com.sparknetworks.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.sparknetworks.model.QuestionCategory;



public class CategoryServiceTest extends AbstractPersonalityTest{

	
	@Test
	public void shouldCreateQuestionCategories() {
		for(QuestionCategory category: categories.values()) {
			QuestionCategory persistedCategory = categoryService.persist(category);
			assertThat(persistedCategory.getId(), notNullValue());
			assertThat(persistedCategory.getCategory(), equalTo(category.getCategory()));
		}
	}

}
