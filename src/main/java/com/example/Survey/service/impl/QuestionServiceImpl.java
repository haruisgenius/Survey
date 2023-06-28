package com.example.Survey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.entity.Question;
import com.example.Survey.entity.Questionnaire;
import com.example.Survey.respository.QuestionDao;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.QuestionService;
import com.example.Survey.vo.QuestionResponse;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionnaireDao questionnaireDao;

//	�s�W�D��
	@Override
	public QuestionResponse createQuestion(List<Question> questionList) {
		List<Questionnaire> allQuestionnaire = questionnaireDao.findAll();
		List<Integer> allSurveyNumber = new ArrayList<>();
		for (Questionnaire questionnaire : allQuestionnaire) {
			allSurveyNumber.add(questionnaire.getSerialNumber());
		}
		for (int i = 0; i < questionList.size(); i++) {
//			���b: �P�_�O�_����J�ݨ��s��
			if (questionList.get(i).getSurveyNumber() == 0) {
				return new QuestionResponse("�п�J�ݨ��s��");
			}
//			���b: �P�_�ݨ��O�_�s�b
			if (!allSurveyNumber.contains(questionList.get(i).getSurveyNumber())) {
				return new QuestionResponse("�L���ݨ�");
			}
//			���b: ���D���i����
			if (!StringUtils.hasText(questionList.get(i).getQuestion())) {
				return new QuestionResponse("�п�J�D��");
			}
//			���b: �Y����Jneeds�h���L�Ȭ�false > �D����
			if (questionList.get(i).getNeeds() == null) {
				questionList.get(i).setNeeds(false);
				;
			}
//			���b: �Y����Jmultiple�h���L�Ȭ�false > ���
			if (questionList.get(i).getMultiple() == null) {
				questionList.get(i).setMultiple(false);
			}
//			���b: �ﶵ���i����
			if (!StringUtils.hasText(questionList.get(i).getqOption())) {
				return new QuestionResponse("�п�J�ﶵ");
			}

		}

		return new QuestionResponse(questionDao.saveAll(questionList), "�s�W�ݨ����\");
	}

//	�s���D��
	@Override
	public QuestionResponse updateQuestion(int surveyNumber, List<Question> questionList) {
//		�N���ק��D�ؤ��y�������X�s�Jlist
		List<Integer> updateQtNumList = new ArrayList<>();
		for (Question questionNum : questionList) {
			updateQtNumList.add(questionNum.getQuestionNumber());
		}
//		�ΰݨ��s�����X���ݨ����Ҧ��D��
		List<Question> updateQuestionList = questionDao.findBySurveyNumberAndQuestionNumberIn(surveyNumber,
				updateQtNumList);
//		���b: �L���ݨ��Φ��ݨ��L���D��
		if (updateQuestionList.size() != questionList.size()) {
			return new QuestionResponse("�L���ݨ����D��");
		}
		
		for(Question originQuesiton : updateQuestionList) {
			for(Question reqQuestion : questionList) {
//				�Y����D�y�����P���ק�y�������ūh���X
				if(originQuesiton.getQuestionNumber() != reqQuestion.getQuestionNumber()) {
					continue;
				}
				// �۲Ű���ק�
				// ���b: ���D����
				if(!StringUtils.hasText(reqQuestion.getQuestion())) {
					return new QuestionResponse("�п�J���D");
				}
				// �q�L�h�s�J�s���D
				originQuesiton.setQuestion(reqQuestion.getQuestion());
				// ���b: �Y����Jneeds�h���L�Ȭ�false > �D����
				if(reqQuestion.getNeeds() == null) {
					originQuesiton.setNeeds(false);
				}
				// ���b: �Y����Jmultiple�h���L�Ȭ�false > ���
				if(reqQuestion.getMultiple() == null) {
					originQuesiton.setMultiple(false);
				}
				// ���b: �ﶵ����
				if(!StringUtils.hasText(reqQuestion.getqOption())) {
					return new QuestionResponse("�п�J�ﶵ");
				}
				// �Y�q�L�s�J�s�ﶵ
				originQuesiton.setqOption(reqQuestion.getqOption());
			}
		}

		return new QuestionResponse(questionDao.saveAll(updateQuestionList), "�ק令�\");
	}

}
