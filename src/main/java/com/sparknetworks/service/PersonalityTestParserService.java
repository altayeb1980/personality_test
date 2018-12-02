package com.sparknetworks.service;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.model.PersonalityTest;

public interface PersonalityTestParserService {

	default PersonalityTest parse() throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper.readValue(getDataInput(), new TypeReference<PersonalityTest>() {
		});
	}

	String getDataInput() throws IOException;
}
