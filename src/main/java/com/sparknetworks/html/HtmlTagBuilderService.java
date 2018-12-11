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
		return buildQuestionTag(question, "style=display:show");
	}

	private String buildQuestionTag(Question question, String style) {
		StringBuilder builder = new StringBuilder();

		builder.append("<div id='question-" + question.getId() + "' " + style + ">");
		builder.append("<span class=\"d-block\">");
		builder.append("<label for=" + StringEscapeUtils.escapeHtml(question.getCategory().getCategory()) + ">"
				+ StringEscapeUtils.escapeHtml(question.getCategory().getCategory()) + "</label>");
		builder.append("/");
		builder.append("&nbsp;");
		builder.append("<label for=" + StringEscapeUtils.escapeHtml(question.getText()) + ">"
				+ StringEscapeUtils.escapeHtml(question.getText()) + "</label>");
		builder.append("</span>");

		String inputType = buildInputType(question);
		builder.append(inputType);
		builder.append("</div>");

		String conditionValue = question.getCondValue();
		if (conditionValue != null) {
			Question childQuestion = question.getChildren().get(0);
			builder.append(buildQuestionTag(childQuestion, "style=display:none"));
			builder.append("<input type='hidden' id='parent-condition-" + question.getId() + "'" + " value='"
					+ conditionValue + "," + childQuestion.getId() + "'>");
		}
		return builder.toString();
	}

	private String buildInputType(Question question) {
		StringBuilder builder = new StringBuilder();
		QuestionType questionType = question.getQuestionType();
		HtmlInputTypes htmlInputType = HtmlInputTypes.valueOf(questionType.getType());

		switch (htmlInputType) {
		case single_choice:
		case single_choice_conditional:

			for (String option : questionType.getOptions().split(",")) {
				builder.append("<div>");
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

			builder.append("<div>");
			builder.append("&nbsp;<input required type=" + htmlInputType.getHtmlInputType() + " name="
					+ question.getId() + " min=" + minRange + " max=" + maxRange + " value= " + minRange + ">");
			builder.append("</div>");
			break;

		default:

		}

		return builder.toString();
	}

}
