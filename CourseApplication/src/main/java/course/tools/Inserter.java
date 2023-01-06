package course.tools;

import course.dal.*;
import course.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		CoursesDao courseDao = CoursesDao.getInstance();
		
		// INSERT objects from our model.
		Courses course1 = new Courses(1, "a", "b", "c", null, null, null, null, null, 0, 0);
		course1 = courseDao.create(course1);
		
		
		// READ.
		Courses c1 = courseDao.getCourseFromCourseId(1);
	}
}
