package com.sparknetworks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sparknetworks.model.Question;
import com.sparknetworks.service.CategoryService;
import com.sparknetworks.service.QuestionService;

@Controller
public class PersonalityTestController {

	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/questions")
	public @ResponseBody ModelAndView allQuestions() {
		ModelAndView model = new ModelAndView("questions");
		List<Question> listQuestions = questionService.listQuestions();
		model.addObject("questions",listQuestions);
		return model;
	}
	
	
	@GetMapping("/categories")
	public ModelAndView allCategories() {
		ModelAndView model = new ModelAndView("categories");
		model.addObject(categoryService.listCategories());
		return model;
	}
	
	
	
}
