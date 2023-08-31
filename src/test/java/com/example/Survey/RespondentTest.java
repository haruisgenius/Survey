package com.example.Survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Survey.respository.QuestionDao;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.respository.RespondentDao;
import com.example.Survey.service.ifs.RespondentService;

@SpringBootTest(classes = SurveyApplication.class)
public class RespondentTest {

	@Autowired
	private QuestionDao qDao;

	@Autowired
	private QuestionnaireDao qnDao;

	@Autowired
	private RespondentService resService;
	
	@Autowired
	private RespondentDao resDao;
	
	@Test
	public void createRespondent() {
		
	}
	
}
