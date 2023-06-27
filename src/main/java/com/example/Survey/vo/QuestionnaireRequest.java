package com.example.Survey.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.Survey.entity.Questionnaire;



public class QuestionnaireRequest {

	private Integer serialNumber;

	private String surveyName;

	private String surveyCaption;

	private String surveyStatus;

	private LocalDate startDate;

	private LocalDate endDate;
	
//	§R°£°Ý¨÷¥Î
	List<Integer> deleteSurveyList;
	

//	-------------------------------

	public QuestionnaireRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionnaireRequest(Integer serialNumber, String surveyName, String surveyCaption, String surveyStatus,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.serialNumber = serialNumber;
		this.surveyName = surveyName;
		this.surveyCaption = surveyCaption;
		this.surveyStatus = surveyStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
//	-----------------------------------

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

	public String getSurveyStatus() {
		return surveyStatus;
	}

	public void setSurveyStatus(String surveyStatus) {
		this.surveyStatus = surveyStatus;
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

	public List<Integer> getDeleteSurveyList() {
		return deleteSurveyList;
	}

	public void setDeleteSurveyList(List<Integer> deleteSurveyList) {
		this.deleteSurveyList = deleteSurveyList;
	}

}
