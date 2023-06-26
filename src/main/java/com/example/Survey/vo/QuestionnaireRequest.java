package com.example.Survey.vo;

import java.time.LocalDate;
import java.util.List;

public class QuestionnaireRequest {

	private Integer serialNumber;
	
	private String surveyName;
	
	private String surveyCaption;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private int status;
	
	private String surveyQuestion;
	
	private String required;
	
	private String multiple;
	
	private String surveyOption;
	
//	§R°£°Ý¨÷List
	private List<Integer> surveyList;
	
//	------------------------

	public QuestionnaireRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionnaireRequest(Integer serialNumber, String surveyName, String surveyCaption, LocalDate startDate,
			LocalDate endDate, int status, String surveyQuestion, String required, String multiple,
			String surveyOption) {
		super();
		this.serialNumber = serialNumber;
		this.surveyName = surveyName;
		this.surveyCaption = surveyCaption;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.surveyQuestion = surveyQuestion;
		this.required = required;
		this.multiple = multiple;
		this.surveyOption = surveyOption;
	}
	
	

//	-------------------------
	
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getSurveyCaption() {
		return surveyCaption;
	}

	public void setSurveyCaption(String surveyCaption) {
		this.surveyCaption = surveyCaption;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(String surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getSurveyOption() {
		return surveyOption;
	}

	public void setSurveyOption(String surveyOption) {
		this.surveyOption = surveyOption;
	}

	public List<Integer> getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(List<Integer> surveyList) {
		this.surveyList = surveyList;
	}
	
	
	
}
