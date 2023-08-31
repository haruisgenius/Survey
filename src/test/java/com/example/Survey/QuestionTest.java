package com.example.Survey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Survey.entity.Question;
import com.example.Survey.entity.Questionnaire;
import com.example.Survey.respository.QuestionDao;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.QuestionService;
import com.example.Survey.vo.QuestionResponse;
import com.example.Survey.vo.QuestionnaireResponse;

@SpringBootTest(classes = SurveyApplication.class)
public class QuestionTest {

	@Autowired
	private QuestionDao qDao;

	@Autowired
	private QuestionnaireDao qnDao;

	@Autowired
	private QuestionService qService;

	@Test
	public void createQuestionTest() {
		List<Question> saveQ1 = new ArrayList<>(Arrays.asList(new Question(1, "Q1", false, true, "op1,op2,op3"),
				new Question(1, "Q2", null, null, "op2-1,op2-2,op2-3")));
		System.out.println(qService.createQuestion(saveQ1).getMessage());
		List<Question> saveQ2 = new ArrayList<>(Arrays.asList(new Question(2, "", false, true, "op1,op2,op3")));
		System.out.println(qService.createQuestion(saveQ2).getMessage());
		List<Question> saveQ3 = new ArrayList<>(Arrays.asList(new Question(3, "Q2", null, null, "")));
		System.out.println(qService.createQuestion(saveQ3).getMessage());
		List<Question> saveQ4 = new ArrayList<>(Arrays.asList(new Question(0, "Q2", null, null, "op1,op2")));
		System.out.println(qService.createQuestion(saveQ4).getMessage());
		List<Question> saveQ5 = new ArrayList<>(Arrays.asList(new Question(99, "Q2", null, null, "op1,op2")));
		System.out.println(qService.createQuestion(saveQ5).getMessage());
	}
	
	@Test
	public void updateQuestionTest() {
		List<Question> updateS1 = new ArrayList<>(Arrays.asList(new Question(1, 1, "upT1", false, true, "upT1op1,upT1op2,upT1op3"),
				new Question(2, 1, "upDupDQ2", true, true, "")));
		System.out.println("updateS1:" + qService.updateQuestion(1, updateS1).getMessage());
		
		List<Question> updateS2 = new ArrayList<>(Arrays.asList(new Question(3, 2, "upT2", false, true, "upT2,upT2,upT2"),
				new Question(4, 1, "updateTest2", true, true, "upT2wooo,upT2kekeke")));
		System.out.println("updateS2:" + qService.updateQuestion(2, updateS2).getMessage());
		
		List<Question> updateS3 = new ArrayList<>(Arrays.asList(new Question(0, 2, "upT3", false, true, "upT3,upT3"),
				new Question(0, 2, "updateTest3", true, true, "upT3wooo,upT3kekeke")));
		System.out.println("updateS3:" + qService.updateQuestion(8, updateS3).getMessage());
	}
	
	@Test
	public void test() {
		List<Integer> testInt = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		List<Question> surveyOptList = qDao.findBySurveyNumberAndQuestionNumberIn(99, testInt);
		System.out.println(surveyOptList.size());
	}
	
	@Test
	public void findQuestionTest() {
		QuestionResponse res1 = qService.findSurveyQuestion(1);
		for(Question res : res1.getQuestionList()) {
			System.out.println(res.getQuestion());
			String[] optionStr = res.getqOption().toString().split(",");
			for(String option : optionStr) {
				System.out.println(option);
				
			}
			
		}
	}

}
