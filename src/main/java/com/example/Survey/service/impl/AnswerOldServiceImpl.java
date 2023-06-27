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

//	�@��(�s����)
	@Override
	public AnswerOldResponse createAnswer(Integer surveyNumber, String respondentName, int respondentAge,
			String respondentPhone, String respondentEmail, String answer) {
//		���b: ����ݨ����Xor�L���ݨ�
		if (surveyNumber == null || surveyNumber == 0) {
			return new AnswerOldResponse("�п�J�ݨ��N�X");
		}
		Optional<QuestionnaireOld> survey = questionnaireDao.findById(surveyNumber);
		if (!survey.isPresent()) {
			return new AnswerOldResponse("�L���ݨ�");
		}
//		���b: ����W�r
		if (!StringUtils.hasText(respondentName)) {
			return new AnswerOldResponse("�ж�W�r");
		}
//		���b: ����~��
		if (respondentAge <= 0) {
			return new AnswerOldResponse("�п�J���Ħ~��");
		}
//		���b: ����q�ܡB�T�{�q�ܮ榡
		String phonePattern = "\\d{4}(-\\d{3}){2}";
		String phonePattern2 = "\\d{10}";
		String phonePattern3 = "\\d{9}";
		if (!StringUtils.hasText(respondentPhone)) {
			return new AnswerOldResponse("�п�J�q�ܸ��X");
		} else if (!respondentPhone.matches(phonePattern) || !respondentPhone.matches(phonePattern2)
				|| !respondentPhone.matches(phonePattern3)) {
			return new AnswerOldResponse("�п�J���Ĺq�ܸ��X");
		}

//		���b: ����email�B�T�{email�榡
		String emailPattern = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})";
		if (!StringUtils.hasText(respondentEmail)) {
			return new AnswerOldResponse("�п�Jemail");
		} else if (!respondentEmail.matches(emailPattern)) {
			return new AnswerOldResponse("�п�J����email");
		}
		
//		�ˬd�D�إ���P���ץ���B��ƿ�
		

		return null;
	}
	
	
//	----------------------- ��k -------------------------
	private AnswerOldResponse checkAnswer(Integer surveyNumber, String answer) {
		Optional<QuestionnaireOld> surveyOp = questionnaireDao.findById(surveyNumber);
		String[] questionAry = surveyOp.get().getSurveyQuestion().split(",");
		String[] requiredAry = surveyOp.get().getRequired().split(",");
		String[] multipleAry = surveyOp.get().getMultiple().split(",");
		String[] optionAry = surveyOp.get().getSurveyOption().split(",");
		
//		�jmap
		Map<String, List<Map<String, String>>> surveyQuestion = new HashMap<>();
//		�jmap��value list
		List<Map<String, String>> surveyQInfoList = new ArrayList<>();
		
		for(int i = 0; i <= questionAry.length; i++) {
			String questionkey = "Q" + (i+1);
//			list��map(�ƭ�)
//			����info
			Map<String, String> requiredMap = new HashMap<>();
			String requiredKey = "required";
			String requiredValue = requiredAry[i];
			requiredMap.put(requiredKey, requiredValue);
			// �s�ilist
			surveyQInfoList.add(requiredMap);
			
//			��ƿ�info
			Map<String, String> multipleMap = new HashMap<>();
			String multipleKey = "multiple";
			String multipleValue = multipleAry[i];
			multipleMap.put(multipleKey, multipleValue);
			// �s�ilist
			surveyQInfoList.add(multipleMap);
			
//			�ﶵinfo
			Map<String, String> optionMap = new HashMap<>();
			String optionKey = "option";
			String optionValue = optionAry[i];
			optionMap.put(optionKey, optionValue);
			// �s�ilist
			surveyQInfoList.add(optionMap);
			
			surveyQuestion.put(questionkey, surveyQInfoList);
		}
		
		
		
		
		
		
		
		

		
		return null;
	}

}
