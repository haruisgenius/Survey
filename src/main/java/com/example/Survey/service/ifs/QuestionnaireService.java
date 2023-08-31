package com.example.Survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.vo.QuestionnaireResponse;

public interface QuestionnaireService {

//	新增問卷
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate);
	
//	編輯問卷
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate);
	
//	修改問卷狀態
	public QuestionnaireResponse changeSurveyStatus();
	
//	刪除問卷
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList);
	
//	找所有問卷
	public QuestionnaireResponse findAllSurvey();
	
//	找問卷
	public QuestionnaireResponse findSurvey(Integer serialNumber);
	

}
