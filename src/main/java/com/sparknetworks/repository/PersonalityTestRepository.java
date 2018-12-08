package com.sparknetworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparknetworks.model.Question;

@Repository
public interface PersonalityTestRepository extends JpaRepository<Question,Long>{

}
