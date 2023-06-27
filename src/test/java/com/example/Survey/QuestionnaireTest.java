package com.example.Survey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Survey.entity.Questionnaire;
import com.example.Survey.respository.QuestionnaireDao;
import com.example.Survey.service.ifs.QuestionnaireService;
import com.example.Survey.vo.QuestionnaireResponse;

@SpringBootTest(classes = SurveyApplication.class)
public class QuestionnaireTest {
	
	@Autowired
	private QuestionnaireDao questionnaireDao;
	
	@Autowired
	private QuestionnaireService questionnaireService;

	@Test
	public void createSurveyTest() {
		QuestionnaireResponse res1 = questionnaireService.createSurvey("test1", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23));
		System.out.println(res1.getMessage());
		QuestionnaireResponse res2 = questionnaireService.createSurvey("", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23));
		System.out.println(res2.getMessage());
		QuestionnaireResponse res3 = questionnaireService.createSurvey("test3", null, null, null);
		System.out.println(res3.getMessage());
		QuestionnaireResponse res4 = questionnaireService.createSurvey("test4", null, LocalDate.of(2023, 6, 21), null);
		System.out.println(res4.getMessage());
		QuestionnaireResponse res5 = questionnaireService.createSurvey("test5", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 20));
		System.out.println(res5.getMessage());
	}
	
	@Test
	public void updateSurveyTest() {
		QuestionnaireResponse res1 = questionnaireService.updateSurvey(99, "test1", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23));
		System.out.println(res1.getMessage());
		QuestionnaireResponse res2 = questionnaireService.updateSurvey(2, "updateTest2", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23));
		System.out.println(res2.getMessage());
		QuestionnaireResponse res3 = questionnaireService.updateSurvey(1, "updateTest1", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23));
		System.out.println(res3.getMessage());
	}

	@Test
	public void changeSurveyStatusTest() {
		QuestionnaireResponse res1 = questionnaireService.changeSurveyStatus();
		System.out.println(res1.getMessage());
	}
	
	@Test
	public void deleteSurveyTest() {
		List<Integer> deleteList = new ArrayList<>();
//		deleteList.add(4);
		deleteList.add(5);
//		deleteList.add(6);
		QuestionnaireResponse res1 = questionnaireService.deleteSurvey(deleteList);
		System.out.println(res1.getMessage());
	}
	
	@Test
	public void findAllSurveyTest() {
		QuestionnaireResponse res1 = questionnaireService.findAllSurvey();
		for(Questionnaire res : res1.getQuestionnaireList()) {
			System.out.println(res.getSerialNumber());
			System.out.println(res.getSurveyName());
			System.out.println(res.getSurveyCaption());
			System.out.println(res.getStartDate());
			System.out.println(res.getEndDate());
			
		}
	}

	
}
