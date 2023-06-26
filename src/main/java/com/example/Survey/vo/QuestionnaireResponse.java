package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.Questionnaire;

public class QuestionnaireResponse {
	
	private Questionnaire questionnaire;
	
	private List<Questionnaire> surveyList;

	private String message;

	public QuestionnaireResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionnaireResponse(Questionnaire questionnaire, String message) {
		super();
		this.questionnaire = questionnaire;
		this.message = message;
	}


	public QuestionnaireResponse(Questionnaire questionnaire) {
		super();
		this.questionnaire = questionnaire;
	}

	public QuestionnaireResponse(String message) {
		super();
		this.message = message;
	}

	public QuestionnaireResponse(List<Questionnaire> surveyList, String message) {
		super();
		this.surveyList = surveyList;
		this.message = message;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Questionnaire> getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(List<Questionnaire> surveyList) {
		this.surveyList = surveyList;
	}
	
	
}
