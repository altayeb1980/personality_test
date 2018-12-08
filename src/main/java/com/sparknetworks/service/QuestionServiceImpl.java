package com.sparknetworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.db.Question;
import com.sparknetworks.repository.PersonalityTestRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private PersonalityTestRepository personalityTestRepository;

	@Override
	public Question persist(Question question) {
		return personalityTestRepository.save(question);
	}

	@Override
	public void delete(Question question) {
		personalityTestRepository.delete(question);
	}
}
