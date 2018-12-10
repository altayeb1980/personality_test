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
import com.sparknetworks.model.User;
import com.sparknetworks.service.QuestionService;
import com.sparknetworks.service.UserService;


@Controller
public class PersonalityTestController {

	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;

	@Autowired
	private HtmlTagBuilderService htmlQuestionTypeAdapter;
	
	
	@GetMapping("/")
	public @ResponseBody ModelAndView init() {
		ModelAndView model = new ModelAndView("questions");
		List<Question> questions = questionService.findAll();
		Map<Question, String> map = new HashMap<>();
		for(Question question:questions) {
			if(question.getParent()!=null) {
				continue;
			}
			map.put(question, htmlQuestionTypeAdapter.buildHtmlTag(question));
		}
		model.addObject("questionsMap",map);
		return model;
	}
	
	@PostMapping("/answers")
	@ResponseBody
	public User answers(@RequestBody User user) {
		return userService.persist(user);
	}
	
}
