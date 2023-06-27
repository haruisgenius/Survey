package com.example.Survey.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.entity.Questionnaire;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.QuestionnaireService;
import com.example.Survey.vo.QuestionnaireResponse;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	private QuestionnaireDao questionnaireDao;

//	�s�W�ݨ�
	@Override
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate) {
//		���b: ����J�ݨ����D
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireResponse("�ݨ����D����");
		}

//		�ݨ������i����

//		���b: ����J�}�l�ɶ�, �h�w�]�����
		if (startDate == null || startDate.toString().isBlank()) {
			startDate = LocalDate.now();
		}

//		���b: ����J�����ɶ�, �w�]���}�l�ɶ�+7��
		if (endDate == null || endDate.toString().isBlank()) {
			endDate = startDate.plusDays(7);
		}

//		���b: �}�l�ɶ����i������, �����ɶ����i�p(��)��}�l�ɶ�
		if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(startDate)) {
			return new QuestionnaireResponse("�}�l�ε����ɶ����~");
		}

//		�ݨ����A: �}�l�ɶ�(0=���}��, 1=�}��, 2=�w����)
		int status = 0;
		if (startDate.equals(LocalDate.now())) {
			status = 1;
		}

		return new QuestionnaireResponse(
				questionnaireDao.save(new Questionnaire(surveyName, surveyCaption, status, startDate, endDate)),
				"�s�W�ݨ����\");
	}

//	�s��ݨ�
	@Override
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate) {
//		���X�ݨ�
		Optional<Questionnaire> survey = questionnaireDao.findById(serialNumber);
//		���b: �Y�ݨ����s�b
		if (!survey.isPresent()) {
			return new QuestionnaireResponse("�L���ݨ�");
		}
//		���b: �ݨ��i�_�s��(���A��2=�w����, 1=�}�� �h���i�s��)
		if (survey.get().getSurveyStatus() == 2 || survey.get().getSurveyStatus() == 1) {
			return new QuestionnaireResponse("�ݨ��L�k�s��");
		}

//		���b: ����J�ݨ����D
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireResponse("�ݨ����D����");
		}
//		�Y����J�ݨ�����, �h���Ŧr��
		if (!StringUtils.hasText(surveyCaption)) {
			surveyCaption = "";
		}
//		���b: ����J�}�l�ɶ�, �h�w�]�����
		if (startDate == null || startDate.toString().isBlank()) {
			startDate = LocalDate.now();
		}

//�e��---------------
//		���b: ����J�����ɶ�, �w�]���}�l�ɶ�+7��
		if (endDate == null || endDate.toString().isBlank()) {
			endDate = startDate.plusDays(7);
		}

//		���b: �}�l�ɶ����i������, �����ɶ����i�p(��)��}�l�ɶ�
		if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(startDate)) {
			return new QuestionnaireResponse("�}�l�ε����ɶ����~");
		}
//�e��---------------

//		�ݨ����A: �}�l�ɶ�(0=���}��, 1=�}��, 2=�w����)
		int status = 0;
		if (startDate == LocalDate.now()) {
			status = 1;
		}

		survey.get().setSurveyName(surveyName);
		survey.get().setSurveyCaption(surveyCaption);
		survey.get().setSurveyStatus(status);
		survey.get().setStartDate(startDate);
		survey.get().setEndDate(endDate);
		return new QuestionnaireResponse(questionnaireDao.save(survey.get()), "���\");
	}

//	�ק�ݨ����A
	@Override
	public QuestionnaireResponse changeSurveyStatus() {
		List<Questionnaire> allSurvey = questionnaireDao.findAll();
		for(Questionnaire survey : allSurvey) {
//			�����ɶ��w�L�A���A��2�w����
			if(survey.getEndDate().isBefore(LocalDate.now())) {
				survey.setSurveyStatus(2);
//				�}�l�ɶ����󤵤ѡA���A��1�}�l
			}else if(survey.getStartDate().isEqual(LocalDate.now())) {
				survey.setSurveyStatus(1);
			}
		}
		
		return new QuestionnaireResponse(questionnaireDao.saveAll(allSurvey), "���\");
	}


//	�R���ݨ�
	@Override
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList) {
//		�˭n�R�����ݨ�list
		List<Questionnaire> allSurveyList = questionnaireDao.findAll();
//		�n�R�����ݨ�list
		List<Questionnaire> deleteList = new ArrayList<>();
		for(Questionnaire survey : allSurveyList) {
			// �P�_�Y���ݨ����y�������breq���A�[�JdeleteList
			if(deleteSurveyList.contains(survey.getSerialNumber())) {
				deleteList.add(survey);
			}
		}
//		���b: �P�_�ݨ��s�b
		// �P�_deleteList���׻Preq�O�_�ۦP�A�Y�_�A�h����R���ݨ����t���s�b���ݨ�
		if(deleteList.size() != deleteSurveyList.size()) {
			return new QuestionnaireResponse("�ݨ����s�b");
		}
//		���b: �ݨ����A0=���}�� �~�i�R���ݨ�
		for(Questionnaire deleteSurvey : deleteList) {
			if(deleteSurvey.getSurveyStatus() != 0) {
				return new QuestionnaireResponse("�ݨ��L�k�R��");
			}
		}
		
		questionnaireDao.deleteAllById(deleteSurveyList);
		return new QuestionnaireResponse("���\");
	}

	
//	��Ҧ��ݨ�
	@Override
	public QuestionnaireResponse findAllSurvey() {
		List<Questionnaire> allSurvey = questionnaireDao.findAllByOrderBySerialNumberDesc();
		return new QuestionnaireResponse(allSurvey, "���\");
	}
	
//	
	
	

}
