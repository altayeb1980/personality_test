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


@Controller
public class PersonalityTestController {

	
//	@Autowired
//	private QuestionService questionService;

//	@Autowired
//	private HtmlQuestionTypeAdapter htmlQuestionTypeAdapter;
	
	
	@GetMapping("/")
	public @ResponseBody ModelAndView init() {
		ModelAndView model = new ModelAndView("questions");
//		List<Question> listQuestions = questionService.listQuestions();
//		
//		if(listQuestions.size() > 0) {
//			Map<QuestionView, String> questionViewMap = htmlQuestionTypeAdapter.buildHtmlTag(listQuestions.get(0));
//			model.addObject("questionViewMap",questionViewMap);
//		}else {
//			
//		}
		
		
		return model;
	}
	
	
//	@GetMapping("/questions")
//	public @ResponseBody ModelAndView allQuestions() {
//		ModelAndView model = new ModelAndView("questions");
//		List<Question> listQuestions = questionService.listQuestions();
//		Map<QuestionView, String> questionViewMap = htmlQuestionTypeAdapter.buildHtmlTag(listQuestions);
//		model.addObject("questionViewMap",questionViewMap);
//		return model;
//	}
	
	
	
//	@GetMapping("/getNextQuestion")
//	public @ResponseBody ModelAndView getNextQuestion() {
//		ModelAndView model = new ModelAndView("questions");
//		List<Question> listQuestions = questionService.listQuestions();
//		Map<QuestionView, String> questionViewMap = htmlQuestionTypeAdapter.buildHtmlTag(listQuestions);
//		model.addObject("questionViewMap",questionViewMap);
//		return model;
//	}
	
	
	
	
	
	
}
