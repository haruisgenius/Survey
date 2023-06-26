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
	private QuestionnaireDao qDao;

	@Autowired
	private QuestionnaireService qService;

	@Test
	public void createSurveyTest() {
//		成功(無問卷說明)
		QuestionnaireResponse res1 = qService.createSurvey("test", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23), "Q1", "必填", "單選", "1;2;3;4");
//		成功(有問卷說明)
		QuestionnaireResponse res2 = qService.createSurvey("test2", "成功(有問卷說明)", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23), "Q1", "必填", "多選", "1;2;3;4");
//		錯誤: 未輸入標題
		QuestionnaireResponse res3 = qService.createSurvey("", "test3", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23), "Q1", "必填", "單選", "1;2;3;4");
//		成功(開始時間預設為當日及結束時間為開始+7)
		QuestionnaireResponse res4 = qService.createSurvey("test4", "test4: 成功(開始時間預設為當日及結束時間為開始+7)", null, null, "Q1",
				"必填", "單選", "1;2;3;4");
//		錯誤: 開始日期早於今天
		QuestionnaireResponse res5 = qService.createSurvey("test5", "test5", LocalDate.of(2023, 6, 21), null, "Q1",
				"必填", "單選", "1;2;3;4");
//		錯誤: 結束日期早於開始日期
		QuestionnaireResponse res6 = qService.createSurvey("test6", "test6", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 20), "Q1", "必填", "單選", "1;2;3;4");
//		錯誤: 未輸入題目
		QuestionnaireResponse res7 = qService.createSurvey("test7", "test7", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "", "必填", "單選", "1;2;3;4");
//		錯誤: 未輸入必填與否，預設為不必填"預設")
		QuestionnaireResponse res8 = qService.createSurvey("test8", "test8", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "Q1", null, "多選", "1;2;3;4");
//		錯誤: 未輸入單複選，預設為單選"單選")
		QuestionnaireResponse res9 = qService.createSurvey("test9", "test9", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "Q1", "非必填", "", "1;2;3;4");
//		錯誤: 未輸入選項
		QuestionnaireResponse res10 = qService.createSurvey("test10", "test10", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "Q1", "必填", "單選", "");
//		成功(開始時間為未來)
		QuestionnaireResponse res11 = qService.createSurvey("test11", "test11: 成功(開始時間為未來)", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選,單選", "1;2;3;4,a;b;c;d");
//		錯誤: 必填數目與題目數目不符
		QuestionnaireResponse res12 = qService.createSurvey("test12", "test12", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填", "多選,單選", "1;2;3;4,aa;ss;dd");
//		錯誤: 多複選數目與題目數目不符
		QuestionnaireResponse res13 = qService.createSurvey("test13", "test13", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選", "1;2;3;4,a;b;c;d");
//		錯誤: 選項數目與題目數目不符
		QuestionnaireResponse res14 = qService.createSurvey("test14", "test14", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選", "1;2;3;4");
		System.out.println(res1.getMessage() + "\n" + res2.getMessage() + "\n" + res3.getMessage() + "\n"
				+ res4.getMessage() + "\n" + res5.getMessage() + "\n" + res6.getMessage() + "\n" + res7.getMessage()
				+ "\n" + res8.getMessage() + "\n" + res9.getMessage() + "\n" + res10.getMessage() + "\n"
				+ res11.getMessage() + "\n" + res12.getMessage() + "\n" + res13.getMessage() + "\n"
				+ res14.getMessage());
	}

	@Test
	public void updateSurveyTest() {
//		成功(修改標題)
		QuestionnaireResponse res1 = qService.updateSurvey(1, "updateTest", null, LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23), "Q1", "必填", "單選", "1;2;3;4");
//		錯誤: 無此問卷
		QuestionnaireResponse res3 = qService.updateSurvey(11, "", "test3", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 23), "Q1", "必填", "單選", "1;2;3;4");
//		錯誤: 開始日期早於今天
		QuestionnaireResponse res5 = qService.updateSurvey(5, "test5", "updateTest5", LocalDate.of(2023, 6, 21), null,
				"Q1", "必填", "單選", "1;2;3;4");
//		錯誤: 未輸入題目
		QuestionnaireResponse res7 = qService.updateSurvey(7, "test7", "updateTest7", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "", "必填", "單選", "1;2;3;4");
//		錯誤: 未輸入必填與否，預設為不必填"預設")
		QuestionnaireResponse res8 = qService.updateSurvey(7, "test8", "updateTest8", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "Q1", null, "多選", "1;2;3;4");
//		錯誤: 未輸入單複選，預設為單選"單選")
		QuestionnaireResponse res9 = qService.updateSurvey(9, "test9", "updateTest9", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "Q1", "非必填", "", "1;2;3;4");
//		錯誤: 未輸入選項
		QuestionnaireResponse res10 = qService.updateSurvey(2, "test10", "updateTest10", LocalDate.of(2023, 7, 23),
				LocalDate.of(2023, 7, 27), "Q1", "必填", "單選", "");
//		錯誤: 必填數目與題目數目不符
		QuestionnaireResponse res12 = qService.updateSurvey(1, "test12", "updateTest12", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填", "多選,單選", "1;2;3;4,aa;ss;dd");
//		錯誤: 多複選數目與題目數目不符
		QuestionnaireResponse res13 = qService.updateSurvey(2, "test13", "updateTest13", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選", "1;2;3;4,a;b;c;d");
//		錯誤: 選項數目與題目數目不符
		QuestionnaireResponse res14 = qService.updateSurvey(3, "test14", "updateTest14", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選", "1;2;3;4");
//		錯誤: 問卷已關閉
		QuestionnaireResponse res15 = qService.updateSurvey(10, "test14", "updateTest14", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選", "1;2;3;4");
//		錯誤: 問卷已開始
		QuestionnaireResponse res16 = qService.updateSurvey(4, "test14", "updateTest14", LocalDate.of(2023, 7, 27),
				LocalDate.of(2023, 7, 29), "Q1,Q2", "必填,非必填", "多選", "1;2;3;4");
		System.out.println(res1.getMessage() + "\n" + res3.getMessage() + "\n" + res5.getMessage() + "\n"
				+ res7.getMessage() + "\n" + res8.getMessage() + "\n" + res9.getMessage() + "\n" + res10.getMessage()
				+ "\n" + res12.getMessage() + "\n" + res13.getMessage() + "\n" + res14.getMessage() + "\n"
				+ res15.getMessage() + "\n" + res16.getMessage());
	}
	
	@Test
	public void deleteSurveyTest() {
		List<Integer> deleteList = new ArrayList<>();
//		deleteList.add(8);
		deleteList.add(9);
//		deleteList.add(10);
//		deleteList.add(11);
		QuestionnaireResponse res1 = qService.deleteSurvey(deleteList);
		System.out.println(deleteList + "\n" + res1.getMessage());
	}

	@Test
	public void findAllSurveyTest() {
		QuestionnaireResponse res1 = qService.findAllSurvey();
		for(Questionnaire survey : res1.getSurveyList()) {
			System.out.println(survey.getSerialNumber());
			System.out.println(survey.getSurveyName());
			System.out.println(survey.getStartDate());
			System.out.println(survey.getEndDate());
		}
	}
	
	@Test
	public void colseSurveyTest() {
		QuestionnaireResponse res1 = qService.closeSurvey();
		for(Questionnaire result : res1.getSurveyList()) {
			System.out.println(result.getSerialNumber());
			System.out.println(result.getStatus());
			System.out.println(res1.getMessage());
		}
	}
	
	

}
