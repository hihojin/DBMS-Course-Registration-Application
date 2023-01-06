package course.dal;

import course.model.Courses;
import course.model.Courses.CertificateType;
import course.model.Courses.CourseDifficulty;
import course.model.Courses.CourseSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursesDao {

	// Single pattern: instantiation is limited to one object.
	private static course.dal.CoursesDao instance = null;
	/**
	 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
	 * instance. This is used to store {@link Courses} into your MySQL instance and retrieve
	 * {@link Courses} from MySQL instance.
	 */
	protected static ConnectionManager connectionManager;

	protected CoursesDao() {
		connectionManager = new ConnectionManager();
	}

	public static course.dal.CoursesDao getInstance() {
		if (instance == null) {
			instance = new course.dal.CoursesDao();
		}
		return instance;
	}

	public Courses create(Courses course) throws SQLException {
		String insertCourse =
				"INSERT INTO Courses(CourseName, CourseUrl, CourseDescription, "
						+ "CourseCertificateType, CourseDifficulty, CourseSubject, InstitutionName, "
						+ "RegionCode, ProfessorId, TAId) "
						+"VALUES(?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCourse,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, course.getCourseName());
			insertStmt.setString(2, course.getCourseUrl());
			insertStmt.setString(3, course.getCourseDescription());
			insertStmt.setString(4, course.getCourseCertificateType().getName());
			insertStmt.setString(5, course.getCourseDifficulty().getName());
			insertStmt.setString(6, course.getCourseSubject().name());
			insertStmt.setString(7, course.getInstitutionName());
			insertStmt.setString(8, course.getRegionCode());
			insertStmt.setInt(9, course.getProfessorsId());
			insertStmt.setInt(10, course.getTaId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int courseId = -1;
			if (resultKey.next()) {
				courseId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			course.setCourseId(courseId);
			return course;
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

	/**
	 * Delete the Courses instance. This runs a DELETE statement.
	 */
	public static Courses delete(Courses courses) throws SQLException {
		String deleteCourses = "DELETE FROM Courses WHERE CourseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCourses);
			deleteStmt.setInt(1, courses.getCourseId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Courses updateDescription(Courses courses, String newDescription) throws SQLException {
		String updateCourse = "UPDATE Courses SET CourseDescription=? WHERE CourseId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCourse);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, courses.getCourseId());
			updateStmt.executeUpdate();

			// Update the person param before returning to the caller.
			courses.setCourseDescription(newDescription);
			return courses;
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

	public Courses getCourseFromCourseId(int courseId) throws SQLException {
		String getCourse = 
				"SELECT CourseId, CourseName, CourseUrl, CourseDescription, CourseCertificateType, CourseDifficulty, " 
						+ "CourseSubject, InstitutionName, RegionCode, ProfessorId, TAId "
						+ "FROM Courses WHERE CourseId = ?;";   		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getCourse);
			selectStmt.setInt(1, courseId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int courseid = results.getInt("CourseId");
				String coursename = results.getString("CourseName");
				String courseurl = results.getString("CourseUrl");
				String coursedescription = results.getString("CourseDescription");
				String certificate = results.getString("CourseCertificateType");
				CertificateType coursecertificatetype = null;
				switch(certificate) {
				case "MASTER LEVEL":
					coursecertificatetype = CertificateType.masterLevel;
					break;
				case "BACHELOR LEVEL":
					coursecertificatetype = CertificateType.bachelorLevel;
					break;
				case "HIGH SCHOOL LEVEL":
					coursecertificatetype = CertificateType.highSchoolLevel;
					break;
				case "PHD LEVEL":
					coursecertificatetype = CertificateType.phdLevel;
					break;
				default:
					break;
				}
				String stringCourseDifficulty = results.getString("CourseDifficulty");
				CourseDifficulty coursedifficulty = null;
				switch(stringCourseDifficulty) {
				case "EASY":
					coursedifficulty = CourseDifficulty.easy;
					break;
				case "MEDIUM":
					coursedifficulty = CourseDifficulty.medium;
					break;
				case "HARD":
					coursedifficulty = CourseDifficulty.hard;
					break;
				case "SUPER HARD":
					coursedifficulty = CourseDifficulty.SUPER_HARD;
					break;
				default:
					break;
				}   
				String stringCourseSubject = results.getString("CourseSubject") ;
				CourseSubject coursesubject = null;
				switch(stringCourseSubject) {
				case "DATA_SCIENCE":
					coursesubject = CourseSubject.DATA_SCIENCE;
					break;
				case "BUSINESS":
					coursesubject = CourseSubject.BUSINESS;
					break;
				case "COMPUTER_SCIENCE":
					coursesubject = CourseSubject.COMPUTER_SCIENCE;
					break;
				case "INFORMATION_TECHNOLOGY":
					coursesubject = CourseSubject.INFORMATION_TECHNOLOGY;
					break;
				case "LANGUAGE_LEARNING":
					coursesubject = CourseSubject.LANGUAGE_LEARNING;
					break;
				case "HEALTH":
					coursesubject = CourseSubject.HEALTH;
					break;
				case "PERSONAL_DEVELOPMENT":
					coursesubject = CourseSubject.PERSONAL_DEVELOPMENT;
					break;
				case "PHYSICAL_SCIENCE_AND_ENGINEERING":
					coursesubject = CourseSubject.PHYSICAL_SCIENCE_AND_ENGINEERING;
					break;
				case "SOCIAL_SCIENCES":
					coursesubject = CourseSubject.SOCIAL_SCIENCES;
					break;
				case "ARTS_AND_HUMANITIES":
					coursesubject = CourseSubject.ARTS_AND_HUMANITIES;
					break;
				case "MATH_AND_LOGIC":
					coursesubject = CourseSubject.MATH_AND_LOGIC;
					break;
				default:
					break;
				}
				String courseinstitutionname = results.getString("InstitutionName");
				String courseregioncode = results.getString("RegionCode");   
				int courseprofessorid = results.getInt("ProfessorId");
				int coursetaid = results.getInt("TAId");
				return new Courses(courseid, coursename, courseurl,
						coursedescription, coursecertificatetype, 
						coursedifficulty,
						coursesubject, courseinstitutionname,
						courseregioncode, courseprofessorid, coursetaid);
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
	
	private String generateStatement(String courseName, String courseSubject, int professorId,
			String region, double rating, String getCourse, int[] column) {
		boolean advancedSearch = false;
		int count = 0;
		if (courseName != null && !courseName.trim().isEmpty()) {
			count += 1;
			column[0] = count;
			getCourse += "WHERE CourseName LIKE ? ";
			advancedSearch = true;
		}
		if (courseSubject != null && !courseSubject.trim().isEmpty()) {
			count += 1;
			column[1] = count;
			if (advancedSearch) {
				getCourse += "AND ";
			} else {
				getCourse += "WHERE ";
			}
			getCourse += "CourseSubject = ? ";
			advancedSearch = true;
		}
		if (professorId > 0) {
			count += 1;
			column[2] = count;
			if (advancedSearch) {
				getCourse += "AND ";
			} else {
				getCourse += "WHERE ";
			}
			getCourse += "ProfessorId = ? ";
			advancedSearch = true;
		}
		if (region != null && !region.trim().isEmpty()) {
			count += 1;
			column[3] = count;
			if (advancedSearch) {
				getCourse += "AND ";
			} else {
				getCourse += "WHERE ";
			}
			getCourse += "RegionCode = ? ";
			advancedSearch = true;
		}
		getCourse += "GROUP BY Courses.CourseId, Courses.CourseName, Courses.CourseUrl, Courses.CourseDescription, "
				+ "Courses.CourseCertificateType, Courses.CourseDifficulty, Courses.CourseSubject, Courses.InstitutionName, "
				+ "Courses.RegionCode, ProfessorId, TAId ";
		if (rating >= 0) {
			count += 1;
			column[4] = count;
			getCourse += "HAVING ";
			getCourse += "rating > ? ";
			advancedSearch = true;
		}
		getCourse += "ORDER BY rating DESC;";
		return getCourse;
	}

	public List<Courses> getCoursesByFilter(String courseName, String courseSubject, int professorId,
			String region, double rating) throws SQLException {
		List<Courses> courses = new ArrayList<>();
		int[] column = new int[5];
		String getCourse = 
				"SELECT Courses.*, IFNULL(AVG(Rating), 0) as rating "
				+ "FROM Courses LEFT OUTER JOIN Reviews "
				+ "ON Courses.CourseId = Reviews.CourseId ";
		getCourse = generateStatement(courseName, courseSubject, professorId,
			region, rating, getCourse, column);
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getCourse);
			if (column[0] != 0) {
				selectStmt.setString(column[0], "%" + courseName + "%");
			}
			if (column[1] != 0) {
				selectStmt.setString(column[1], courseSubject);
			}
			if (column[2] != 0) {
				selectStmt.setInt(column[2], professorId);
			}
			if (column[3] != 0) {
				selectStmt.setString(column[3], region);
			}
			if (column[4] != 0) {
				selectStmt.setDouble(column[4], rating);
			}
			results = selectStmt.executeQuery();
			while (results.next()) {
				int courseid = results.getInt("CourseId");
				String coursename = results.getString("CourseName");
				String courseurl = results.getString("CourseUrl");
				String coursedescription = results.getString("CourseDescription");
				String certificate = results.getString("CourseCertificateType");
				double myRating = results.getDouble("rating");
				CertificateType coursecertificatetype = null;
				switch(certificate) {
				case "MASTER LEVEL":
					coursecertificatetype = CertificateType.masterLevel;
					break;
				case "BACHELOR LEVEL":
					coursecertificatetype = CertificateType.bachelorLevel;
					break;
				case "HIGH SCHOOL LEVEL":
					coursecertificatetype = CertificateType.highSchoolLevel;
					break;
				case "PHD LEVEL":
					coursecertificatetype = CertificateType.phdLevel;
					break;
				default:
					break;
				}
				String stringCourseDifficulty = results.getString("CourseDifficulty");
				CourseDifficulty coursedifficulty = null;
				switch(stringCourseDifficulty) {
				case "EASY":
					coursedifficulty = CourseDifficulty.easy;
					break;
				case "MEDIUM":
					coursedifficulty = CourseDifficulty.medium;
					break;
				case "HARD":
					coursedifficulty = CourseDifficulty.hard;
					break;
				case "SUPER HARD":
					coursedifficulty = CourseDifficulty.SUPER_HARD;
					break;
				default:
					break;
				}   
				String stringCourseSubject = results.getString("CourseSubject") ;
				CourseSubject coursesubject = null;
				switch(stringCourseSubject) {
				case "DATA_SCIENCE":
					coursesubject = CourseSubject.DATA_SCIENCE;
					break;
				case "BUSINESS":
					coursesubject = CourseSubject.BUSINESS;
					break;
				case "COMPUTER_SCIENCE":
					coursesubject = CourseSubject.COMPUTER_SCIENCE;
					break;
				case "INFORMATION_TECHNOLOGY":
					coursesubject = CourseSubject.INFORMATION_TECHNOLOGY;
					break;
				case "LANGUAGE_LEARNING":
					coursesubject = CourseSubject.LANGUAGE_LEARNING;
					break;
				case "HEALTH":
					coursesubject = CourseSubject.HEALTH;
					break;
				case "PERSONAL_DEVELOPMENT":
					coursesubject = CourseSubject.PERSONAL_DEVELOPMENT;
					break;
				case "PHYSICAL_SCIENCE_AND_ENGINEERING":
					coursesubject = CourseSubject.PHYSICAL_SCIENCE_AND_ENGINEERING;
					break;
				case "SOCIAL_SCIENCES":
					coursesubject = CourseSubject.SOCIAL_SCIENCES;
					break;
				case "ARTS_AND_HUMANITIES":
					coursesubject = CourseSubject.ARTS_AND_HUMANITIES;
					break;
				case "MATH_AND_LOGIC":
					coursesubject = CourseSubject.MATH_AND_LOGIC;
					break;
				default:
					break;
				}
				String courseinstitutionname = results.getString("InstitutionName");
				String courseregioncode = results.getString("RegionCode");   
				int courseprofessorid = results.getInt("ProfessorId");
				int coursetaid = results.getInt("TAId");
				Courses myCourse = new Courses(courseid, coursename, courseurl,
						coursedescription, coursecertificatetype, 
						coursedifficulty,
						coursesubject, courseinstitutionname,
						courseregioncode, courseprofessorid, coursetaid, myRating);
				courses.add(myCourse);
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
		return courses;
	}
	
}