package com.example.Survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.vo.QuestionnaireResponse;

public interface QuestionnaireService {

//	新增問卷(暫存session)
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);

//	編輯問卷資訊
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);
	
//	刪除問卷
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList);
	
//	列出所有問卷
	public QuestionnaireResponse findAllSurvey();
	
//	修改問卷開放狀態
	public QuestionnaireResponse closeSurvey();
	

	
}
