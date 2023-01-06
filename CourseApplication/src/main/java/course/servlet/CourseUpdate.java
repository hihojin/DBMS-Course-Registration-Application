package course.servlet;

import course.dal.*;
import course.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CourseUpdate")
public class CourseUpdate extends HttpServlet {
	
	protected CoursesDao courseDao;
	
	@Override
	public void init() throws ServletException {
		courseDao = CoursesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int courseId = Integer.parseInt(req.getParameter("courseid"));
        if (courseId == 0) {
            messages.put("Success", "Invalid CourseId");
        } else {
        	try {
        		Courses course = courseDao.getCourseFromCourseId(courseId);
        		if(courseId == 0) {
        			messages.put("success", "CourseId does not exist.");
        		}
        		req.setAttribute("course", course);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CourseUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int courseId = Integer.parseInt(req.getParameter("courseid"));
        if (courseId <= 0) {
            messages.put("Success", "Invalid CourseId");
        } else {
        	try {
        		Courses course = courseDao.getCourseFromCourseId(courseId);
        		if(course == null) {
        			messages.put("success", "CourseId does not exist. No update to perform.");
        		} else {
        			String newDescription = req.getParameter("coursedescription");
        			if (newDescription == null || newDescription.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid description.");
        	        } else {
        	        	course = courseDao.updateDescription(course, newDescription);
        	        	messages.put("success", "Successfully updated " + courseId);
        	        }
        		}
        		req.setAttribute("course", course);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CourseUpdate.jsp").forward(req, resp);
    }
}
