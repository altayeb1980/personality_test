package com.sparknetworks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sparknetworks.html.HtmlTagBuilderService;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.model.User;
import com.sparknetworks.repository.QuestionSpecification;
import com.sparknetworks.service.CategoryService;
import com.sparknetworks.service.QuestionService;
import com.sparknetworks.service.UserService;

@Controller
public class PersonalityTestController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;

	@Autowired
	private HtmlTagBuilderService htmlQuestionTypeService;

	@GetMapping("/")
	public @ResponseBody ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		List<QuestionCategory> categories= categoryService.findAll();
		model.addObject("categories", categories);
		return model;
	}

	
	
	@GetMapping("/questions")
	public @ResponseBody ModelAndView questions() {
		ModelAndView model = new ModelAndView("questions");
		List<Question> questions = questionService.findAllWithParentIdNull(new QuestionSpecification());
		Map<Question, String> map = new HashMap<>();
		for (Question question : questions) {
			map.put(question, htmlQuestionTypeService.buildHtmlTag(question));
		}
		model.addObject("questionsMap", map);
		return model;
	}
	
	
	@PostMapping("/answers")
	@ResponseBody
	public User answers(@RequestBody User user) {
		User existing  = userService.findByEmail(user.getEmail());
		if(existing != null) {
			user = new User(user.getEmail());
			user.setAnswers(existing.getAnswers());
			user.setId(existing.getId());
		}
		return userService.persist(user);
	}
}
