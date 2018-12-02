package com.sparknetworks.service;

import java.util.List;

import com.sparknetworks.exception.QuestionException;
import com.sparknetworks.model.Question;

public interface QuestionService {
	
	List<Question> listQuestions() throws QuestionException;
}
