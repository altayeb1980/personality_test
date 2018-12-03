package com.sparknetworks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sparknetworks.html.HtmlQuestionTypeAdapter;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionView;
import com.sparknetworks.service.QuestionService;

@Controller
public class PersonalityTestController {

	
	@Autowired
	private QuestionService questionService;
	
	private HtmlQuestionTypeAdapter htmlQuestionTypeAdapter;
	
	
	@GetMapping("/questions")
	public @ResponseBody ModelAndView allQuestions() {
		ModelAndView model = new ModelAndView("questions");
		List<Question> listQuestions = questionService.listQuestions();
		Map<QuestionView, String> questionViewMap = htmlQuestionTypeAdapter.buildHtmlTag(listQuestions);
		model.addObject("questionViewMap",questionViewMap);
		return model;
	}
	
	
	
	
	
}
