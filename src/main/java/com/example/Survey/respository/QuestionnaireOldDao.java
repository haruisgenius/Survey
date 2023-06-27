package com.example.Survey.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Survey.entity.QuestionnaireOld;

@Repository
public interface QuestionnaireOldDao extends JpaRepository<QuestionnaireOld, Integer> {

	public List<QuestionnaireOld> findAllByOrderBySerialNumberDesc();
	
}
