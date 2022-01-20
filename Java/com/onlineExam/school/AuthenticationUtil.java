package com.onlineExam.school;

import java.sql.*;

public class AuthenticationUtil{
		
	public void adminValidation(LoginBean login) throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			validationPassword(connection,login);
			connection.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(resultset != null){
				resultset.close();
			}
		}
	}
	private void validationPassword(Connection connection,LoginBean login) throws Exception{
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try{
			String registerNumber = "SELECT PASSWORD FROM AUTHENTICATION WHERE REGISTERNUMBER = ?"; 
			preparedStatement = connection.prepareStatement(registerNumber);
			preparedStatement.setString(1, login.getRegisterNumber());
			
			resultset = preparedStatement.executeQuery();
			
			if (resultset.next()){
				String password = resultset.getString("PASSWORD");
				String dbpassword = login.getPassword();
				
				if(!password.equals(dbpassword)){
					throw new Exception("Invalid Password");
				}
			}
			else{
				throw new Exception("Invalid User");
			}
			resultset.close();
			preparedStatement.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(resultset != null){
				resultset.close();
			}
		}
	}
	public void studentValidation(LoginBean stulogin)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			studentPasswordValidation(connection,stulogin);
			connection.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(connection != null){
				connection.close();
			}
		}
	}
	private void studentPasswordValidation(Connection connection, LoginBean stulogin) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String validationStuPassword = "SELECT PASSWORD FROM STUDENT_AUTHENTICATION WHERE LOGIN_ID = ?";
			preparedStatement = connection.prepareStatement(validationStuPassword);
			preparedStatement.setString(1, stulogin.getRegisterNumber());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				String password = resultSet.getString("PASSWORD");
				String dbpassword = stulogin.getPassword();
				
				if(!password.equals(dbpassword)){
					throw new Exception("Invalid Password");
				}
			}
			else{
				throw new Exception("Invalid User");
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
			if(connection != null){
				connection.close();
			}
		}
	}
}