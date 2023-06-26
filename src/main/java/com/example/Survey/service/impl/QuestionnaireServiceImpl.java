package com.example.Survey.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
			LocalDate endDate, String surveyQuestion, String required, String multiple, String surveyOption) {
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

//		�D��-----------
//		���b: ����J���D
		if (!StringUtils.hasText(surveyQuestion)) {
			return new QuestionnaireResponse("����J�D��");
		}

//		���b: ����J���� (�w�]������0)
		
		if (!StringUtils.hasText(required)) {
			return new QuestionnaireResponse("�ФĿ沈��P�_");
		}

//		���b: ����J��ƿ� (�w�]���0)
		if (!StringUtils.hasText(multiple)) {
			return new QuestionnaireResponse("�п�ܳ��Φh��");
		}

//		���b: ����J�ﶵ
		if (!StringUtils.hasText(surveyOption)) {
			return new QuestionnaireResponse("����J�ﶵ");
		}
		
//		���b: ����P�_�B��ƿ�οﶵ�P�D�ؼƶq�۲�
		String[] questionAry = surveyQuestion.split(",");
		String[] requiredAry = required.split(",");
		String[] multipleAry = multiple.split(",");
		String[] optionAry = surveyOption.split(",");
		if (requiredAry.length != questionAry.length || multipleAry.length != questionAry.length
				|| optionAry.length != questionAry.length) {
			return new QuestionnaireResponse("���ˬd�O�_������J");
		}

//		�s��Ʈw
		return new QuestionnaireResponse(questionnaireDao.save(new Questionnaire(surveyName, surveyCaption, startDate,
				endDate, status, surveyQuestion, required, multiple, surveyOption)), "�s�W�ݨ����\");
	}

	/*
	 * �s�W�ݨ��D�� �e��----- �w�]�D�ءB����B��ƿ�B�ﶵ���Ŧr�� String question = ""; String requiredStr
	 * =""; String multipleStr = ""; String option = "";
	 * 
	 * ���b: ����J���D if (!StringUtils.hasText(surveyQuestion)) { return new
	 * QuestionnaireResponse("����J�D��"); } �e��-----
	 * �Yquestion���šAquestion=��J�̿�J���e�A�_�h�|�[","+��J�̤��e if
	 * (!StringUtils.hasText(question)){ question = surveyQuestion; } else {
	 * question += "," + surveyQuestion; }
	 * 
	 * ���b: ����J���� (�w�]������0) if (!StringUtils.hasText(required)) { required = "�w�]"; }
	 * �e��----- 
	 * �YrequiredStr���šArequiredStr=��J�̿�J���e�A�_�h�|�[","+��J�̤��e
	 * if(!StringUtils.hasText(requiredStr)) { requiredStr = required; } else {
	 * requiredStr += "," + required; } 
	 * ���b: ����J��ƿ� (�w�]���0)
	 * if(!StringUtils.hasText(multiple)) { multiple = "���"; } 
	 * �e��-----
	 * �YmultipleStr���šAmultipleStr=��J�̿�J���e�A�_�h�|�[","+��J�̤��e
	 * if(!StringUtils.hasText(multipleStr)) { multipleStr = multiple; } else {
	 * multipleStr += "," + multiple; } 
	 * ���b: ����J�ﶵ
	 * if(!StringUtils.hasText(surveyOption)) { return new
	 * QuestionnaireResponse("����J�ﶵ"); } 
	 * �e��-----
	 * �Yoption���šAoption=��J�̿�J���e�A�_�h�|�[,+��J�̤��e if (!StringUtils.hasText(option)) {
	 * option = surveyOption; } else { option += "," + surveyOption; }
	 * ----------
	 * �ˬd����P�_�B��ƿ��J�O�_���~
	 * List<String> requireList = Arrays.asList("����", "�D����");
	 * for(String require : requiredAry) {
	 * 		if(!requireList.contains(require)) {
	 * 			return new QuestionnaireResponse("��J���~");
	 * 		}
	 * 	}
	 */

//	�s��ݨ���T
	@Override
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption) {
//		���X�ݨ�
		Optional<Questionnaire> survey = questionnaireDao.findById(serialNumber);
//		���b: �Y�ݨ����s�b
		if (!survey.isPresent()) {
			return new QuestionnaireResponse("�L���ݨ�");
		}
//		���b: �ݨ��i�_�s��(���A��2=�w����, 1=�}�� �h���i�s��)
		if (survey.get().getStatus() == 2 || survey.get().getStatus() == 1) {
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

//		�D��-----------
//		���b: ����J���D
		if (!StringUtils.hasText(surveyQuestion)) {
			return new QuestionnaireResponse("����J�D��");
		}

//		���b: ����J���� (�w�]������0)
		if (!StringUtils.hasText(required)) {
			return new QuestionnaireResponse("�ФĿ沈��P�_");
		}

//		���b: ����J��ƿ� (�w�]���0)
		if (!StringUtils.hasText(multiple)) {
			return new QuestionnaireResponse("�п�ܳ��Φh��");
		}

//		���b: ����J�ﶵ
		if (!StringUtils.hasText(surveyOption)) {
			return new QuestionnaireResponse("����J�ﶵ");
		}

//		���b: ����P�_�B��ƿ�οﶵ�P�D�ؼƶq�۲�
		String[] questionAry = surveyQuestion.split(",");
		String[] requiredAry = required.split(",");
		String[] multipleAry = multiple.split(",");
		String[] optionAry = surveyOption.split(",");
		if (requiredAry.length != questionAry.length || multipleAry.length != questionAry.length
				|| optionAry.length != questionAry.length) {
			return new QuestionnaireResponse("���ˬd�O�_������J");
		}

//		�s��Ʈw
		survey.get().setSurveyName(surveyName);
		survey.get().setSurveyCaption(surveyCaption);
		survey.get().setStartDate(startDate);
		survey.get().setEndDate(endDate);
		survey.get().setStatus(status);
		survey.get().setSurveyQuestion(surveyQuestion);
		survey.get().setRequired(required);
		survey.get().setMultiple(multiple);
		return new QuestionnaireResponse(questionnaireDao.save(survey.get()), "�s��ݨ���T���\");
	}

	
//	�R���ݨ�
	@Override
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList) {
		List<Questionnaire> allSurveyList = questionnaireDao.findAll();
		
//		�N�n�R�����ݨ��[�Jlist
		List<Questionnaire> deleteList = new ArrayList<>();
		for(Questionnaire survey : allSurveyList) {
			for(Integer deleteSurvey : deleteSurveyList) {
				if(survey.getSerialNumber() == deleteSurvey) {
					deleteList.add(survey);
				}
			}
		}
//		���b: �T�{���L���R���ݨ�(����R���ݨ�list���׻Preq�O�_�ۦP)
		if(deleteList.size() != deleteSurveyList.size()) {
			return new QuestionnaireResponse("�L���ݨ�");
		}
		
//		���b: �ˬd���ݨ����A(���}��~��R��)
		for(Questionnaire deleteSurvey : deleteList) {
			if(deleteSurvey.getStatus() != 0) {
				return new QuestionnaireResponse("�ݨ��L�k�R��");
			}
		}
		
		questionnaireDao.deleteAllById(deleteSurveyList);
		return new QuestionnaireResponse("�R���ݨ����\");
	}

	
//	��Ҧ��ݨ�
	@Override
	public QuestionnaireResponse findAllSurvey() {
		List<Questionnaire> allSurvey = questionnaireDao.findAllByOrderBySerialNumberDesc();
		return new QuestionnaireResponse(allSurvey, "���\");
	}

//	�ק�ݨ��}�񪬺A
	@Override
	public QuestionnaireResponse closeSurvey() {
		List<Questionnaire> allSurvey = questionnaireDao.findAll();
		for(Questionnaire survey : allSurvey) {
			if(survey.getEndDate().isBefore(LocalDate.now())) {
				survey.setStatus(2);
			}else if(survey.getEndDate().isEqual(LocalDate.now())) {
				survey.setStatus(1);
			}
		}
		questionnaireDao.saveAll(allSurvey);
		return new QuestionnaireResponse(allSurvey, "���\");
	}




}
