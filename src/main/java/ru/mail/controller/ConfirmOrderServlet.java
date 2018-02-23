package ru.mail.controller;

import org.apache.log4j.Logger;
import ru.mail.service.InitClasses.DaoFactory;
import ru.mail.service.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Denis Monich
 * this class getting request to and sending response from confirmOrder page
 */
public class ConfirmOrderServlet extends HttpServlet {
    
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = Logger.getLogger(ConfirmOrderServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to ConfirmOrderServlet doGet");
        //getting order Id from session
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        //checking is basket empty
        if (order == null) {
            logger.debug("redirect to coffeeList page");
            resp.sendRedirect(req.getContextPath() + "/coffeeList");
        } else {
            logger.debug("getting order details by order's id");

            if(order.getOrderItemList().isEmpty()){
                logger.debug("redirect to coffeeList page");
                resp.sendRedirect(req.getContextPath() + "/coffeeList");
            }else{
                req.setAttribute("order", order);
                req.getRequestDispatcher("/WEB-INF/jsp/confirmOrder.jsp").forward(req, resp);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to ConfirmOrderServlet doPost");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        if(order==null){
            logger.debug("redirect to /coffeeList");
            resp.sendRedirect(req.getContextPath() + "/coffeeList");
        }else{
            //checking is request parameters not null
            if (name != null && address != null && phone != null) {
                logger.debug("confirming order");
                //confirming order
                order.setUserName(name);
                order.setAddress(address);
                order.setPhoneNumber(phone);
                daoFactory.getOrderDao().save(order);
                //cleaning of session
                session.setAttribute("order", null);
                logger.debug("redirect to congratulation page");
                resp.sendRedirect(req.getContextPath() + "/congratulation");
            }
        }



    }
}
