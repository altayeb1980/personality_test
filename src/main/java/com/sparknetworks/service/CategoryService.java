package com.sparknetworks.service;

import java.util.List;
import java.util.Optional;

import com.sparknetworks.model.QuestionCategory;

public interface CategoryService {

	QuestionCategory persist(QuestionCategory category);
	List<QuestionCategory> findAll();
	void delete(QuestionCategory category);
	Optional<QuestionCategory> findById(Long id);
}
