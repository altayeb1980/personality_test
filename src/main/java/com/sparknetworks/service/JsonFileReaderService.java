package com.sparknetworks.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparknetworks.exception.JsonParserException;
import com.sparknetworks.exception.ResourceNotFoundException;
import com.sparknetworks.model.PersonalityTest;


@Service
public class JsonFileReaderService{

	
	@Value("${json.config.folder}")
	private Resource resource;

	private PersonalityTest personalityTest; 
	
	@PostConstruct
	public void init() {
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			String dataInput = getDataInput();
			personalityTest = jsonMapper.readValue(dataInput, new TypeReference<PersonalityTest>() {
			});
		}catch(ResourceNotFoundException e) {
			throw new RuntimeException("resource not find "+ resource.getFilename());
		} catch (IOException e) {
			throw new JsonParserException("unable to parse json file "+resource.getFilename());
		}
	}
	
	private String getDataInput() throws ResourceNotFoundException {
		InputStream is;
		try {
			is = resource.getInputStream();
			return new BufferedReader(new InputStreamReader(is))
					  .lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new ResourceNotFoundException(e);
		}
	}
	
	public PersonalityTest getPersonalityTest() {
		return personalityTest;
	}
}
