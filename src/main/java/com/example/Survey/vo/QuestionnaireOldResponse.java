package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.QuestionnaireOld;

public class QuestionnaireOldResponse {
	
	private QuestionnaireOld questionnaire;
	
	private List<QuestionnaireOld> surveyList;

	private String message;

	public QuestionnaireOldResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionnaireOldResponse(QuestionnaireOld questionnaire, String message) {
		super();
		this.questionnaire = questionnaire;
		this.message = message;
	}


	public QuestionnaireOldResponse(QuestionnaireOld questionnaire) {
		super();
		this.questionnaire = questionnaire;
	}

	public QuestionnaireOldResponse(String message) {
		super();
		this.message = message;
	}

	public QuestionnaireOldResponse(List<QuestionnaireOld> surveyList, String message) {
		super();
		this.surveyList = surveyList;
		this.message = message;
	}

	public QuestionnaireOld getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(QuestionnaireOld questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<QuestionnaireOld> getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(List<QuestionnaireOld> surveyList) {
		this.surveyList = surveyList;
	}
	
	
}
