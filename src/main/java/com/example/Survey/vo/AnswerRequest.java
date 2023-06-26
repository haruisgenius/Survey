package com.example.Survey.vo;

import java.time.LocalDateTime;

public class AnswerRequest {

	private Integer answerNumber;
	
	private String respondentName;
	
	private int respondentAge;
	
	private String respondentPhone;
	
	private String respondentEmail;
	
	private Integer surveyNumber;
	
	private String answer;
	
	private LocalDateTime answerTime;
	
//	----------------------------

	public AnswerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerRequest(Integer answerNumber, String respondentName, int respondentAge, String respondentPhone,
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
	
//	-----------------------

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
