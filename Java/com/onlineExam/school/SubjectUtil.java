package com.onlineExam.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectUtil {

	public void createSubject(String subject) throws Exception {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			validateStandard(connection, subject);
			int autoNumber = getAutoNumber(connection);
			insertStandard(connection, autoNumber, subject);
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void validateStandard(Connection connection, String subject) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			if (subject == null || "".equals(subject)) {
				throw new Exception("Subject should not empty");
			}
			String validation = "SELECT * FROM SUBJECT WHERE SUBJECT_NAME = ?";
			preparedStatement = connection.prepareStatement(validation);
			preparedStatement.setString(1, subject);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				throw new Exception("Subject " + subject + " Already Exist");
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
			String autoSubjectNumber = "SELECT IFNULL(MAX(SUBJECT_ID),0)+1 AS AUTO_NUMBER FROM SUBJECT";
			preparedStatement = connection.prepareStatement(autoSubjectNumber);
			resultSet = preparedStatement.executeQuery();
			int autoSubjectId = 0;
			if (resultSet.next()) {
				autoSubjectId = resultSet.getInt("AUTO_NUMBER");
			}
			preparedStatement.execute();
			preparedStatement.close();
			return autoSubjectId;
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

	public void insertStandard(Connection connection, int autoNumber, String subject) throws Exception {
		PreparedStatement preparedStatement = null;
		try {
			String insertSubject = "INSERT INTO SUBJECT VALUES(?,?)";
			preparedStatement = connection.prepareStatement(insertSubject);
			preparedStatement.setInt(1, autoNumber);
			preparedStatement.setString(2, subject);
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

	public static List<Subject> showSubject() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			String showSubject = "SELECT * FROM SUBJECT";
			preparedStatement = connection.prepareStatement(showSubject);
			resultSet = preparedStatement.executeQuery();

			List<Subject> subjects = new ArrayList<>();
			while (resultSet.next()) {
				int subjectId = resultSet.getInt("SUBJECT_ID");
				String subjectName = resultSet.getString("SUBJECT_NAME");

				Subject subject = new Subject();
				subject.setSubjectId(subjectId);
				subject.setSubjectName(subjectName);
				subjects.add(subject);
			}
			preparedStatement.execute();
			preparedStatement.close();
			resultSet.close();
			return subjects;
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

	public void editSubject(Subject subject) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			validateStandard(connection, subject.getSubjectName());
			String editSubject = "UPDATE SUBJECT SET SUBJECT_NAME = ? WHERE SUBJECT_ID = ?";
			preparedStatement = connection.prepareStatement(editSubject);
			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.setInt(2, subject.getSubjectId());
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

	public void deleteSubject(Subject subject) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseConnection.getDatabaseConnection();
			String deleteSubject = "DELETE FROM SUBJECT WHERE SUBJECT_ID = ?";
			preparedStatement = connection.prepareStatement(deleteSubject);
			preparedStatement.setInt(1, subject.getSubjectId());
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
}
