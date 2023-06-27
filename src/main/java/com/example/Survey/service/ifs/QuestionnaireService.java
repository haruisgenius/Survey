package com.example.Survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.vo.QuestionnaireResponse;

public interface QuestionnaireService {

//	�s�W�ݨ�
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate);
	
//	�s��ݨ�
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate);
	
//	�ק�ݨ����A
	public QuestionnaireResponse changeSurveyStatus();
	
//	�R���ݨ�
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList);
	
//	��Ҧ��ݨ�
	public QuestionnaireResponse findAllSurvey();
	

}
