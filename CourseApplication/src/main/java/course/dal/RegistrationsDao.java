package course.dal;

import course.model.Registrations;
import course.model.Registrations.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistrationsDao {
	private static RegistrationsDao instance = null;
	protected static ConnectionManager connectionManager;
	
	protected RegistrationsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RegistrationsDao getInstance() {
		if (instance == null) {
			instance = new RegistrationsDao();
		}
		return instance;
	}
	
	public Registrations create(Registrations registration) throws SQLException {
		String insertResigration =
				"INSERT INTO RegistrationStatus(UserId, RegisteredCourse, Completed) "
						+"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertResigration,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, registration.getUserId());
			insertStmt.setInt(2, registration.getCourseId());
			insertStmt.setString(3, registration.getStatus().getName());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int registrationId = -1;
			if (resultKey.next()) {
				registrationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			registration.setRegistrationId(registrationId);
			return registration;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Registrations drop(Registrations registration) throws SQLException {
		String updateStatus = "UPDATE RegistrationStatus SET Completed=? WHERE RegistrationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStatus);
			updateStmt.setString(1, "DROPPED");
			updateStmt.setInt(2, registration.getRegistrationId());
			updateStmt.executeUpdate();
			
			registration.setStatus(Status.dropped);
			return registration;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Registrations complete(Registrations registration) throws SQLException {
		String updateStatus = "UPDATE RegistrationStatus SET Completed=? WHERE RegistrationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStatus);
			updateStmt.setString(1, "COMPLETED");
			updateStmt.setInt(2, registration.getRegistrationId());
			updateStmt.executeUpdate();
			
			registration.setStatus(Status.completed);
			return registration;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Registrations inProgress(Registrations registration) throws SQLException {
		String updateStatus = "UPDATE RegistrationStatus SET Completed=? WHERE RegistrationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStatus);
			updateStmt.setString(1, "IN_PROGRESS");
			updateStmt.setInt(2, registration.getRegistrationId());
			updateStmt.executeUpdate();
			
			registration.setStatus(Status.inProgress);
			return registration;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Registrations getRegistraionFromRegistrationId(int registrationId) throws SQLException {
		String getRegistration = 
				"SELECT * FROM RegistrationStatus WHERE RegistrationId = ?;";   		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getRegistration);
			selectStmt.setInt(1, registrationId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int userId = results.getInt("userId");
				int courseId = results.getInt("RegisteredCourse");
				String status = results.getString("Completed");
				Status thisStatus = null;
				switch(status) {
				case "DROPPED":
					thisStatus = Status.dropped;
					break;
				case "COMPLETED":
					thisStatus = Status.completed;
					break;
				case "IN_PROGRESS":
					thisStatus = Status.inProgress;
					break;
				default:
					break;
				}
				
				return new Registrations(registrationId, userId, courseId, thisStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
		}
		return null;
	}
	
	public List<Registrations> getRegistrationsByUser(int userId) throws SQLException {
		List<Registrations> registrations = new ArrayList<>();
		String getRegistration = 
				"SELECT RegistrationId, userId, RegisteredCourse, CourseName, CourseURL, Completed "
				+ "FROM RegistrationStatus JOIN Courses "
				+ "ON RegistrationStatus.RegisteredCourse = Courses.CourseId "
				+ "WHERE RegistrationStatus.userId =?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getRegistration);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int registrationId = results.getInt("RegistrationId");
				int courseId = results.getInt("RegisteredCourse");
				String courseName = results.getString("CourseName");
				String courseUrl = results.getString("CourseURL");
				String status = results.getString("Completed");
				Status thisStatus = null;
				switch(status) {
				case "DROPPED":
					thisStatus = Status.dropped;
					break;
				case "COMPLETED":
					thisStatus = Status.completed;
					break;
				case "IN_PROGRESS":
					thisStatus = Status.inProgress;
					break;
				default:
					break;
				}
				Registrations registration = new Registrations(registrationId, userId, courseId, thisStatus, courseName, courseUrl);
				registrations.add(registration);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return registrations;
	}
}
