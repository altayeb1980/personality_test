package com.sparknetworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.Question;
import com.sparknetworks.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository personalityTestRepository;

	@Override
	public Question persist(Question question) {
		return personalityTestRepository.save(question);
	}

	@Override
	public void delete(Question question) {
		personalityTestRepository.delete(question);
	}
	
	@Override
	public List<Question> findAll() {
		return personalityTestRepository.findAll();
	}

}
