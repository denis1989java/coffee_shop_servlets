package ru.mail.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Denis Monich
 * this class getting request to and sending response from congratulation page
 */
public class CongratulationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CongratulationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to congratulation servlet doGet");
        HttpSession session = req.getSession();
        Long orderId = (Long) session.getAttribute("orderId");
        //checking is order Id in session null
        if (orderId != null) {
            logger.debug("redirect to coffeeList page");
            resp.sendRedirect(req.getContextPath() + "/coffeeList");
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/congratulation.jsp").forward(req, resp);
        }
    }
}
