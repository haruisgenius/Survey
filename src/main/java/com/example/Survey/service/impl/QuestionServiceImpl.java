package com.example.Survey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.constants.RtnCode;
import com.example.Survey.entity.Question;
import com.example.Survey.entity.Questionnaire;
import com.example.Survey.respository.QuestionDao;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.QuestionService;
import com.example.Survey.vo.QuestionResponse;
import com.example.Survey.vo.QuestionnaireResponse;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionnaireDao questionnaireDao;

//	新增題目
	@Override
	public QuestionResponse createQuestion(List<Question> questionList) {
		List<Questionnaire> allQuestionnaire = questionnaireDao.findAll();
		List<Integer> allSurveyNumber = new ArrayList<>();
		for (Questionnaire questionnaire : allQuestionnaire) {
			allSurveyNumber.add(questionnaire.getSerialNumber());
		}
		for (int i = 0; i < questionList.size(); i++) {
//			防呆: 判斷是否有輸入問卷編號
			if (questionList.get(i).getSurveyNumber() == 0) {
				return new QuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
			}
//			防呆: 判斷問卷是否存在
			if (!allSurveyNumber.contains(questionList.get(i).getSurveyNumber())) {
				return new QuestionResponse(RtnCode.NOT_FOUND.getMessage());
			}
//			防呆: 問題不可為空
			if (!StringUtils.hasText(questionList.get(i).getQuestion())) {
				return new QuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
			}
//			防呆: 若未輸入needs則布林值為false > 非必填
			if (questionList.get(i).getNeeds() == null) {
				questionList.get(i).setNeeds(false);
				;
			}
//			防呆: 若未輸入multiple則布林值為false > 單選
			if (questionList.get(i).getMultiple() == null) {
				questionList.get(i).setMultiple(false);
			}
//			防呆: 選項不可為空
			if (!StringUtils.hasText(questionList.get(i).getqOption())) {
				return new QuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
			}

		}

		return new QuestionResponse(questionDao.saveAll(questionList), RtnCode.SUCCESSFUL.getMessage());
	}

//	編輯題目
	@Override
	public QuestionResponse updateQuestion(int surveyNumber, List<Question> questionList) {
//		將欲修改題目之流水號撈出存入list
		List<Integer> updateQtNumList = new ArrayList<>();
		for (Question questionNum : questionList) {
			updateQtNumList.add(questionNum.getQuestionNumber());
		}
//		用問卷編號撈出此問卷的所有題目
		List<Question> updateQuestionList = questionDao.findBySurveyNumberAndQuestionNumberIn(surveyNumber,
				updateQtNumList);
//		防呆: 無此問卷或此問卷無此題目
		if (updateQuestionList.size() != questionList.size()) {
			return new QuestionResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		for(Question originQuesiton : updateQuestionList) {
			for(Question reqQuestion : questionList) {
//				若原問題流水號與欲修改流水號不符則跳出
				if(originQuesiton.getQuestionNumber() != reqQuestion.getQuestionNumber()) {
					continue;
				}
				// 相符執行修改
				// 防呆: 問題為空
				if(!StringUtils.hasText(reqQuestion.getQuestion())) {
					return new QuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
				}
				// 通過則存入新問題
				originQuesiton.setQuestion(reqQuestion.getQuestion());
				// 防呆: 若未輸入needs則布林值為false > 非必填
				if(reqQuestion.getNeeds() == null) {
					originQuesiton.setNeeds(false);
				}
				// 防呆: 若未輸入multiple則布林值為false > 單選
				if(reqQuestion.getMultiple() == null) {
					originQuesiton.setMultiple(false);
				}
				// 防呆: 選項為空
				if(!StringUtils.hasText(reqQuestion.getqOption())) {
					return new QuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
				}
				// 若通過存入新選項
				originQuesiton.setqOption(reqQuestion.getqOption());
			}
		}

		return new QuestionResponse(questionDao.saveAll(updateQuestionList), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public QuestionResponse findSurveyQuestion(int surveyNumber) {
		
		return new QuestionResponse(questionDao.findBySurveyNumber(surveyNumber), RtnCode.SUCCESSFUL.getMessage());
	}

	
	
	
}
