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

//	新增問卷
	@Override
	public QuestionnaireResponse createSurvey(String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, String surveyQuestion, String required, String multiple, String surveyOption) {
//		防呆: 未輸入問卷標題
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireResponse("問卷標題為空");
		}
//		若未輸入問卷說明, 則為空字串
		if (!StringUtils.hasText(surveyCaption)) {
			surveyCaption = "";
		}
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
			return new QuestionnaireResponse("開始或結束時間錯誤");
		}

//		問卷狀態: 開始時間(0=未開放, 1=開放中, 2=已結束)
		int status = 0;
		if (startDate.equals(LocalDate.now())) {
			status = 1;
		}

//		題目-----------
//		防呆: 未輸入問題
		if (!StringUtils.hasText(surveyQuestion)) {
			return new QuestionnaireResponse("未輸入題目");
		}

//		防呆: 未輸入必填 (預設不必填0)
		
		if (!StringUtils.hasText(required)) {
			return new QuestionnaireResponse("請勾選必填與否");
		}

//		防呆: 未輸入單複選 (預設單選0)
		if (!StringUtils.hasText(multiple)) {
			return new QuestionnaireResponse("請選擇單選或多選");
		}

//		防呆: 未輸入選項
		if (!StringUtils.hasText(surveyOption)) {
			return new QuestionnaireResponse("未輸入選項");
		}
		
//		防呆: 必填與否、單複選及選項與題目數量相符
		String[] questionAry = surveyQuestion.split(",");
		String[] requiredAry = required.split(",");
		String[] multipleAry = multiple.split(",");
		String[] optionAry = surveyOption.split(",");
		if (requiredAry.length != questionAry.length || multipleAry.length != questionAry.length
				|| optionAry.length != questionAry.length) {
			return new QuestionnaireResponse("請檢查是否都有填入");
		}

//		存資料庫
		return new QuestionnaireResponse(questionnaireDao.save(new Questionnaire(surveyName, surveyCaption, startDate,
				endDate, status, surveyQuestion, required, multiple, surveyOption)), "新增問卷成功");
	}

	/*
	 * 新增問卷題目 前端----- 預設題目、必填、單複選、選項之空字串 String question = ""; String requiredStr
	 * =""; String multipleStr = ""; String option = "";
	 * 
	 * 防呆: 未輸入問題 if (!StringUtils.hasText(surveyQuestion)) { return new
	 * QuestionnaireResponse("未輸入題目"); } 前端-----
	 * 若question為空，question=輸入者輸入內容，否則疊加","+輸入者內容 if
	 * (!StringUtils.hasText(question)){ question = surveyQuestion; } else {
	 * question += "," + surveyQuestion; }
	 * 
	 * 防呆: 未輸入必填 (預設不必填0) if (!StringUtils.hasText(required)) { required = "預設"; }
	 * 前端----- 
	 * 若requiredStr為空，requiredStr=輸入者輸入內容，否則疊加","+輸入者內容
	 * if(!StringUtils.hasText(requiredStr)) { requiredStr = required; } else {
	 * requiredStr += "," + required; } 
	 * 防呆: 未輸入單複選 (預設單選0)
	 * if(!StringUtils.hasText(multiple)) { multiple = "單選"; } 
	 * 前端-----
	 * 若multipleStr為空，multipleStr=輸入者輸入內容，否則疊加","+輸入者內容
	 * if(!StringUtils.hasText(multipleStr)) { multipleStr = multiple; } else {
	 * multipleStr += "," + multiple; } 
	 * 防呆: 未輸入選項
	 * if(!StringUtils.hasText(surveyOption)) { return new
	 * QuestionnaireResponse("未輸入選項"); } 
	 * 前端-----
	 * 若option為空，option=輸入者輸入內容，否則疊加,+輸入者內容 if (!StringUtils.hasText(option)) {
	 * option = surveyOption; } else { option += "," + surveyOption; }
	 * ----------
	 * 檢查必填與否、單複選輸入是否有誤
	 * List<String> requireList = Arrays.asList("必填", "非必填");
	 * for(String require : requiredAry) {
	 * 		if(!requireList.contains(require)) {
	 * 			return new QuestionnaireResponse("輸入錯誤");
	 * 		}
	 * 	}
	 */

//	編輯問卷資訊
	@Override
	public QuestionnaireResponse updateSurvey(Integer serialNumber, String surveyName, String surveyCaption,
			LocalDate startDate, LocalDate endDate, String surveyQuestion, String required, String multiple,
			String surveyOption) {
//		撈出問卷
		Optional<Questionnaire> survey = questionnaireDao.findById(serialNumber);
//		防呆: 若問卷不存在
		if (!survey.isPresent()) {
			return new QuestionnaireResponse("無此問卷");
		}
//		防呆: 問卷可否編輯(狀態為2=已結束, 1=開放中 則不可編輯)
		if (survey.get().getStatus() == 2 || survey.get().getStatus() == 1) {
			return new QuestionnaireResponse("問卷無法編輯");
		}

//		防呆: 未輸入問卷標題
		if (!StringUtils.hasText(surveyName)) {
			return new QuestionnaireResponse("問卷標題為空");
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
			return new QuestionnaireResponse("開始或結束時間錯誤");
		}
//前端---------------

//		問卷狀態: 開始時間(0=未開放, 1=開放中, 2=已結束)
		int status = 0;
		if (startDate == LocalDate.now()) {
			status = 1;
		}

//		題目-----------
//		防呆: 未輸入問題
		if (!StringUtils.hasText(surveyQuestion)) {
			return new QuestionnaireResponse("未輸入題目");
		}

//		防呆: 未輸入必填 (預設不必填0)
		if (!StringUtils.hasText(required)) {
			return new QuestionnaireResponse("請勾選必填與否");
		}

//		防呆: 未輸入單複選 (預設單選0)
		if (!StringUtils.hasText(multiple)) {
			return new QuestionnaireResponse("請選擇單選或多選");
		}

//		防呆: 未輸入選項
		if (!StringUtils.hasText(surveyOption)) {
			return new QuestionnaireResponse("未輸入選項");
		}

//		防呆: 必填與否、單複選及選項與題目數量相符
		String[] questionAry = surveyQuestion.split(",");
		String[] requiredAry = required.split(",");
		String[] multipleAry = multiple.split(",");
		String[] optionAry = surveyOption.split(",");
		if (requiredAry.length != questionAry.length || multipleAry.length != questionAry.length
				|| optionAry.length != questionAry.length) {
			return new QuestionnaireResponse("請檢查是否都有填入");
		}

//		存資料庫
		survey.get().setSurveyName(surveyName);
		survey.get().setSurveyCaption(surveyCaption);
		survey.get().setStartDate(startDate);
		survey.get().setEndDate(endDate);
		survey.get().setStatus(status);
		survey.get().setSurveyQuestion(surveyQuestion);
		survey.get().setRequired(required);
		survey.get().setMultiple(multiple);
		return new QuestionnaireResponse(questionnaireDao.save(survey.get()), "編輯問卷資訊成功");
	}

	
//	刪除問卷
	@Override
	public QuestionnaireResponse deleteSurvey(List<Integer> deleteSurveyList) {
		List<Questionnaire> allSurveyList = questionnaireDao.findAll();
		
//		將要刪除之問卷加入list
		List<Questionnaire> deleteList = new ArrayList<>();
		for(Questionnaire survey : allSurveyList) {
			for(Integer deleteSurvey : deleteSurveyList) {
				if(survey.getSerialNumber() == deleteSurvey) {
					deleteList.add(survey);
				}
			}
		}
//		防呆: 確認有無欲刪除問卷(比較刪除問卷list長度與req是否相同)
		if(deleteList.size() != deleteSurveyList.size()) {
			return new QuestionnaireResponse("無此問卷");
		}
		
//		防呆: 檢查此問卷狀態(未開放才能刪除)
		for(Questionnaire deleteSurvey : deleteList) {
			if(deleteSurvey.getStatus() != 0) {
				return new QuestionnaireResponse("問卷無法刪除");
			}
		}
		
		questionnaireDao.deleteAllById(deleteSurveyList);
		return new QuestionnaireResponse("刪除問卷成功");
	}

	
//	找所有問卷
	@Override
	public QuestionnaireResponse findAllSurvey() {
		List<Questionnaire> allSurvey = questionnaireDao.findAllByOrderBySerialNumberDesc();
		return new QuestionnaireResponse(allSurvey, "成功");
	}

//	修改問卷開放狀態
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
		return new QuestionnaireResponse(allSurvey, "成功");
	}




}
