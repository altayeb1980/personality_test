package com.sparknetworks;

import java.io.IOException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparknetworks.model.PersonalityTest;
import com.sparknetworks.service.JsonFileReaderService;


@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractApplicationTest {

	@Autowired
	private JsonFileReaderService jsonFileReaderService;

	protected PersonalityTest personalityTest;

	@Before
	public void init() throws IOException {
		personalityTest = jsonFileReaderService.getPersonalityTest();
	}

}
