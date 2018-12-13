package com.sparknetworks.service;

import java.util.List;

import com.sparknetworks.model.QuestionCategory;

public interface CategoryService {

	QuestionCategory persist(QuestionCategory category);
	List<QuestionCategory> findAll();
	void delete(QuestionCategory category);
}
