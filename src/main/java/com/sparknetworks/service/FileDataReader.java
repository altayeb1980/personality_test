package com.sparknetworks.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
public class FileDataReader implements PersonalityTestParserService {

	
	@Value("${json.config.folder}")
	private Resource resource;

	@Override
	public String getDataInput() throws IOException {
		InputStream is = resource.getInputStream();
		return new BufferedReader(new InputStreamReader(is))
				  .lines().collect(Collectors.joining("\n"));
		
		
	}
}
