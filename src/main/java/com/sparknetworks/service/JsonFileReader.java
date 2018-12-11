package com.sparknetworks.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.exception.ResourceNotFoundException;
import com.sparknetworks.model.Question;
import com.sparknetworks.model.QuestionCategory;
import com.sparknetworks.model.QuestionType;

@Service
public class JsonFileReader {
	private static final int CONDITION_EXACT_EQUAL_INDEX = 1;
	private static final String TO_JSON_FIELD = "to";
	private static final String FROM_JSON_FIELD = "from";
	private static final String RANGE_JSON_FIELD = "range";
	private static final String IF_POSITIVE_JSON_FIELD = "if_positive";
	private static final String EXACT_EQUALS_JSON_FIELD = "exactEquals";
	private static final String PREDICATE_JSON_FIELD = "predicate";
	private static final String TYPE_JSON_FIELD = "type";
	private static final String QUESTION_JSON_FIELD = "question";
	private static final String CATEGORY_JSON_FIELD = "category";
	private static final String CONDITION_JSON_FIELD = "condition";
	private static final String OPTIONS_JSON_FIELD = "options";
	private static final String QUESTION_TYPE_JSON_FIELD = "question_type";
	private static final String QUESTIONS_JSON_FIELD = "questions";
	private static final String CATEGORIES_JSON_FIELD = "categories";

	@Value("${json.config.folder}")
	private Resource resource;

	public List<QuestionCategory> readCategories() {

		ObjectMapper jsonMapper = new ObjectMapper();
		List<QuestionCategory> categories = new ArrayList<>();
		try {
			String dataInput = getDataInput();
			Map<String, Object> dataJsonMap = jsonMapper.readValue(dataInput, HashMap.class);
			List<String> categoryList = (List<String>) dataJsonMap.get(CATEGORIES_JSON_FIELD);
			for (String category : categoryList) {
				categories.add(new QuestionCategory(category));
			}
		} catch (ResourceNotFoundException exp) {
			throw new RuntimeException(exp);
		} catch (IOException exp) {
			throw new RuntimeException(exp);
		}

		return categories;
	}

	public List<Question> readJsonFile(Map<String,QuestionCategory> categories) {
		ObjectMapper jsonMapper = new ObjectMapper();
		List<Question> listOfQuestions = new ArrayList<>();
		try {
			String dataInput = getDataInput();
			Map<String, Object> dataJsonMap = jsonMapper.readValue(dataInput, HashMap.class);
			List<Map<String, Object>> questionsMap = (List<Map<String, Object>>) dataJsonMap.get(QUESTIONS_JSON_FIELD);

			for (Map<String, Object> questionMap : questionsMap) {
				Map<String, Object> questionTypeMap = (Map<String, Object>) questionMap.get(QUESTION_TYPE_JSON_FIELD);
				Map<String, Object> conditionMap = (Map<String, Object>) questionTypeMap.get(CONDITION_JSON_FIELD);

				
				
				Question parentQuestion = new Question.Builder()
						.withQuestionCategory(categories.get(questionMap.get(CATEGORY_JSON_FIELD) + ""))
						.withQuestionType(new QuestionType(String.valueOf(questionTypeMap.get(TYPE_JSON_FIELD)),
								String.join(",", (List<String>) questionTypeMap.get(OPTIONS_JSON_FIELD)), null))
						.withText(String.valueOf(questionMap.get(QUESTION_JSON_FIELD))).withEmptyChildren().build();

				if (conditionMap != null && !conditionMap.isEmpty()) {
					Question childQuestion = getChildQuestion(categories,conditionMap, parentQuestion);
					parentQuestion.getChildren().add(childQuestion);
				}

				listOfQuestions.add(parentQuestion);
			}
		} catch (ResourceNotFoundException exp) {
			throw new RuntimeException(exp);
		} catch (IOException exp) {
			throw new RuntimeException(exp);
		}
		return listOfQuestions;
	}

	private Question getChildQuestion(Map<String,QuestionCategory> categories,Map<String, Object> conditionMap, Question parentQuestion) {
		String conditionExactValue = getConditionExactValue(conditionMap);
		Map<String, Object> positiveConditionMap = (Map<String, Object>) conditionMap.get(IF_POSITIVE_JSON_FIELD);
		Map<String, Object> questionTypeConditionMap = (Map<String, Object>) positiveConditionMap
				.get(QUESTION_TYPE_JSON_FIELD);
		Map<String, Object> questionTypeRangeConditionMap = (Map<String, Object>) questionTypeConditionMap
				.get(RANGE_JSON_FIELD);

		Question childQuestion = new Question.Builder()
				.withQuestionCategory(categories.get(positiveConditionMap.get(CATEGORY_JSON_FIELD) + ""))
				.withQuestionType(new QuestionType(String.valueOf(questionTypeConditionMap.get(TYPE_JSON_FIELD)), null,
						questionTypeRangeConditionMap.get(FROM_JSON_FIELD) + ","
								+ questionTypeRangeConditionMap.get(TO_JSON_FIELD)))
				.withText(String.valueOf(positiveConditionMap.get(QUESTION_JSON_FIELD))).withParent(parentQuestion)
				.withConditionExactValue(conditionExactValue).build();
		return childQuestion;
	}

	private String getConditionExactValue(Map<String, Object> conditionMap) {
		Map<String, Object> predicateConditionMap = (Map<String, Object>) conditionMap.get(PREDICATE_JSON_FIELD);
		List<String> exactEqualList = (List<String>) predicateConditionMap.get(EXACT_EQUALS_JSON_FIELD);
		if (exactEqualList != null && !exactEqualList.isEmpty()) {
			return exactEqualList.get(CONDITION_EXACT_EQUAL_INDEX);
		}
		return StringUtils.EMPTY;
	}

	private String getDataInput() throws ResourceNotFoundException {
		try (InputStream is = resource.getInputStream()) {
			return new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new ResourceNotFoundException(e);
		}
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
