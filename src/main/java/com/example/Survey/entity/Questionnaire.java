package com.example.Survey.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {

//	�y����
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;
	
//	�ݨ��W��
	@Column(name = "survey_name")
	private String surveyName;
	
//	�ݨ�����(�i��)
	@Column(name = "survey_caption")
	private String surveyCaption;
	
//	�ݨ����A (0=���}��, 1=�}��, 2=�w����)
	@Column(name = "survey_status")
	private int surveyStatus;
	
//	�}�l��
	@Column(name = "start_date")
	private LocalDate startDate;
	
//	������
	@Column(name = "end_date")
	private LocalDate endDate;
	
//	-----------------------------

	public Questionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Questionnaire(Integer serialNumber, String surveyName, String surveyCaption, int surveyStatus,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.serialNumber = serialNumber;
		this.surveyName = surveyName;
		this.surveyCaption = surveyCaption;
		this.surveyStatus = surveyStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
//	�s�W�ݨ���
	public Questionnaire(String surveyName, String surveyCaption, int surveyStatus, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.surveyName = surveyName;
		this.surveyCaption = surveyCaption;
		this.surveyStatus = surveyStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
//	------------------------------

	public Integer getSerialNumber() {
		return serialNumber;
	}

//	�y��������set
//	public void setSerialNumber(Integer serialNumber) {
//		this.serialNumber = serialNumber;
//	}

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

	public int getSurveyStatus() {
		return surveyStatus;
	}

	public void setSurveyStatus(int surveyStatus) {
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
	
	
	
}
