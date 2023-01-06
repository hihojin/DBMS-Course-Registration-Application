package course.servlet;

import course.dal.*;
import course.model.*;
import course.model.Courses.CertificateType;
import course.model.Courses.CourseDifficulty;
import course.model.Courses.CourseSubject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CourseCreate")
public class CourseCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CourseCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        // Create the Course.
        String courseName = req.getParameter("coursename");
        String courseUrl = req.getParameter("courseurl");
        String courseDescription = req.getParameter("coursedescription");   
        String stringCertificateTypeFromReq= req.getParameter("coursecertificatetype");
        CertificateType courseCertificateType = null;
        switch(stringCertificateTypeFromReq) {
        case "MASTER LEVEL":
        	courseCertificateType = CertificateType.masterLevel;
        	break;
        case "BACHELOR LEVEL":
        	courseCertificateType = CertificateType.bachelorLevel;
        	break;
        case "HIGH SCHOOL LEVEL":
        	courseCertificateType = CertificateType.highSchoolLevel;
        	break;
        case "PHD LEVEL":
        	courseCertificateType = CertificateType.phdLevel;
        	break;
        default:
        	break;
        }
        String stringCourseDifficultyFromReq= req.getParameter("coursedifficulty");
        CourseDifficulty courseDifficulty = null;
        switch(stringCourseDifficultyFromReq) {
        case "EASY":
        	courseDifficulty = CourseDifficulty.easy;
        	break;
        case "MEDIUM":
        	courseDifficulty = CourseDifficulty.medium;
        	break;
        case "HARD":
        	courseDifficulty = CourseDifficulty.hard;
        	break;
        case "SUPER HARD":
        	courseDifficulty = CourseDifficulty.SUPER_HARD;
        	break;
        default:
        	break;
        }
        String institutionName = req.getParameter("institutionname");   
        String stringCourseSubjectFromReq= req.getParameter("coursesubject");
        CourseSubject courseSubject = null;
        switch(stringCourseSubjectFromReq) {
        case "DATA_SCIENCE":
        	courseSubject = CourseSubject.DATA_SCIENCE;
        	break;
        case "BUSINESS":
        	courseSubject = CourseSubject.BUSINESS;
        	break;
        case "COMPUTER_SCIENCE":
        	courseSubject = CourseSubject.COMPUTER_SCIENCE;
        	break;
        case "INFORMATION_TECHNOLOGY":
        	courseSubject = CourseSubject.INFORMATION_TECHNOLOGY;
        	break;
        case "LANGUAGE_LEARNING":
        	courseSubject = CourseSubject.LANGUAGE_LEARNING;
        	break;
        case "HEALTH":
        	courseSubject = CourseSubject.HEALTH;
        	break;
        case "PERSONAL_DEVELOPMENT":
        	courseSubject = CourseSubject.PERSONAL_DEVELOPMENT;
        	break;
        case "PHYSICAL_SCIENCE_AND_ENGINEERING":
        	courseSubject = CourseSubject.PHYSICAL_SCIENCE_AND_ENGINEERING;
        	break;
        case "SOCIAL_SCIENCES":
        	courseSubject = CourseSubject.SOCIAL_SCIENCES;
        	break;
        case "ARTS_AND_HUMANITIES":
        	courseSubject = CourseSubject.ARTS_AND_HUMANITIES;
        	break;
        case "MATH_AND_LOGIC":
        	courseSubject = CourseSubject.MATH_AND_LOGIC;
        	break;
        default:
        	break;
        }
        String regionCode = req.getParameter("regioncode");   
        int professorsId = Integer.parseInt(req.getParameter("professorid"));
        int taId = Integer.parseInt(req.getParameter("taid"));
        try {
        	Courses course = new Courses(courseName, courseUrl, courseDescription,
        			courseCertificateType, courseDifficulty,
        			courseSubject,
        			institutionName, regionCode, professorsId, taId);
        	course = coursesDao.create(course);
        	messages.put("success", "Successfully created " + course.getCourseId());

        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new IOException(e);
        }

        
        req.getRequestDispatcher("/CourseCreate.jsp").forward(req, resp);
    }
}
