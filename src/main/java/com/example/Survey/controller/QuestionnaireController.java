package com.example.Survey.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.Survey.service.ifs.QuestionnaireService;

@CrossOrigin
@RestController
public class QuestionnaireController {

	private QuestionnaireService questionnaireService;
	
}
