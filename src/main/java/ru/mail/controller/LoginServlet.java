package ru.mail.controller;

import org.apache.log4j.Logger;
import ru.mail.service.UserService;
import ru.mail.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Denis Monich
 * this class getting request to and sending response from login page
 */
public class LoginServlet extends HttpServlet {

    //getting access to service methods
    private final UserService userDAO = UserServiceImpl.getInstance();
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to LoginServlet doGet");
        String user = (String) req.getSession().getAttribute("user");
        if (user != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.debug("coming to LoginServlet doPost");
        //getting json file from UI
        String login = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        //checking is login and password is valid
        if (userDAO.isValidUser(login, password)) {
            String validUser = "admin";
            req.getSession().setAttribute("user", validUser);
            logger.debug("redirect to admin page");
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else {
            //sending json about invalid user
            logger.debug("redirect to login page");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

}
