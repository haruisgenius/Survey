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

//	流水號
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;

//	問卷名稱
	@Column(name = "survey_name")
	private String surveyName;

//	問卷說明文 (可空)
	@Column(name = "survey_caption")
	private String surveyCaption;

//	問卷內容-------------

//	題目
	@Column(name = "survey_question")
	private String surveyQuestion;

//	必填與否
	@Column(name = "required")
	private String required;

//	單選與否
	@Column(name = "multiple")
	private String multiple;

//	選項內容
	@Column(name = "survey_option")
	private String surveyOption;

//	問卷狀態 (0=未開放, 1=開放中, 2=已結束)
	@Column(name = "status")
	private int status;
	
//	開始時間 (預設為當日)
	@Column(name = "start_date")
	private LocalDate startDate;

//	結束時間 (預設為當日+7)
	@Column(name = "end_date")
	private LocalDate endDate;

//	------- 建構方法 -------

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

//	新增問卷資訊(標題、說明、開始時間、結束時間、問卷狀態)
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
	
//	新增問卷題目(題目、必填、單複選、選項)
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

//	流水號不用set

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
