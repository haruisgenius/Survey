package com.example.Survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.vo.QuestionnaireOldResponse;

public interface QuestionnaireOldService {

//	新增問卷(暫存session)
	public QuestionnaireOldResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);

//	編輯問卷資訊
	public QuestionnaireOldResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);
	
//	刪除問卷
	public QuestionnaireOldResponse deleteSurvey(List<Integer> deleteSurveyList);
	
//	列出所有問卷
	public QuestionnaireOldResponse findAllSurvey();
	
//	修改問卷開放狀態
	public QuestionnaireOldResponse closeSurvey();

//	撈出問卷內容(作答用)
	public QuestionnaireOldResponse showSurvey(Integer serialNumber);

	
}
