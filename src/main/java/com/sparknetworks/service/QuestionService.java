package com.sparknetworks.service;

import java.util.List;

import com.sparknetworks.model.Question;

public interface QuestionService {

	Question persist(Question question);

	void delete(Question question);

	List<Question> findAll();
	

	
}
