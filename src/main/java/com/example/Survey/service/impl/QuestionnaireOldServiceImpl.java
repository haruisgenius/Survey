package com.example.Survey.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.entity.QuestionnaireOld;
import com.example.Survey.respository.QuestionnaireOldDao;
import com.example.Survey.service.ifs.QuestionnaireOldService;
import com.example.Survey.vo.QuestionnaireOldResponse;

@Service
public class QuestionnaireOldServiceImpl implements QuestionnaireOldService {

	@Autowired
	private QuestionnaireOldDao questionnaireOldDao;

//	�s�W�ݨ�
	@Override
	public QuestionnaireOldResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple, String surveyOption) {
//		���b: ����J�ݨ����D
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireOldResponse("�ݨ����D����");
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
			return new QuestionnaireOldResponse("�}�l�ε����ɶ����~");
		}

//		�ݨ����A: �}�l�ɶ�(0=���}��, 1=�}��, 2=�w����)
		int status = 0;
		if (startDate.equals(LocalDate.now())) {
			status = 1;
		}

//		�D��-----------
//		���b: ����J���D
		if (!StringUtils.hasText(surveyQuestion)) {
			return new QuestionnaireOldResponse("����J�D��");
		}

//		���b: ����J���� (�w�]������0)
		
		if (!StringUtils.hasText(required)) {
			return new QuestionnaireOldResponse("�ФĿ沈��P�_");
		}

//		���b: ����J��ƿ� (�w�]���0)
		if (!StringUtils.hasText(multiple)) {
			return new QuestionnaireOldResponse("�п�ܳ��Φh��");
		}

//		���b: ����J�ﶵ
		if (!StringUtils.hasText(surveyOption)) {
			return new QuestionnaireOldResponse("����J�ﶵ");
		}
		
//		���b: ����P�_�B��ƿ�οﶵ�P�D�ؼƶq�۲�
		String[] questionAry = surveyQuestion.split(",");
		String[] requiredAry = required.split(",");
		String[] multipleAry = multiple.split(",");
		String[] optionAry = surveyOption.split(",");
		if (requiredAry.length != questionAry.length || multipleAry.length != questionAry.length
				|| optionAry.length != questionAry.length) {
			return new QuestionnaireOldResponse("���ˬd�O�_������J");
		}

//		�s��Ʈw
		return new QuestionnaireOldResponse(questionnaireOldDao.save(new QuestionnaireOld(surveyName, surveyCaption, startDate,
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
	public QuestionnaireOldResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption) {
//		���X�ݨ�
		Optional<QuestionnaireOld> survey = questionnaireOldDao.findById(serialNumber);
//		���b: �Y�ݨ����s�b
		if (!survey.isPresent()) {
			return new QuestionnaireOldResponse("�L���ݨ�");
		}
//		���b: �ݨ��i�_�s��(���A��2=�w����, 1=�}�� �h���i�s��)
		if (survey.get().getStatus() == 2 || survey.get().getStatus() == 1) {
			return new QuestionnaireOldResponse("�ݨ��L�k�s��");
		}

//		���b: ����J�ݨ����D
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireOldResponse("�ݨ����D����");
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
			return new QuestionnaireOldResponse("�}�l�ε����ɶ����~");
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
			return new QuestionnaireOldResponse("����J�D��");
		}

//		���b: ����J���� (�w�]������0)
		if (!StringUtils.hasText(required)) {
			return new QuestionnaireOldResponse("�ФĿ沈��P�_");
		}

//		���b: ����J��ƿ� (�w�]���0)
		if (!StringUtils.hasText(multiple)) {
			return new QuestionnaireOldResponse("�п�ܳ��Φh��");
		}

//		���b: ����J�ﶵ
		if (!StringUtils.hasText(surveyOption)) {
			return new QuestionnaireOldResponse("����J�ﶵ");
		}

//		���b: ����P�_�B��ƿ�οﶵ�P�D�ؼƶq�۲�
		String[] questionAry = surveyQuestion.split(",");
		String[] requiredAry = required.split(",");
		String[] multipleAry = multiple.split(",");
		String[] optionAry = surveyOption.split(",");
		if (requiredAry.length != questionAry.length || multipleAry.length != questionAry.length
				|| optionAry.length != questionAry.length) {
			return new QuestionnaireOldResponse("���ˬd�O�_������J");
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
		return new QuestionnaireOldResponse(questionnaireOldDao.save(survey.get()), "�s��ݨ���T���\");
	}

	
//	�R���ݨ�
	@Override
	public QuestionnaireOldResponse deleteSurvey(List<Integer> deleteSurveyList) {
		List<QuestionnaireOld> allSurveyList = questionnaireOldDao.findAll();
		
//		�N�n�R�����ݨ��[�Jlist
		List<QuestionnaireOld> deleteList = new ArrayList<>();
		for(QuestionnaireOld survey : allSurveyList) {
			for(Integer deleteSurvey : deleteSurveyList) {
				if(survey.getSerialNumber() == deleteSurvey) {
					deleteList.add(survey);
				}
			}
		}
//		���b: �T�{���L���R���ݨ�(����R���ݨ�list���׻Preq�O�_�ۦP)
		if(deleteList.size() != deleteSurveyList.size()) {
			return new QuestionnaireOldResponse("�L���ݨ�");
		}
		
//		���b: �ˬd���ݨ����A(���}��~��R��)
		for(QuestionnaireOld deleteSurvey : deleteList) {
			if(deleteSurvey.getStatus() != 0) {
				return new QuestionnaireOldResponse("�ݨ��L�k�R��");
			}
		}
		
		questionnaireOldDao.deleteAllById(deleteSurveyList);
		return new QuestionnaireOldResponse("�R���ݨ����\");
	}

	
//	��Ҧ��ݨ�
	@Override
	public QuestionnaireOldResponse findAllSurvey() {
		List<QuestionnaireOld> allSurvey = questionnaireOldDao.findAllByOrderBySerialNumberDesc();
		return new QuestionnaireOldResponse(allSurvey, "���\");
	}

//	�ק�ݨ��}�񪬺A
	@Override
	public QuestionnaireOldResponse closeSurvey() {
		List<QuestionnaireOld> allSurvey = questionnaireOldDao.findAll();
		for(QuestionnaireOld survey : allSurvey) {
			if(survey.getEndDate().isBefore(LocalDate.now())) {
				survey.setStatus(2);
			}else if(survey.getEndDate().isEqual(LocalDate.now())) {
				survey.setStatus(1);
			}
		}
		questionnaireOldDao.saveAll(allSurvey);
		return new QuestionnaireOldResponse(allSurvey, "���\");
	}

	
//	���X�ݨ����e(�@����)
	@Override
	public QuestionnaireOldResponse showSurvey(Integer serialNumber) {
		Optional<QuestionnaireOld> survey = questionnaireOldDao.findById(serialNumber);
		if(!survey.isPresent()) {
			return new QuestionnaireOldResponse("�L���ݨ�");
		}
		QuestionnaireOld surveyInfo = survey.get();
		if(surveyInfo.getStatus() == 0) {
			return new QuestionnaireOldResponse("���ݨ��|���}��@��");
		}else if(surveyInfo.getStatus() == 2) {
			return new QuestionnaireOldResponse("���ݨ��w����");
		}
		return new QuestionnaireOldResponse(surveyInfo, "���\");
	}




}
