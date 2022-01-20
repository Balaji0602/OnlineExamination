package com.onlineExam.school;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StandardUtil {
	public static void main(String args[]) throws Exception {
		StandardUtil std = new StandardUtil();
		Standard standards = new Standard();
		standards.setStandardId(3);
		standards.setStandardName("1");
		std.editStandard(standards);
	}

	public void createStandard(String standard) throws Exception {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			validateStandard(connection, standard);
			int autoNumber = getAutoNumber(connection);
			insertStandard(connection, autoNumber, standard);
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void validateStandard(Connection connection, String standard) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			if (standard == null || "".equals(standard)) {
				throw new Exception("Standard should not empty");
			}
			String validation = "SELECT * FROM STANDARD WHERE STANDARD_NAME = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, standard);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				throw new Exception("Standard " + standard + " Already Exist");
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
			String autoStandardNumber = "SELECT IFNULL(MAX(STANDARD_ID),0)+1 AS AUTO_NUMBER FROM STANDARD";
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

	public void insertStandard(Connection connection, int autoNumber, String standard) throws Exception {
		PreparedStatement preparedStatement = null;
		try {
			String insertStandard = "INSERT INTO STANDARD VALUES(?,?)";
			preparedStatement = connection.prepareStatement(insertStandard);
			preparedStatement.setInt(1, autoNumber);
			preparedStatement.setString(2, standard);
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

	public static List<Standard> showStandard() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			String showstandard = "SELECT * FROM STANDARD";
			preparedStatement = connection.prepareStatement(showstandard);
			resultSet = preparedStatement.executeQuery();

			List<Standard> standards = new ArrayList<>();
			while (resultSet.next()) {
				int standardId = resultSet.getInt("STANDARD_ID");
				String standardName = resultSet.getString("STANDARD_NAME");

				Standard standard = new Standard();
				standard.setStandardId(standardId);
				standard.setStandardName(standardName);
				standards.add(standard);
			}
			preparedStatement.execute();
			preparedStatement.close();
			resultSet.close();
			return standards;
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

	public void editStandard(Standard standard) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			validateStandard(connection, standard.getStandardName());
			String editStandard = "UPDATE STANDARD SET STANDARD_NAME = ? WHERE STANDARD_ID = ?";
			preparedStatement = connection.prepareStatement(editStandard);
			preparedStatement.setString(1, standard.getStandardName());
			preparedStatement.setInt(2, standard.getStandardId());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void deleteStandard(Standard standard) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			deleteStandardValidation(connection,standard.getStandardName());
			String deleteStandard = "DELETE FROM STANDARD WHERE STANDARD_ID = ?";
			preparedStatement = connection.prepareStatement(deleteStandard);
			preparedStatement.setInt(1, standard.getStandardId());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
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
		}
	}

	private void deleteStandardValidation(Connection connection, String standardName) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String validation = " SELECT STD.STANDARD_NAME FROM STANDARD_SECTION_MAP SSM INNER JOIN STANDARD STD ON SSM.STANDARD_ID = STD.STANDARD_ID WHERE STANDARD_NAME = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, standardName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				throw new Exception("Standard " + standardName + " is Mapped In Standard Section Creation Page So You First Need to Delete There");
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
