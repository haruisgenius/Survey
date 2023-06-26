package com.example.Survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.vo.QuestionnaireResponse;

public interface QuestionnaireService {

//	�s�W�ݨ�(�Ȧssession)
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);

//	�s��ݨ���T
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);
	
//	�R���ݨ�
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList);
	
//	�C�X�Ҧ��ݨ�
	public QuestionnaireResponse findAllSurvey();
	
//	�ק�ݨ��}�񪬺A
	public QuestionnaireResponse closeSurvey();
	

	
}
