package com.sparknetworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparknetworks.model.QuestionCategory;

@Repository
public interface CategoryRepository extends JpaRepository<QuestionCategory,Long>{
	QuestionCategory findByCategory(String category);
}

