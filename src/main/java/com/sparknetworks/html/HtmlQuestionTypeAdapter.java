package com.sparknetworks.html;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.HtmlInputTypes;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;

@Service
public class HtmlQuestionTypeAdapter {

	public Map<Question, HtmlTag> buildHtmlTag(final Question question) {
		Map<Question, HtmlTag> map = new HashMap<>();
		QuestionType questionType = question.getQuestionType();
		StringBuilder builder = new StringBuilder();
		HtmlInputTypes htmlInputType = HtmlInputTypes.valueOf(questionType.getType());
		switch (htmlInputType) {
		case single_choice:
		case single_choice_conditional:
			for (String option : questionType.getOptions().split(",")) {
				builder.append("<div>");
				builder.append("&nbsp;<input type=" + htmlInputType.getHtmlInputType() + " id=" + question.getId() + " name=" + question.getId()
						+ " value='" + option + "'>");
				builder.append("&nbsp;<label for='" + option + "'>" + StringEscapeUtils.escapeHtml(option) + "</label>");
				builder.append("</div>");
			}
			map.put(question, new HtmlTag(builder.toString()));
			break;
		default:

		}
		return map;
	}

}
