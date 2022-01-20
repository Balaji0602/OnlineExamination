package com.onlineExam.school;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionUtil {
	
	public void createSection(String section) throws Exception {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			validateSection(connection, section);
			int autoNumber = getAutoNumber(connection);
			insertSection(connection, autoNumber, section);
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	private void validateSection(Connection connection, String section) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			if (section == null || "".equals(section)) {
				throw new Exception("Section should not empty");
			}
			String validation = "SELECT * FROM SECTION WHERE SECTION = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, section);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				throw new Exception("Section " + section + " Already Exist");
			}
			resultSet.close();
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

	private int getAutoNumber(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String autoStandardNumber = "SELECT IFNULL(MAX(SECTION_ID),0)+1 AS AUTO_NUMBER FROM SECTION";
			preparedStatement = connection.prepareStatement(autoStandardNumber);
			resultSet = preparedStatement.executeQuery();
			int autoStandardId = 0;
			if (resultSet.next()) {
				autoStandardId = resultSet.getInt("AUTO_NUMBER");
			}
			preparedStatement.execute();
			preparedStatement.close();
			return autoStandardId;
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

	public void insertSection(Connection connection, int autoNumber, String section) throws Exception {
		PreparedStatement preparedStatement = null;
		try {
			String insertStandard = "INSERT INTO SECTION VALUES(?,?)";
			preparedStatement = connection.prepareStatement(insertStandard);
			preparedStatement.setInt(1, autoNumber);
			preparedStatement.setString(2, section);
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

	public static List<Section> showSection() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			String showstandard = "SELECT * FROM SECTION";
			preparedStatement = connection.prepareStatement(showstandard);
			resultSet = preparedStatement.executeQuery();

			List<Section> sections = new ArrayList<>();
			while (resultSet.next()) {
				int sectionId = resultSet.getInt("SECTION_ID");
				String sectionName = resultSet.getString("SECTION");

				Section section = new Section();
				section.setSectionId(sectionId);
				section.setSectionName(sectionName);
				sections.add(section);
			}
			preparedStatement.execute();
			preparedStatement.close();
			resultSet.close();
			return sections;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}
	public void editSection(Section section)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			validateSection(connection,section.getSectionName());
			String editSection = "UPDATE SECTION SET SECTION = ? WHERE SECTION_ID = ?";
			preparedStatement = connection.prepareStatement(editSection);
			preparedStatement.setString(1, section.getSectionName());
			preparedStatement.setInt(2, section.getSectionId());
			preparedStatement.execute();
			preparedStatement.close();
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
	public void deleteSection(Section section)throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = DatabaseConnection.getDatabaseConnection();
			deleteSectionValidation(connection,section.getSectionName());
			String deleteSection = "DELETE FROM SECTION WHERE SECTION_ID = ?";
			preparedStatement = connection.prepareStatement(deleteSection);
			preparedStatement.setInt(1, section.getSectionId());
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

	private void deleteSectionValidation(Connection connection, String sectionName) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String validation = " SELECT SEC.SECTION FROM STANDARD_SECTION_MAP SSM INNER JOIN SECTION SEC ON SSM.SECTION_ID = SEC.SECTION_ID WHERE SECTION = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, sectionName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				throw new Exception("Section " + sectionName + " is Mapped In Standard Section Creation Page So First You Need to Delete There");
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
}
