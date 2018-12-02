package com.sparknetworks;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPropertyContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private final static String CUSTOM_PREFIX = "custom.";

	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

		// ObjectMapper mapper = new ObjectMapper();
		// TypeReference<PersonalityTest> typeReference = new
		// TypeReference<PersonalityTest>(){};
		//
		// Resource resource =
		// configurableApplicationContext.getResource("classpath:personality_test.json");
		//
		// try {
		// PersonalityTest personalityTest =
		// mapper.readValue(resource.getInputStream(),typeReference);
		// configurableApplicationContext.getEnvironment().getPropertySources().addFirst();
		// } catch (IOException e){
		// throw new RuntimeException(e);
		// }

		try {
			Resource resource = configurableApplicationContext.getResource("classpath:personality_test.json");
			Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
			Set<Map.Entry> set = readValue.entrySet();
			List<MapPropertySource> propertySources = convertEntrySet(set, Optional.empty());
			for (PropertySource propertySource : propertySources) {
				configurableApplicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<MapPropertySource> convertEntrySet(Set<Map.Entry> entrySet, Optional<String> parentKey) {
		return entrySet.stream().map((Map.Entry e) -> convertToPropertySourceList(e, parentKey))
				.flatMap(Collection::stream).collect(Collectors.toList());
	}

	private List<MapPropertySource> convertToPropertySourceList(Map.Entry e, Optional<String> parentKey) {
		String key = parentKey.map(s -> s + ".").orElse("") + (String) e.getKey();
		Object value = e.getValue();
		return covertToPropertySourceList(key, value);
	}

	@SuppressWarnings("unchecked")
	private List<MapPropertySource> covertToPropertySourceList(String key, Object value) {
		if (value instanceof LinkedHashMap) {
			LinkedHashMap map = (LinkedHashMap) value;
			Set<Map.Entry> entrySet = map.entrySet();
			return convertEntrySet(entrySet, Optional.ofNullable(key));
		}
		String finalKey = CUSTOM_PREFIX + key;
		return Collections.singletonList(new MapPropertySource(finalKey, Collections.singletonMap(finalKey, value)));
	}

}
