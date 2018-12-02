package com.sparknetworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.exception.CategoryException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private JsonFileReaderService jsonFileReaderService;

	@Override
	public List<String> listCategories() throws CategoryException {
		return jsonFileReaderService.getPersonalityTest().getCategories();
	}

}
