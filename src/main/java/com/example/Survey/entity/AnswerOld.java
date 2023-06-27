package com.example.Survey.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer_old")
public class AnswerOld {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_number")
	private Integer answerNumber;
	
	@Column(name = "respondent_name")
	private String respondentName;
	
	@Column(name = "respondent_age")
	private int respondentAge;
	
	@Column(name = "respondent_phone")
	private String respondentPhone;
	
	@Column(name = "respondent_email")
	private String respondentEmail;
	
	@Column(name = "survey_number")
	private Integer surveyNumber;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "answer_time")
	private LocalDateTime answerTime = LocalDateTime.now();
	
//	--------«Øºc¤èªk-----------

	public AnswerOld() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerOld(Integer answerNumber, String respondentName, int respondentAge, String respondentPhone,
			String respondentEmail, Integer surveyNumber, String answer, LocalDateTime answerTime) {
		super();
		this.answerNumber = answerNumber;
		this.respondentName = respondentName;
		this.respondentAge = respondentAge;
		this.respondentPhone = respondentPhone;
		this.respondentEmail = respondentEmail;
		this.surveyNumber = surveyNumber;
		this.answer = answer;
		this.answerTime = answerTime;
	}
	
//	------- get set -----------

	public Integer getAnswerNumber() {
		return answerNumber;
	}

	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}

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

	public Integer getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(Integer surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDateTime getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(LocalDateTime answerTime) {
		this.answerTime = answerTime;
	}
	
	

}
