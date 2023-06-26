package com.example.Survey.service.ifs;

import com.example.Survey.vo.AnswerResponse;

public interface AnswerService {

//	§@µª
	public AnswerResponse createAnswer(Integer surveyNumber, String respondentName, int respondentAge,
			String respondentPhone, String respondentEmail, String answer);

}
