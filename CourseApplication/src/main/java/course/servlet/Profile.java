package course.servlet;

import course.dal.*;
import course.model.*;
import course.model.Registrations.Status;

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

@WebServlet("/Profile")

public class Profile extends HttpServlet {
	
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

        List<Registrations> registrations = new ArrayList<>();
        int userId = Integer.parseInt(req.getParameter("userid"));
        try {
        	registrations = registrationsDao.getRegistrationsByUser(userId);
        } catch (SQLException e) {
    		e.printStackTrace();
    		throw new IOException(e);
    	}

		req.setAttribute("registrations", registrations);
        req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
	}

}
