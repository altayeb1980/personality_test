package com.sparknetworks.service;

import com.sparknetworks.model.db.Question;

public interface QuestionPersistenceService {

	Question persist(Question question);
	void delete(Question question);
	
	
}
