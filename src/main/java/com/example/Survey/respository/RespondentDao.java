package com.example.Survey.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Survey.entity.Respondent;

@Repository
public interface RespondentDao extends JpaRepository<Respondent, Integer> {

	public List<Respondent> findBySurveyNumberOrderByAnswerTimeDesc(int surveyNumber);
	
}
