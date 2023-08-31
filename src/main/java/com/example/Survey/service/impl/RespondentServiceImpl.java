package com.example.Survey.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Survey.constants.RtnCode;
import com.example.Survey.entity.Questionnaire;
import com.example.Survey.entity.Respondent;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.respository.RespondentDao;
import com.example.Survey.service.ifs.RespondentService;
import com.example.Survey.vo.QuestionResponse;
import com.example.Survey.vo.RespondentResponse;

@Service
public class RespondentServiceImpl implements RespondentService {

	@Autowired
	private RespondentDao respondentDao;
	
	@Autowired
	private QuestionnaireDao questionnaireDao;

//	新增回答者
	@Override
	public RespondentResponse createRespondent(String respondentName, int respondentAge, String respondentPhone,
			String respondentEmail, int surveyNumber, String answer) {
		
		if(!StringUtils.hasText(respondentName)) {
			return new RespondentResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		if(!StringUtils.hasText(respondentPhone)) {
			return new RespondentResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		if(respondentAge <= 0) {
			return new RespondentResponse(RtnCode.INCORRECT.getMessage());
		}
		
		if(!StringUtils.hasText(respondentEmail)) {
			return new RespondentResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
//		檢查問卷存在
		Optional<Questionnaire> survey = questionnaireDao.findById(surveyNumber);
		if(!survey.isPresent()) {
			return new RespondentResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		return new RespondentResponse(
				respondentDao.save(
						new Respondent(respondentName, respondentAge, respondentPhone, respondentEmail, surveyNumber, answer)),
				RtnCode.SUCCESSFUL.getMessage());
	}

//	找作答者
	@Override
	public RespondentResponse findRespondent(int surveyNumber) {
		return new RespondentResponse(respondentDao.findBySurveyNumberOrderByAnswerTimeDesc(surveyNumber), RtnCode.SUCCESSFUL.getMessage());
	}

}
