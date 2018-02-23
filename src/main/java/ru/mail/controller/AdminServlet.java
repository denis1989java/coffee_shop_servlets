package ru.mail.controller;

import org.apache.log4j.Logger;
import ru.mail.service.InitClasses.DaoFactory;
import ru.mail.service.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author Denis Monich
 * this class getting request to and sending response from admin page
 */
public class AdminServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminServlet.class);
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("getting all orders");
        //getting all orders
        List<Order> orders = daoFactory.getOrderDao().list();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        logger.debug("changing order's delivery status by order Id");
        //changing order's delivery status by order Id
        daoFactory.getOrderDao().update(Long.valueOf(orderId));
        logger.debug("redirect to admin page");
        //redirect to admin page
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
