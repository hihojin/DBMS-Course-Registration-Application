package course.servlet;

import course.dal.*;
import course.model.*;
import course.model.Registrations.Status;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	protected RegistrationsDao registrationsDao;
	
	@Override
	public void init() throws ServletException {
		registrationsDao = RegistrationsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Register for a Course");        
        req.getRequestDispatcher("/Register.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        int userId = Integer.parseInt(req.getParameter("userid"));
        int courseId = Integer.parseInt(req.getParameter("courseid"));
        String status = req.getParameter("status");
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
        try {
        	Registrations registration = new Registrations(userId, courseId, thisStatus);
        	registration = registrationsDao.create(registration);
        	messages.put("success", "Successfully created " + registration.getRegistrationId());
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new IOException(e);
        }

        req.getRequestDispatcher("/Register.jsp").forward(req, resp);
	}

}
