package com.sparknetworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<QuestionCategory> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public QuestionCategory persist(QuestionCategory category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(QuestionCategory category) {
		categoryRepository.delete(category);
	}

	@Override
	public QuestionCategory findByCategory(String category) {
		return categoryRepository.findByCategory(category);
	}
	
	
}
