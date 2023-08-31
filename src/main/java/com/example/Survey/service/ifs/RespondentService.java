package com.example.Survey.service.ifs;

import com.example.Survey.vo.RespondentResponse;

public interface RespondentService {

//	新增回答
	public RespondentResponse createRespondent(String respondentName, int respondentAge, String respondentPhone,
			String respondentEmail, int surveyNumber, String answer);

	// 找作答
	public RespondentResponse findRespondent(int surveyNumber);

}
