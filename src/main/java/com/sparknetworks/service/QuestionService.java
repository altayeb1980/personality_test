package com.sparknetworks.service;

import com.sparknetworks.model.Question;

public interface QuestionService {

	Question persist(Question question);
	void delete(Question question);
	
	
	
}
