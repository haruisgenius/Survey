package com.example.Survey.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Survey.entity.Question;
import com.example.Survey.entity.Questionnaire;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	public List<Question> findBySurveyNumberAndQuestionNumberIn(int surveyNumber, List<Integer> questionNumList);

	public List<Question> findBySurveyNumber(int surveyNumber);
}
