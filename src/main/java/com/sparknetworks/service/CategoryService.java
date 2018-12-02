package com.sparknetworks.service;

import java.util.List;

import com.sparknetworks.exception.CategoryException;

public interface CategoryService {

	List<String> listCategories() throws CategoryException;
}
