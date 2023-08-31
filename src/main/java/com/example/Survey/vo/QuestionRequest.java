package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.Question;

public class QuestionRequest {

	private Integer questionNumber;
	
	private int surveyNumber;
	
	private String question;
	
	private Boolean needs;
	
	private Boolean multiple;
	
	private String qOption;
	
	private List<Question> questionList;
	
//	private List<Integer> deleteQuestionList;
	
//	-------------------------------------------

	public QuestionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionRequest(Integer questionNumber, int surveyNumber, String question, Boolean needs,
			Boolean multiple, String qOption) {
		super();
		this.questionNumber = questionNumber;
		this.surveyNumber = surveyNumber;
		this.question = question;
		this.needs = needs;
		this.multiple = multiple;
		this.qOption = qOption;
	}
	
//	-------------------------------------------

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Boolean getNeeds() {
		return needs;
	}

	public void setNeeds(Boolean needs) {
		this.needs = needs;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getqOption() {
		return qOption;
	}

	public void setqOption(String qOption) {
		this.qOption = qOption;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	
	
	
	
}
