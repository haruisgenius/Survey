package com.example.Survey.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.constants.RtnCode;
import com.example.Survey.entity.Questionnaire;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.QuestionnaireService;
import com.example.Survey.vo.QuestionnaireResponse;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	private QuestionnaireDao questionnaireDao;

//	新增問卷
	@Override
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate) {
//		防呆: 未輸入問卷標題
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}

//		問卷說明可為空

//		防呆: 未輸入開始時間, 則預設為當日
		if (startDate == null || startDate.toString().isBlank()) {
			startDate = LocalDate.now();
		}

//		防呆: 未輸入結束時間, 預設為開始時間+7日
		if (endDate == null || endDate.toString().isBlank()) {
			endDate = startDate.plusDays(7);
		}

//		防呆: 開始時間不可早於當日, 結束時間不可小(早)於開始時間
		if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(startDate)) {
			return new QuestionnaireResponse(RtnCode.INCORRECT.getMessage());
		}

//		問卷狀態: 開始時間(0=未開放, 1=開放中, 2=已結束)
		int status = 0;
		if (startDate.equals(LocalDate.now())) {
			status = 1;
		}

		return new QuestionnaireResponse(
				questionnaireDao.save(new Questionnaire(surveyName, surveyCaption, status, startDate, endDate)),
				RtnCode.SUCCESSFUL.getMessage());
	}

//	編輯問卷
	@Override
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate) {
//		撈出問卷
		Optional<Questionnaire> survey = questionnaireDao.findById(serialNumber);
//		防呆: 若問卷不存在
		if (!survey.isPresent()) {
			return new QuestionnaireResponse(RtnCode.NOT_FOUND.getMessage());
		}
//		防呆: 問卷可否編輯(狀態為2=已結束, 1=開放中 則不可編輯)
		if (survey.get().getSurveyStatus() == 2 || survey.get().getSurveyStatus() == 1) {
			return new QuestionnaireResponse(RtnCode.INCORRECT.getMessage());
		}

//		防呆: 未輸入問卷標題
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
//		若未輸入問卷說明, 則為空字串
		if (!StringUtils.hasText(surveyCaption)) {
			surveyCaption = "";
		}
//		防呆: 未輸入開始時間, 則預設為當日
		if (startDate == null || startDate.toString().isBlank()) {
			startDate = LocalDate.now();
		}

//前端---------------
//		防呆: 未輸入結束時間, 預設為開始時間+7日
		if (endDate == null || endDate.toString().isBlank()) {
			endDate = startDate.plusDays(7);
		}

//		防呆: 開始時間不可早於當日, 結束時間不可小(早)於開始時間
		if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(startDate)) {
			return new QuestionnaireResponse(RtnCode.INCORRECT.getMessage());
		}
//前端---------------

//		問卷狀態: 開始時間(0=未開放, 1=開放中, 2=已結束)
		int status = 0;
		if (startDate == LocalDate.now()) {
			status = 1;
		}

		survey.get().setSurveyName(surveyName);
		survey.get().setSurveyCaption(surveyCaption);
		survey.get().setSurveyStatus(status);
		survey.get().setStartDate(startDate);
		survey.get().setEndDate(endDate);
		return new QuestionnaireResponse(questionnaireDao.save(survey.get()), RtnCode.SUCCESSFUL.getMessage());
	}

//	修改問卷狀態
	@Override
	public QuestionnaireResponse changeSurveyStatus() {
		List<Questionnaire> allSurvey = questionnaireDao.findAll();
		for(Questionnaire survey : allSurvey) {
//			結束時間已過，狀態改2已結束
			if(survey.getEndDate().isBefore(LocalDate.now())) {
				survey.setSurveyStatus(2);
//				開始時間等於今天，狀態改1開始
			}else if(survey.getStartDate().isEqual(LocalDate.now())) {
				survey.setSurveyStatus(1);
			}
		}
		
		return new QuestionnaireResponse(questionnaireDao.saveAll(allSurvey), RtnCode.SUCCESSFUL.getMessage());
	}


//	刪除問卷
	@Override
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList) {
//		裝要刪除的問卷list
		List<Questionnaire> allSurveyList = questionnaireDao.findAll();
//		要刪除的問卷list
		List<Questionnaire> deleteList = new ArrayList<>();
		for(Questionnaire survey : allSurveyList) {
			// 判斷若此問卷之流水號有在req內，加入deleteList
			if(deleteSurveyList.contains(survey.getSerialNumber())) {
				deleteList.add(survey);
			}
		}
//		防呆: 判斷問卷存在
		// 判斷deleteList長度與req是否相同，若否，則表欲刪除問卷內含不存在之問卷
		if(deleteList.size() != deleteSurveyList.size()) {
			return new QuestionnaireResponse(RtnCode.NOT_FOUND.getMessage());
		}
//		防呆: 問卷狀態0=未開放 才可刪除問卷
		for(Questionnaire deleteSurvey : deleteList) {
			if(deleteSurvey.getSurveyStatus() != 0) {
				return new QuestionnaireResponse(RtnCode.INCORRECT.getMessage());
			}
		}
		
		questionnaireDao.deleteAllById(deleteSurveyList);
		return new QuestionnaireResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	
//	找所有問卷
	@Override
	public QuestionnaireResponse findAllSurvey() {
		List<Questionnaire> allSurvey = questionnaireDao.findAllByOrderBySerialNumberDesc();
		return new QuestionnaireResponse(allSurvey, RtnCode.SUCCESSFUL.getMessage());
	}

//	找問卷
	@Override
	public QuestionnaireResponse findSurvey(Integer serialNumber) {
		Optional<Questionnaire> surveyOp = questionnaireDao.findById(serialNumber);
		Questionnaire survey = surveyOp.get();
		return new QuestionnaireResponse(survey, RtnCode.SUCCESSFUL.getMessage());
	}

	
	
//	
	
	

}
