package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.Respondent;

public class RespondentResponse {

	private Respondent respondent;

	private List<Respondent> respondentList;

	private String message;

//	--------------------------------------------

	public RespondentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespondentResponse(Respondent respondent, List<Respondent> respondentList, String message) {
		super();
		this.respondent = respondent;
		this.respondentList = respondentList;
		this.message = message;
	}

	public RespondentResponse(List<Respondent> respondentList, String message) {
		super();
		this.respondentList = respondentList;
		this.message = message;
	}

	public RespondentResponse(Respondent respondent, String message) {
		super();
		this.respondent = respondent;
		this.message = message;
	}

	public RespondentResponse(String message) {
		super();
		this.message = message;
	}

//	-------------------------------------------

	public Respondent getRespondent() {
		return respondent;
	}

	public void setRespondent(Respondent respondent) {
		this.respondent = respondent;
	}

	public List<Respondent> getRespondentList() {
		return respondentList;
	}

	public void setRespondentList(List<Respondent> respondentList) {
		this.respondentList = respondentList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
