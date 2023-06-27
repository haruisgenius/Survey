package com.example.Survey.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire_old")
public class QuestionnaireOld {

//	�y����
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;

//	�ݨ��W��
	@Column(name = "survey_name")
	private String surveyName;

//	�ݨ������� (�i��)
	@Column(name = "survey_caption")
	private String surveyCaption;

//	�ݨ����e-------------

//	�D��
	@Column(name = "survey_question")
	private String surveyQuestion;

//	����P�_
	@Column(name = "required")
	private String required;

//	���P�_
	@Column(name = "multiple")
	private String multiple;

//	�ﶵ���e
	@Column(name = "survey_option")
	private String surveyOption;

//	�ݨ����A (0=���}��, 1=�}��, 2=�w����)
	@Column(name = "status")
	private int status;
	
//	�}�l�ɶ� (�w�]�����)
	@Column(name = "start_date")
	private LocalDate startDate;

//	�����ɶ� (�w�]�����+7)
	@Column(name = "end_date")
	private LocalDate endDate;

//	------- �غc��k -------

	public QuestionnaireOld() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionnaireOld(Integer serialNumber, String surveyName, String surveyCaption, LocalDate startDate,
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

//	�s�W�ݨ���T(���D�B�����B�}�l�ɶ��B�����ɶ��B�ݨ����A)
	public QuestionnaireOld(String surveyName, String surveyCaption, LocalDate startDate, LocalDate endDate, int status, String surveyQuestion, String required, String multiple, String surveyOption) {
		super();
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
	
//	�s�W�ݨ��D��(�D�ءB����B��ƿ�B�ﶵ)
	public QuestionnaireOld(String surveyQuestion, String required, String multiple, String surveyOption) {
		super();
		this.surveyQuestion = surveyQuestion;
		this.required = required;
		this.multiple = multiple;
		this.surveyOption = surveyOption;
	}
	
	

//------- get set -------

	public Integer getSerialNumber() {
		return serialNumber;
	}

//	�y��������set

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

}
