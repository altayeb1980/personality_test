package com.sparknetworks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;

@Service
public class JsonFilePersistenceService {

	@Autowired
	private JsonFileReader jsonFileReader;
	@Autowired
	private QuestionService jsonPersistenceService;
	@Autowired
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		
		Map<String,QuestionCategory> categories = new HashMap<>();
		
		for(QuestionCategory category:jsonFileReader.readCategories()) {
			categories.put(category.getCategory(),categoryService.persist(category));
		}
		
		List<Question> questions = jsonFileReader.readJsonFile(categories);
		for (Question question : questions) {
			jsonPersistenceService.persist(question);
		}
	}
}
