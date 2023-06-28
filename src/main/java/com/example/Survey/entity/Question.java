package com.example.Survey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

//	�D�جy����
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_number")
	private Integer questionNumber;
	
//	�ݨ��s��
	@Column(name = "survey_number")
	private int surveyNumber;
	
//	���D
	@Column(name = "question")
	private String question;
	
//	����
	@Column(name = "needs")
	private Boolean needs;
	
//	��ƿ�
	@Column(name = "multiple")
	private Boolean multiple;
	
//	�ﶵ
	@Column(name = "q_option")
	private String qOption;

//	-------------------------------------------
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Integer questionNumber, int surveyNumber, String question, Boolean needs, Boolean multiple,
			String qOption) {
		super();
		this.questionNumber = questionNumber;
		this.surveyNumber = surveyNumber;
		this.question = question;
		this.needs = needs;
		this.multiple = multiple;
		this.qOption = qOption;
	}
	
//	�s�W�D��
	public Question(int surveyNumber, String question, Boolean needs, Boolean multiple, String qOption) {
		super();
		this.surveyNumber = surveyNumber;
		this.question = question;
		this.needs = needs;
		this.multiple = multiple;
		this.qOption = qOption;
	}
	
//	�s���D��
	public void updateQuestion(Integer questionNumber, String question, Boolean needs, Boolean multiple,
			String qOption) {
		this.questionNumber = questionNumber;
		this.question = question;
		this.needs = needs;
		this.multiple = multiple;
		this.qOption = qOption;
	}
	
//	-------------------------------------------

	public Integer getQuestionNumber() {
		return questionNumber;
	}

//	�y��������Set
//	public void setQuestionNumber(Integer questionNumber) {
//		this.questionNumber = questionNumber;
//	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Boolean getNeeds() {
		return needs;
	}

	public void setNeeds(Boolean needs) {
		this.needs = needs;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getqOption() {
		return qOption;
	}

	public void setqOption(String qOption) {
		this.qOption = qOption;
	}

	
	
	
	
}
