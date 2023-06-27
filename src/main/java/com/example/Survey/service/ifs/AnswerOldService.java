package com.example.Survey.service.ifs;

import com.example.Survey.vo.AnswerOldResponse;

public interface AnswerOldService {

//	§@µª
	public AnswerOldResponse createAnswer(Integer surveyNumber, String respondentName, int respondentAge,
			String respondentPhone, String respondentEmail, String answer);

}
