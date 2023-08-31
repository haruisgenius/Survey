package com.example.Survey.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "respondent")
public class Respondent {

//	作答紙編號
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "respondent_number")
	private Integer respondentNumber;

//	回答者姓名
	@Column(name = "respondent_name")
	private String respondentName;

//	回答者年齡
	@Column(name = "respondent_age")
	private int respondentAge;

//	回答者電話
	@Column(name = "respondent_phone")
	private String respondentPhone;

//	回答者電子郵件
	@Column(name = "respondent_email")
	private String respondentEmail;

//	問卷編號
	@Column(name = "survey_number")
	private int surveyNumber;
	
//	答案
	@Column(name = "answer")
	private String answer;

//	交卷時間
	@Column(name = "answer_time")
	private LocalDateTime answerTime = LocalDateTime.now();

//	--------------------------------

	public Respondent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Respondent(Integer respondentNumber, String respondentName, int respondentAge, String respondentPhone,
		String respondentEmail, int surveyNumber, String answer, LocalDateTime answerTime) {
	super();
	this.respondentNumber = respondentNumber;
	this.respondentName = respondentName;
	this.respondentAge = respondentAge;
	this.respondentPhone = respondentPhone;
	this.respondentEmail = respondentEmail;
	this.surveyNumber = surveyNumber;
	this.answer = answer;
	this.answerTime = answerTime;
}



	public Respondent(String respondentName, int respondentAge, String respondentPhone, String respondentEmail,
			int surveyNumber, String answer) {
		super();
		this.respondentName = respondentName;
		this.respondentAge = respondentAge;
		this.respondentPhone = respondentPhone;
		this.respondentEmail = respondentEmail;
		this.surveyNumber = surveyNumber;
		this.answer = answer;
	}
	
//	--------------------------------

	public Integer getRespondentNumber() {
		return respondentNumber;
	}

//	流水號不用set
//	public void setRespondentNumber(Integer respondentNumber) {
//		this.respondentNumber = respondentNumber;
//	}

	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}

	public int getRespondentAge() {
		return respondentAge;
	}

	public void setRespondentAge(int respondentAge) {
		this.respondentAge = respondentAge;
	}

	public String getRespondentPhone() {
		return respondentPhone;
	}

	public void setRespondentPhone(String respondentPhone) {
		this.respondentPhone = respondentPhone;
	}

	public String getRespondentEmail() {
		return respondentEmail;
	}

	public void setRespondentEmail(String respondentEmail) {
		this.respondentEmail = respondentEmail;
	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public LocalDateTime getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(LocalDateTime answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
