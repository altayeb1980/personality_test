package com.sparknetworks.html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sparknetworks.model.HtmlInputTypes;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;
import com.sparknetworks.model.QuestionView;

public class HtmlQuestionTypeAdapter {


	public Map<QuestionView, String> buildHtmlTag(final List<Question> questions) {
		
		Map<QuestionView, String> map = new HashMap<>();
		for (Question question : questions) {
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
					builder.append("<label for="+option+">Huey</label>");
					builder.append("</div>");
				}
				map.put(questionView, builder.toString());
				break;
//			case number_range:
//				builder.append("<div>");
//				builder.append("<input type="+htmlInputType.getHtmlInputType()+" id="+option+" name="+option+" value="+option+" min="+questionType.get+">");
//				builder.append("<label for="+option+">Huey</label>");
//				builder.append("</div>");
//				
//				break;

			default:

			}

		}
		return map;
	}

}
