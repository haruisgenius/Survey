package com.example.Survey.service.ifs;

import java.util.List;

import com.example.Survey.entity.Question;
import com.example.Survey.vo.QuestionResponse;

public interface QuestionService {

//	�s�W�D��
	public QuestionResponse createQuestion(List<Question> questionList);
	
//	�s���D��
	public QuestionResponse updateQuestion(int surveyNumber, List<Question> questionList);

}
