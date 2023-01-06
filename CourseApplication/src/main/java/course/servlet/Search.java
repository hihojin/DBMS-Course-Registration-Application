package course.servlet;

import course.dal.*;
import course.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet{
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

        List<Courses> courses = new ArrayList<>();
        
        String courseName = req.getParameter("coursename");
        String courseSubject = req.getParameter("coursesubject");
        String pId = req.getParameter("professorid");
        int professorId = -1;
        if (pId != null && !pId.trim().isEmpty()) {
        	professorId = Integer.parseInt(pId);
        }
        String region = req.getParameter("region");
        String rtg = req.getParameter("rating");
        double rating = -1.0;
        if (rtg != null && !rtg.trim().isEmpty()) {
        	rating = Double.parseDouble(rtg);
        }
        
        try {
        	courses = coursesDao.getCoursesByFilter(courseName, courseSubject, professorId, region, rating);
        } catch (SQLException e) {
    		e.printStackTrace();
    		throw new IOException(e);
    	}
        messages.put("success", "Displaying results:");   
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/Search.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Courses> courses = new ArrayList<>();
        
        String courseName = req.getParameter("coursename");
        String courseSubject = req.getParameter("coursesubject");
        String pId = req.getParameter("professorid");
        int professorId = -1;
        if (pId != null && !pId.trim().isEmpty()) {
        	professorId = Integer.parseInt(pId);
        }
        String region = req.getParameter("region");
        String rtg = req.getParameter("rating");
        double rating = -1.0;
        if (rtg != null && !rtg.trim().isEmpty()) {
        	rating = Double.parseDouble(rtg);
        }
        
        try {
        	courses = coursesDao.getCoursesByFilter(courseName, courseSubject, professorId, region, rating);
        } catch (SQLException e) {
    		e.printStackTrace();
    		throw new IOException(e);
    	}
        messages.put("success", "Displaying results:"); 
		req.setAttribute("courses", courses);
        req.getRequestDispatcher("/Search.jsp").forward(req, resp);
	}
}
