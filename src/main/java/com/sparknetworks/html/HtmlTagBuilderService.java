package com.sparknetworks.html;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionType;

/**
 * 
 * @author Al-Tayeb_Saadeh
 *
 */
@Service
public class HtmlTagBuilderService {

	public String buildHtmlTag(final Question question) {
		return buildQuestionTag(question, "class=parent-questions-div","style=display:show");
	}

	private String buildQuestionTag(Question question, String id,String style) {
		StringBuilder builder = new StringBuilder();
		builder.append("<div "+id+" "+ style+ ">");
		builder.append("<span class=\"d-block\">");
		builder.append("<label for=" + StringEscapeUtils.escapeHtml(question.getCategory().getCategory()) + ">"
				+ StringEscapeUtils.escapeHtml(question.getCategory().getCategory()) + "</label>");
		builder.append("/");
		builder.append("&nbsp;");
		builder.append("<label for=" + StringEscapeUtils.escapeHtml(question.getText()) + ">"
				+ StringEscapeUtils.escapeHtml(question.getText()) + "</label>");
		builder.append("</span>");

		String inputType = buildInputType(question, style);
		builder.append(inputType);
		

		String conditionValue = question.getCondValue();
		if (conditionValue != null) {
			Question childQuestion = question.getChildren().get(0);
			builder.append(buildQuestionTag(childQuestion, "class=child-questions-div","style=display:none"));
			//builder.append("<input type='hidden' id='parent-condition-" + question.getId() + "'" + " value='" + conditionValue + "," + childQuestion.getId() + "'>");
			builder.append("<input type='hidden' id='parent-condition-" + question.getId() + "'" + " value='" + conditionValue+"'>");
		}
		builder.append("</div>");
		return builder.toString();
	}

	private String buildInputType(Question question, String style) {
		StringBuilder builder = new StringBuilder();
		QuestionType questionType = question.getQuestionType();
		HtmlInputTypes htmlInputType = HtmlInputTypes.valueOf(questionType.getType());
		
		switch (htmlInputType) {
		case single_choice:
		case single_choice_conditional:

			for (String option : questionType.getOptions().split(",")) {
				builder.append("<div class='fields-div'>");		
				builder.append("&nbsp;<input type=" + htmlInputType.getHtmlInputType() + " name='" + question.getId()
						+ "' value='" + StringEscapeUtils.escapeHtml(option) + "' > ");
				builder.append("&nbsp;<label for='" + question.getId() + "'>" + StringEscapeUtils.escapeHtml(option)
						+ "</label>");
				builder.append("</div>");		
			}

			break;
		case number_range:
			String ranges[] = questionType.getRanges().split(",");
			String minRange = ranges[0];
			String maxRange = ranges[1];

			builder.append("<div class='fieldsDiv'>");
			builder.append("<input type=" + htmlInputType.getHtmlInputType() + " name="
					+ question.getId() + " min=" + minRange + " max=" + maxRange + ">");
			builder.append("</div>");
			break;

		default:

		}
		
		return builder.toString();
	}

}
