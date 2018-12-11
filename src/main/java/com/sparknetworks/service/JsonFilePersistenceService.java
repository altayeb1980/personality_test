package com.sparknetworks.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.Question;

@Service
public class JsonFilePersistenceService {

	@Autowired
	private JsonFileReader jsonFileReader;
	@Autowired
	private QuestionService jsonPersistenceService;

	@PostConstruct
	public void init() {
		List<Question> questions = jsonFileReader.readJsonFile();
		for (Question question : questions) {
			jsonPersistenceService.persist(question);
		}
	}
}
