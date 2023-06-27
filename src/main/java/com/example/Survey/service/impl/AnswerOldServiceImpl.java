package com.example.Survey.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.entity.QuestionnaireOld;
import com.example.Survey.respository.AnswerOldDao;
import com.example.Survey.respository.QuestionnaireOldDao;
import com.example.Survey.service.ifs.AnswerOldService;
import com.example.Survey.service.ifs.QuestionnaireOldService;
import com.example.Survey.vo.AnswerOldResponse;

@Service
public class AnswerOldServiceImpl implements AnswerOldService {

	@Autowired
	private QuestionnaireOldDao questionnaireDao;

	@Autowired
	private QuestionnaireOldService qService;

	@Autowired
	private AnswerOldDao answerDao;

//	作答(存答案)
	@Override
	public AnswerOldResponse createAnswer(Integer surveyNumber, String respondentName, int respondentAge,
			String respondentPhone, String respondentEmail, String answer) {
//		防呆: 未填問卷號碼or無此問卷
		if (surveyNumber == null || surveyNumber == 0) {
			return new AnswerOldResponse("請輸入問卷代碼");
		}
		Optional<QuestionnaireOld> survey = questionnaireDao.findById(surveyNumber);
		if (!survey.isPresent()) {
			return new AnswerOldResponse("無此問卷");
		}
//		防呆: 未填名字
		if (!StringUtils.hasText(respondentName)) {
			return new AnswerOldResponse("請填名字");
		}
//		防呆: 未填年齡
		if (respondentAge <= 0) {
			return new AnswerOldResponse("請輸入有效年齡");
		}
//		防呆: 未填電話、確認電話格式
		String phonePattern = "\\d{4}(-\\d{3}){2}";
		String phonePattern2 = "\\d{10}";
		String phonePattern3 = "\\d{9}";
		if (!StringUtils.hasText(respondentPhone)) {
			return new AnswerOldResponse("請輸入電話號碼");
		} else if (!respondentPhone.matches(phonePattern) || !respondentPhone.matches(phonePattern2)
				|| !respondentPhone.matches(phonePattern3)) {
			return new AnswerOldResponse("請輸入有效電話號碼");
		}

//		防呆: 未填email、確認email格式
		String emailPattern = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})";
		if (!StringUtils.hasText(respondentEmail)) {
			return new AnswerOldResponse("請輸入email");
		} else if (!respondentEmail.matches(emailPattern)) {
			return new AnswerOldResponse("請輸入有效email");
		}
		
//		檢查題目必填與答案必填、單複選
		

		return null;
	}
	
	
//	----------------------- 方法 -------------------------
	private AnswerOldResponse checkAnswer(Integer surveyNumber, String answer) {
		Optional<QuestionnaireOld> surveyOp = questionnaireDao.findById(surveyNumber);
		String[] questionAry = surveyOp.get().getSurveyQuestion().split(",");
		String[] requiredAry = surveyOp.get().getRequired().split(",");
		String[] multipleAry = surveyOp.get().getMultiple().split(",");
		String[] optionAry = surveyOp.get().getSurveyOption().split(",");
		
//		大map
		Map<String, List<Map<String, String>>> surveyQuestion = new HashMap<>();
//		大map的value list
		List<Map<String, String>> surveyQInfoList = new ArrayList<>();
		
		for(int i = 0; i <= questionAry.length; i++) {
			String questionkey = "Q" + (i+1);
//			list的map(數個)
//			必選info
			Map<String, String> requiredMap = new HashMap<>();
			String requiredKey = "required";
			String requiredValue = requiredAry[i];
			requiredMap.put(requiredKey, requiredValue);
			// 存進list
			surveyQInfoList.add(requiredMap);
			
//			單複選info
			Map<String, String> multipleMap = new HashMap<>();
			String multipleKey = "multiple";
			String multipleValue = multipleAry[i];
			multipleMap.put(multipleKey, multipleValue);
			// 存進list
			surveyQInfoList.add(multipleMap);
			
//			選項info
			Map<String, String> optionMap = new HashMap<>();
			String optionKey = "option";
			String optionValue = optionAry[i];
			optionMap.put(optionKey, optionValue);
			// 存進list
			surveyQInfoList.add(optionMap);
			
			surveyQuestion.put(questionkey, surveyQInfoList);
		}
		
		
		
		
		
		
		
		

		
		return null;
	}

}
