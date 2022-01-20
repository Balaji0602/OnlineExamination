package com.onlineExam.school;
import java.sql.*;
import java.util.*;

public class ExaminationMode {
	public void createExaminationMode(String examinationMode)throws Exception{
		Connection connection = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			validateExamMode(connection,examinationMode);
			int autoNumber = getautoExamNo(connection);
			insertExaminationMode(connection,autoNumber,examinationMode);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	private void insertExaminationMode(Connection connection, int autoNumber, String examinationMode) throws Exception {
		PreparedStatement preparedStatement = null;
		try{
			String insertExamMode = "INSERT INTO EXAMINATION VALUES (?,?)";
			preparedStatement = connection.prepareStatement(insertExamMode);
			preparedStatement.setInt(1, autoNumber);
			preparedStatement.setString(2, examinationMode);
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

	private int getautoExamNo(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String autoNumber = "SELECT IFNULL(MAX(EXAMINATION_ID),0)+1 AS AUTO_NUMBER FROM EXAMINATION";
			preparedStatement = connection.prepareStatement(autoNumber);
			resultSet = preparedStatement.executeQuery();
			int autoExamId = 0;
			if(resultSet.next()){
				autoExamId = resultSet.getInt("AUTO_NUMBER");
			}
			resultSet.close();
			preparedStatement.close();
			return autoExamId;
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

	private void validateExamMode(Connection connection, String examinationMode) throws Exception{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String validation = "SELECT * FROM EXAMINATION WHERE EXAMINATION_NAME = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, examinationMode);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				throw new Exception ("The Mode of Examination is already Exist");
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
	public static List<Examination> showExamMode()throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String showExamMode = "SELECT * FROM EXAMINATION";
			preparedStatement = connection.prepareStatement(showExamMode);
			resultSet = preparedStatement.executeQuery();
			List<Examination> examMode = new ArrayList<>();
			while(resultSet.next()){
				int examinationId = resultSet.getInt("EXAMINATION_ID");
				String examinationMode = resultSet.getString("EXAMINATION_NAME");
				
				Examination exam = new Examination();
				exam.setExaminationId(examinationId);
				exam.setExaminationMode(examinationMode);
				examMode.add(exam);
			}
			resultSet.close();
			preparedStatement.close();
			return examMode;
		}catch(Exception ex){
			ex.printStackTrace();
			throw  ex;
		}
	}
	public void editExamMode(Examination examMode)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String editExamMode = "UPDATE EXAMINATION SET EXAMINATION_NAME = ? WHERE EXAMINATION_ID = ?";
			preparedStatement = connection.prepareStatement(editExamMode);
			preparedStatement.setString(1, examMode.getExaminationMode());
			preparedStatement.setInt(2, examMode.getExaminationId());
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
	public void deleteExamMode(int examinationId)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String deleteExamMode = ("DELETE FROM EXAMINATION WHERE EXAMINATION_ID = ?");
			preparedStatement = connection.prepareStatement(deleteExamMode);
			preparedStatement.setInt(1, examinationId);
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
