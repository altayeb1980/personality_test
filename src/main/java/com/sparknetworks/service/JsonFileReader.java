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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.exception.ResourceNotFoundException;
import com.sparknetworks.model.db.Question;
import com.sparknetworks.model.db.QuestionCategories;
import com.sparknetworks.model.db.QuestionCondition;
import com.sparknetworks.model.db.QuestionType;

@Service
public class JsonFileReader {
	@Value("${json.config.folder}")
	private Resource resource;

	public List<Question> readJsonData() {
		ObjectMapper jsonMapper = new ObjectMapper();
		List<Question> listOfQuestions = new ArrayList<>();
		try {
			String dataInput = getDataInput();
			Map<String, Object> dataJsonMap = jsonMapper.readValue(dataInput, HashMap.class);
			List<Map<String, Object>> questionsMap = (List<Map<String, Object>>) dataJsonMap.get("questions");

			for (Map<String, Object> questionMap : questionsMap) {
				Map<String, Object> questionTypeMap = (Map<String, Object>) questionMap.get("question_type");
				List<String> options = (List<String>) questionTypeMap.get("options");
				Map<String, Object> conditionMap = (Map<String, Object>) questionTypeMap.get("condition");
				QuestionCondition questionCondition = null;
				if (conditionMap != null && !conditionMap.isEmpty()) {
					Map<String, Object> predicateConditionMap = (Map<String, Object>) conditionMap.get("predicate");
					List<String> exactEqualList = (List<String>) predicateConditionMap.get("exactEquals");
					if (exactEqualList != null && !exactEqualList.isEmpty()) {
						String exactEquals = exactEqualList.get(1);
						Map<String, Object> positiveConditionMap = (Map<String, Object>) conditionMap
								.get("if_positive");

						Map<String, Object> questionTypeConditionMap = (Map<String, Object>) positiveConditionMap
								.get("question_type");
						Map<String, Object> questionTypeRangeConditionMap = (Map<String, Object>) questionTypeConditionMap
								.get("range");
						QuestionType questionType = new QuestionType(
								String.valueOf(questionTypeConditionMap.get("type")), null, null,
								questionTypeRangeConditionMap.get("from") + ","
										+ questionTypeRangeConditionMap.get("to"));
						questionCondition = new QuestionCondition(exactEquals,
								new Question(String.valueOf(positiveConditionMap.get("question")),
										QuestionCategories.valueOf(positiveConditionMap.get("category") + ""),
										questionType));
					}
				}
				QuestionType questionType = new QuestionType(String.valueOf(questionTypeMap.get("type")),
						String.join(",", options), questionCondition, null);
				listOfQuestions.add(new Question(String.valueOf(questionMap.get("question")),
						QuestionCategories.valueOf(questionMap.get("category") + ""), questionType));
			}
		} catch (ResourceNotFoundException exp) {
			throw new RuntimeException(exp);
		} catch (IOException exp) {
			throw new RuntimeException(exp);
		}
		return listOfQuestions;
	}

	private String getDataInput() throws ResourceNotFoundException {
		InputStream is;
		try {
			is = resource.getInputStream();
			return new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new ResourceNotFoundException(e);
		}
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
