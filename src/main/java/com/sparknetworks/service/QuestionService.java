package com.sparknetworks.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.sparknetworks.model.Question;

public interface QuestionService {

	Question persist(Question question);

	void delete(Question question);

	List<Question> findAllWithParentIdNull(Specification<Question> specification);
	Question findByText(String questionText);
	

	
}
