package ru.mail.controller;


import org.apache.log4j.Logger;
import ru.mail.service.InitClasses.DaoFactory;
import ru.mail.service.OrderService;
import ru.mail.service.impl.OrderServiceImpl;
import ru.mail.service.model.Coffee;
import ru.mail.service.model.Order;
import ru.mail.service.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author Denis Monich
 * this class getting request to and sending response from coffeeList page
 */
public class CoffeeListServlet extends HttpServlet {

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private static final Logger logger = Logger.getLogger(CoffeeListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to CoffeeListServlet doGet");
        logger.debug("getting list of all coffees");
        List<Coffee> coffees = daoFactory.getCoffeeDao().list();
        HttpSession session = req.getSession();
        Order curOrder = (Order) session.getAttribute("order");
        if (curOrder != null) {
            int length = 0;
            for (OrderItem orderItem : curOrder.getOrderItemList()) {
                length = length + orderItem.getQuantity();
            }
            req.setAttribute("length", length);
        }
        req.setAttribute("coffees", coffees);
        req.getRequestDispatcher("/WEB-INF/jsp/coffees.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("coming to CoffeeListServlet doPost");
        String toSendQuantity = req.getParameter("toSendQuantity");
        String toSendCoffeeId = req.getParameter("toSendCoffeeId");
        String added=req.getParameter("added");
        //checking is request parameters not null
        HttpSession session = req.getSession();
        logger.debug("putting the order to basket");
        Order curOrder = (Order) session.getAttribute("order");
        //putting order to basket and getting Id of this order
        Order order = orderService.putToBasket(curOrder, toSendQuantity, toSendCoffeeId);
        //putting order id to session
        session.setAttribute("order", order);
        logger.debug("redirect to order page");
        if(!added.equals("")){
            resp.sendRedirect(req.getContextPath() + "/coffeeList");
        }else{
            resp.sendRedirect(req.getContextPath() + "/order");
        }
    }
}
