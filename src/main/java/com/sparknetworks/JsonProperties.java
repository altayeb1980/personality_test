package com.sparknetworks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import com.sparknetworks.model.Question;

@Configuration
@ConfigurationProperties(prefix = "custom")
public class JsonProperties {

	private List<String> categories = new ArrayList<String>();

	@NestedConfigurationProperty
	private List<Question> questions = new ArrayList<Question>();

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
