package com.onlineExam.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionUtil {
	public void createQuestion(Question question)throws Exception{
		Connection connection = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			validateQuestion(connection,question.getQuestion());
			String autoNumber =  getautoQuestionId(connection);
			String stdsecmapId = standardSectionMap(connection,question);
			String autoQuestionNo =  getautoQuestionNo(connection);
			String status = "Active";
			insertQuestion(connection,autoNumber,question,stdsecmapId,autoQuestionNo,status);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	private String standardSectionMap(Connection connection, Question question) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String stdsecId = " SELECT SS_ID FROM STANDARD_SECTION_MAP WHERE STANDARD_ID = ? AND SECTION_ID = ?";
			preparedStatement = connection.prepareStatement(stdsecId);
			preparedStatement.setString(1, question.getStandard());
			preparedStatement.setString(2, question.getSection());
			resultSet = preparedStatement.executeQuery();
			String stdsecmapId = "0";
			if(resultSet.next()){
				stdsecmapId = resultSet.getString("SS_ID"); 
			}
			resultSet.close();
			preparedStatement.close();
			return stdsecmapId;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	private void insertQuestion(Connection connection, String autoNumber, Question question,String stdsecmapId,String autoQuestionNo,String status) throws Exception {
		PreparedStatement preparedStatement = null;
		try{
			String insertQuestion = " INSERT INTO QUESTION_ANSWER VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertQuestion);
			preparedStatement.setString(1, autoNumber);
			preparedStatement.setString(2, question.getExamMode());
			preparedStatement.setString(3, stdsecmapId);
			preparedStatement.setString(4, question.getSubject());
			preparedStatement.setString(5, autoQuestionNo);
			preparedStatement.setString(6, question.getQuestion());
			preparedStatement.setString(7, question.getOption1());
			preparedStatement.setString(8, question.getOption2());
			preparedStatement.setString(9, question.getOption3());
			preparedStatement.setString(10, question.getOption4());
			preparedStatement.setString(11, question.getAnswer());
			preparedStatement.setString(12, status);
			
			preparedStatement.execute();
			preparedStatement.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
		}
	}
	private String getautoQuestionNo(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String autoNumber = "SELECT IFNULL(MAX(QUESTION_NO),0)+1 AS AUTO_NUMBER FROM QUESTION_ANSWER";
			preparedStatement = connection.prepareStatement(autoNumber);
			resultSet = preparedStatement.executeQuery();
			String autoQuestionNo = "0";
			if(resultSet.next()){
				autoQuestionNo = resultSet.getString("AUTO_NUMBER");
			}
			resultSet.close();
			preparedStatement.close();
			return autoQuestionNo;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(resultSet != null){
				resultSet.close();
			}
		}
	}
	private String getautoQuestionId(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String autoNumber = "SELECT IFNULL(MAX(QUESTION_ID),0)+1 AS AUTO_NUMBER FROM QUESTION_ANSWER";
			preparedStatement = connection.prepareStatement(autoNumber);
			resultSet = preparedStatement.executeQuery();
			String autoQuestionId = "0";
			if(resultSet.next()){
				autoQuestionId = resultSet.getString("AUTO_NUMBER");
			}
			resultSet.close();
			preparedStatement.close();
			return autoQuestionId;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(resultSet != null){
				resultSet.close();
			}
		}
	}
	private void validateQuestion(Connection connection, String question) throws Exception{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String validation = "SELECT * FROM QUESTION_ANSWER WHERE QUESTION = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, question);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				throw new Exception ("The Question is already Exist");
			}
			resultSet.close();
			preparedStatement.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(resultSet != null){
				resultSet.close();
			}
		}
	}
	public static List<Question> showQuestion()throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String showQuestion = "SELECT * FROM QUESTION_ANSWER";
			preparedStatement = connection.prepareStatement(showQuestion);
			resultSet = preparedStatement.executeQuery();
			List<Question> questions = new ArrayList<>();
			while(resultSet.next()){
				String questionId = resultSet.getString("QUESTION_ID");
				String question = resultSet.getString("QUESTION");
				
				Question ques = new Question();
				ques.setQuestionId(questionId);
				ques.setQuestion(question);
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
	public void editQuestion(Question question)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String editExamMode = "UPDATE QUESTION_ANSWER SET QUESTION = ? WHERE QUESTION_ID = ?";
			preparedStatement = connection.prepareStatement(editExamMode);
			preparedStatement.setString(1, question.getQuestion());
			preparedStatement.setString(2, question.getQuestionId());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (preparedStatement != null){
				preparedStatement.close();
			}
			if (connection != null){
				connection.close();
			}
		}
	}
	public void deleteQuestion(String questionId)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String deleteQuestion = ("DELETE FROM QUESTION_ANSWER WHERE QUESTION_ID = ?");
			preparedStatement = connection.prepareStatement(deleteQuestion);
			preparedStatement.setString(1, questionId);
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(connection != null){
				connection.close();
			}
			if(preparedStatement != null){
				preparedStatement.close();
			}
		}
	}
}
