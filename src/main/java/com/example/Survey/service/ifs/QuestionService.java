package com.example.Survey.service.ifs;

import java.util.List;

import com.example.Survey.entity.Question;
import com.example.Survey.vo.QuestionResponse;

public interface QuestionService {

//	新增題目
	public QuestionResponse createQuestion(List<Question> questionList);
	
//	編輯題目
	public QuestionResponse updateQuestion(int surveyNumber, List<Question> questionList);

}
