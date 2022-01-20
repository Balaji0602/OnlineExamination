package com.onlineExam.school;

import java.sql.*;
import java.util.*;

public class StandardSectionMapping {
	
	public void createStandardSection(StandardSection stdsec) throws Exception {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			validateStdSec(connection, stdsec);
			int autoNumber = getStandardSectionMapId(connection);
			insertStandardSectionMapId(connection, autoNumber, stdsec);
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	private void validateStdSec(Connection connection, StandardSection stdsec) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String validation = "SELECT * FROM STANDARD_SECTION_MAP WHERE STANDARD_ID = ? AND SECTION_ID = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1,stdsec.getStandardId());
			preparedStatement.setString(2,stdsec.getSectionId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				throw new Exception("Standard and Section"+stdsec.getStandardId() +"And"+ stdsec.getSectionId()+"Is Already Exist");
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
		}
	}

	private int getStandardSectionMapId(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String autoStdSecMapNumber = "SELECT IFNULL(MAX(SS_ID),0)+1 AS AUTO_NUMBER FROM STANDARD_SECTION_MAP";
			preparedStatement = connection.prepareStatement(autoStdSecMapNumber);
			resultSet = preparedStatement.executeQuery();
			int autoStandardSectionMapId = 0;
			if (resultSet.next()) {
				autoStandardSectionMapId = resultSet.getInt("AUTO_NUMBER");
			}
			preparedStatement.execute();
			preparedStatement.close();
			return autoStandardSectionMapId;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				preparedStatement.close();
			}
		}
	}

	private void insertStandardSectionMapId(Connection connection, int autoNumber, StandardSection stdsec)
			throws Exception {
		PreparedStatement preparedStatement = null;
		try {
			String insertSSid = "INSERT INTO STANDARD_SECTION_MAP VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(insertSSid);
			preparedStatement.setInt(1, autoNumber);
			preparedStatement.setString(2, stdsec.getStandardId());
			preparedStatement.setString(3, stdsec.getSectionId());

			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	public static List<StandardSection> showStdSec() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			String showStdSec = "SELECT SSM.SS_ID, STD.STANDARD_NAME , SEC.SECTION FROM STANDARD_SECTION_MAP SSM INNER JOIN STANDARD STD ON SSM.STANDARD_ID = STD.STANDARD_ID INNER JOIN SECTION SEC ON SSM.SECTION_ID = SEC.SECTION_ID";
			preparedStatement = connection.prepareStatement(showStdSec);
			resultSet = preparedStatement.executeQuery();

			List<StandardSection> stdsecs = new ArrayList<>();
			while (resultSet.next()) {
				int stdsecId = resultSet.getInt("SS_ID");
				String standardId = resultSet.getString("STANDARD_NAME");
				String sectionId = resultSet.getString("SECTION");

				StandardSection stdsec = new StandardSection();
				stdsec.setStandardSectionId(stdsecId);
				stdsec.setStandardName(standardId);
				stdsec.setSectionName(sectionId);
				stdsecs.add(stdsec);
			}
			resultSet.close();
			preparedStatement.close();
			return stdsecs;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}
	public void deleteStandardSection(String stdsecId)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			String deleteStdSec = "DELETE FROM STANDARD_SECTION_MAP WHERE SS_ID = ?";
			preparedStatement = connection.prepareStatement(deleteStdSec);
			preparedStatement.setString(1, stdsecId);
			preparedStatement.execute();
			connection.close();
			preparedStatement.close();
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
