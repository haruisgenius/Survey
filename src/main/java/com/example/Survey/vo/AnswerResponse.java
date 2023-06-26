package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.Answer;

public class AnswerResponse {

	private Answer answerEntity;
	
	private List<Answer> answerList;
	
	private String message;

	public AnswerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerResponse(Answer answerEntity, List<Answer> answerList, String message) {
		super();
		this.answerEntity = answerEntity;
		this.answerList = answerList;
		this.message = message;
	}

	public Answer getAnswerEntity() {
		return answerEntity;
	}

	public void setAnswerEntity(Answer answerEntity) {
		this.answerEntity = answerEntity;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
