package ru.mail.controller;

import org.apache.log4j.Logger;
import ru.mail.service.OrderService;
import ru.mail.service.impl.OrderServiceImpl;
import ru.mail.service.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author Denis Monich
 * this class getting request to and sending response from order page
 */
public class OrderServlet extends HttpServlet {

    private final OrderService orderDAO = OrderServiceImpl.getInstance();
    private static final Logger logger = Logger.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to orderServlet doGet");
        HttpSession session = req.getSession();
        Order item = (Order) session.getAttribute("order");
        logger.debug("getting order details");
        if (item == null || item.getOrderItemList().isEmpty()) {
            logger.debug("redirect to coffeeList page");
            resp.sendRedirect(req.getContextPath() + "/coffeeList");
        } else {
            req.setAttribute("order", item);
            req.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to orderServlet doPost");
        String toSendQuantity = req.getParameter("toSendQuantity");
        String toSendCoffeeId = req.getParameter("toSendCoffeeId");
        String delete = req.getParameter("delete");
        String back = req.getParameter("goBack");
        //checking is request parameters not null
        if (toSendQuantity != null && toSendCoffeeId != null) {
            //length of parameters must be same
            HttpSession session = req.getSession();
            Order order = (Order) session.getAttribute("order");
            logger.debug("updating of order by Id");
            //putting order to basket and getting Id of this order
            order = orderDAO.updateOrder(order, toSendQuantity, toSendCoffeeId);
            logger.debug("redirect to confirmOrder page");
            session.setAttribute("order", order);
            if (delete != "") {
                resp.sendRedirect(req.getContextPath() + "/order");
            } else if (back != "") {
                resp.sendRedirect(req.getContextPath() + "/coffeeList");
            } else {
                resp.sendRedirect(req.getContextPath() + "/confirmOrder");
            }

        } else {
            logger.debug("redirect to coffeeList page");
            resp.sendRedirect(req.getContextPath() + "/coffeeList");
        }

    }
}
