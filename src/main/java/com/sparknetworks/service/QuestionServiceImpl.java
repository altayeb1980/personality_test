package com.sparknetworks.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.Question;
import com.sparknetworks.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question persist(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public void delete(Question question) {
		questionRepository.delete(question);
	}
	
	@Override
	@Transactional
	public List<Question> findAllWithParentIdNull(Specification<Question> specification) {
		return questionRepository.findAll(specification);
	}

}
