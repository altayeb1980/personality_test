package com.sparknetworks.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sparknetworks.model.Question;

public class QuestionSpecification implements Specification<Question>{

	@Override
	public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.isNull(root.get("parent")));		
		return predicates.get(0);
	}
}
