package com.example.Survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Survey.service.ifs.QuestionService;
import com.example.Survey.service.ifs.QuestionnaireService;
import com.example.Survey.service.ifs.RespondentService;
import com.example.Survey.vo.QuestionRequest;
import com.example.Survey.vo.QuestionResponse;
import com.example.Survey.vo.QuestionnaireRequest;
import com.example.Survey.vo.QuestionnaireResponse;
import com.example.Survey.vo.RespondentRequest;
import com.example.Survey.vo.RespondentResponse;

@CrossOrigin
@RestController
public class QuestionnaireController {

	@Autowired
	private QuestionnaireService qnService;

	@Autowired
	private QuestionService qService;

	@Autowired
	private RespondentService rpService;

//	問卷 ---------------------------------------
	@GetMapping(value = "find_all_survey")
	public QuestionnaireResponse findAllSurvey() {
		return qnService.findAllSurvey();
	}

	@GetMapping(value = "change_survey_status")
	public QuestionnaireResponse changeSurveyStatus() {
		return qnService.changeSurveyStatus();
	}

	@PostMapping(value = "create_survey")
	public QuestionnaireResponse createSurvey(@RequestBody QuestionnaireRequest qnReq) {
		return qnService.createSurvey(qnReq.getSurveyName(), qnReq.getSurveyCaption(), qnReq.getStartDate(),
				qnReq.getEndDate());
	}

	@PostMapping(value = "update_survey")
	public QuestionnaireResponse updateSurvey(@RequestBody QuestionnaireRequest qnReq) {
		return qnService.updateSurvey(qnReq.getSerialNumber(), qnReq.getSurveyName(), qnReq.getSurveyCaption(),
				qnReq.getStartDate(), qnReq.getEndDate());
	}

	@PostMapping(value = "delete_survey")
	public QuestionnaireResponse deleteSurvey(@RequestBody QuestionnaireRequest qnReq) {
		return qnService.deleteSurvey(qnReq.getDeleteSurveyList());
	}
	
	@PostMapping(value = "find_survey")
	public QuestionnaireResponse findSurvey(@RequestBody QuestionnaireRequest qnReq) {
		return qnService.findSurvey(qnReq.getSerialNumber());
	}

//	題目 ---------------------------------
	@PostMapping(value = "create_question")
	public QuestionResponse createQuestion(@RequestBody QuestionRequest qReq) {
		return qService.createQuestion(qReq.getQuestionList());
	}

	@PostMapping(value = "update_question")
	public QuestionResponse updateQuestion(@RequestBody QuestionRequest qReq) {
		return qService.updateQuestion(qReq.getSurveyNumber(), qReq.getQuestionList());
	}

	@PostMapping(value = "find_survey_question")
	public QuestionResponse findSurveyQuestion(@RequestBody QuestionRequest qReq) {
		return qService.findSurveyQuestion(qReq.getSurveyNumber());
	}

//	作答 --------------------------------
	@PostMapping(value = "create_respondent")
	public RespondentResponse createRespondent(@RequestBody RespondentRequest rpReq) {
		return rpService.createRespondent(rpReq.getRespondentName(), rpReq.getRespondentAge(),
				rpReq.getRespondentPhone(), rpReq.getRespondentEmail(), rpReq.getSurveyNumber(), rpReq.getAnswer());
	}
	
	@PostMapping(value = "find_respondent")
	public RespondentResponse findRespondent(@RequestBody RespondentRequest rpReq) {
		return rpService.findRespondent(rpReq.getSurveyNumber());
	}

}
