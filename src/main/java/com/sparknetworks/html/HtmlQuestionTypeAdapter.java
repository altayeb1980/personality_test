package com.sparknetworks.html;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sparknetworks.model.HtmlInputTypes;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;
import com.sparknetworks.model.QuestionView;

@Service
public class HtmlQuestionTypeAdapter {


	public Map<QuestionView, String> buildHtmlTag(final Question question) {
		
		Map<QuestionView, String> map = new HashMap<>();
		//for (Question question : questions) {
			QuestionView questionView = new QuestionView(question.getQuestion(), question.getCategory());
			
			QuestionType questionType = question.getQuestionType();
			StringBuilder builder = new StringBuilder();
			HtmlInputTypes htmlInputType = HtmlInputTypes.valueOf(questionType.getType());
			switch (htmlInputType) {
			case single_choice:
			case single_choice_conditional:
				
				for(String option:questionType.getOptions()) {
					builder.append("<div>");
					builder.append("<input type="+htmlInputType.getHtmlInputType()+" id="+option+" name="+option+" value="+option+">");
					builder.append("<label for="+option+">"+option+"</label>");
					builder.append("</div>");
				}
				
				
				if(questionType.getCondition() != null) {
					
				}
				
				map.put(questionView, builder.toString());
				break;

			default:

			}

		//}
		return map;
	}

}
