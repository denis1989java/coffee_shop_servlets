package ru.mail.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Denis Monich
 * this class getting request to and sending response from logout page
 */
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to LogoutServlet doGet");
        //set null to user for session and redirect to login page
        req.getSession().invalidate();
        logger.debug("redirect to login page");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
