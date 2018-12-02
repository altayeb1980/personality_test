package com.sparknetworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.exception.QuestionException;
import com.sparknetworks.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService{

	
	@Autowired
	private JsonFileReaderService jsonFileReaderService;
	
	@Override
	public List<Question> listQuestions() throws QuestionException {
		return jsonFileReaderService.getPersonalityTest().getQuestions();
	}

}
