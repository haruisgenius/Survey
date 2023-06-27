package com.example.Survey.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.vo.QuestionnaireOldResponse;

public interface QuestionnaireOldService {

//	�s�W�ݨ�(�Ȧssession)
	public QuestionnaireOldResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);

//	�s��ݨ���T
	public QuestionnaireOldResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption);
	
//	�R���ݨ�
	public QuestionnaireOldResponse deleteSurvey(List<Integer> deleteSurveyList);
	
//	�C�X�Ҧ��ݨ�
	public QuestionnaireOldResponse findAllSurvey();
	
//	�ק�ݨ��}�񪬺A
	public QuestionnaireOldResponse closeSurvey();

//	���X�ݨ����e(�@����)
	public QuestionnaireOldResponse showSurvey(Integer serialNumber);

	
}
