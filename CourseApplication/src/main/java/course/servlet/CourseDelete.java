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


@WebServlet("/CourseDelete")
public class CourseDelete extends HttpServlet {
	
	protected CoursesDao coursesDao;
	
	@Override
	public void init() throws ServletException {
		coursesDao = CoursesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Course");        
        req.getRequestDispatcher("/CourseDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate course id.
        int courseId = Integer.parseInt(req.getParameter("courseid"));
        if (courseId <= 0) {
            messages.put("title", "Invalid CourseId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Course.
	        Courses course = new Courses(courseId);
	        try {
	        	course = CoursesDao.delete(course);
	        	// Update the message.
		        if (course == null) {
		            messages.put("title", "Successfully deleted " + courseId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + courseId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CourseDelete.jsp").forward(req, resp);
    }
}
