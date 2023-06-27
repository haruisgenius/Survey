package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.Questionnaire;

public class QuestionnaireResponse {

	private Questionnaire questionnaire;
	
	private List<Questionnaire> questionnaireList;
	
	private String message;
	
//	---------------------------

	public QuestionnaireResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionnaireResponse(String message) {
		super();
		this.message = message;
	}

	public QuestionnaireResponse(Questionnaire questionnaire, List<Questionnaire> questionnaireList, String message) {
		super();
		this.questionnaire = questionnaire;
		this.questionnaireList = questionnaireList;
		this.message = message;
	}

	public QuestionnaireResponse(Questionnaire questionnaire, String message) {
		super();
		this.questionnaire = questionnaire;
		this.message = message;
	}

	public QuestionnaireResponse(List<Questionnaire> questionnaireList, String message) {
		super();
		this.questionnaireList = questionnaireList;
		this.message = message;
	}
	
//	------------------------------

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<Questionnaire> getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
