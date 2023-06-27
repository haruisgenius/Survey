package com.example.Survey.vo;

import java.util.List;

import com.example.Survey.entity.AnswerOld;

public class AnswerOldResponse {

	private AnswerOld answerEntity;
	
	private List<AnswerOld> answerList;
	
	private String message;
	
//	----------------------------------------

	public AnswerOldResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerOldResponse(AnswerOld answerEntity, List<AnswerOld> answerList, String message) {
		super();
		this.answerEntity = answerEntity;
		this.answerList = answerList;
		this.message = message;
	}

	public AnswerOldResponse(String message) {
		super();
		this.message = message;
	}
	
//	----------------------------------------

	public AnswerOld getAnswerEntity() {
		return answerEntity;
	}

	public void setAnswerEntity(AnswerOld answerEntity) {
		this.answerEntity = answerEntity;
	}

	public List<AnswerOld> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerOld> answerList) {
		this.answerList = answerList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
