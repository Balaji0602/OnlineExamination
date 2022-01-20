package com.onlineExam.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OnlineExam {
	public static List<Question> showQuestion(Question onlineExam)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String showQuestion = "SELECT QUESTION,OPT1,OPT2,OPT3,OPT4 FROM QUESTION_ANSWER WHERE SS_ID = ?,SUBJECT = ?";
			preparedStatement = connection.prepareStatement(showQuestion);
			preparedStatement.setString(1, onlineExam.getSSid());
			preparedStatement.setString(2, onlineExam.getSubjectId());
			resultSet = preparedStatement.executeQuery();
			List<Question> questions = new ArrayList<>();
			while(resultSet.next()){
				String questionId = resultSet.getString("QUESTION_ID");
				String question = resultSet.getString("QUESTION");
				String option1 = resultSet.getString("OPT1");
				String option2 = resultSet.getString("OPT2");
				String option3 = resultSet.getString("OPT3");
				String option4 = resultSet.getString("OPT4");
				Question ques = new Question();
				ques.setQuestionId(questionId);
				ques.setQuestion(question);
				ques.setOption1(option1);
				ques.setOption2(option2);
				ques.setOption3(option3);
				ques.setOption4(option4);
				questions.add(ques);
			}
			resultSet.close();
			preparedStatement.close();
			return questions;
		}catch(Exception ex){
			ex.printStackTrace();
			throw  ex;
		}
	}
}
