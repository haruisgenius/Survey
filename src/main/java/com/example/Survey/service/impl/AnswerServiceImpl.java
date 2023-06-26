package com.example.Survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Survey.respository.AnswerDao;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.AnswerService;
import com.example.Survey.vo.AnswerResponse;

@Service
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	private QuestionnaireDao questionnaireDao;

	@Autowired
	private AnswerDao answerDao;

//	§@µª
	@Override
	public AnswerResponse createAnswer(Integer surveyNumber, String respondentName, int respondentAge,
			String respondentPhone, String respondentEmail, String answer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
