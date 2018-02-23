package ru.mail.service.InitClasses;


import org.apache.log4j.Logger;
import ru.mail.service.CoffeeDAO;
import ru.mail.service.OrderDAO;
import ru.mail.service.impl.*;

import java.util.ResourceBundle;

public class DaoFactory {


    private static final Logger logger = Logger.getLogger(DaoFactory.class);

    private static final DaoFactory instance = new DaoFactory();

    private CoffeeDAO coffeeDao;
    private OrderDAO orderDao;

    private DaoFactory() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("root");
        String DBType = resourceBundle.getString("method.of.orders.saving");

        if ("file".equals(DBType)) {
            logger.debug("Work with files system");
            coffeeDao = new CoffeeDAOFileImpl();
            orderDao = new OrderDAOFileImpl();
        } else if ("mysql".equals(DBType)) {
            logger.debug("Work with MySQL DB");
            coffeeDao = new CoffeeDAOMySQLImpl();
            orderDao = new OrderDAOMySQLImpl();
        } else if ("oracle".equals(DBType)) {
            logger.debug("Work with Oracle DB");
            coffeeDao = new CoffeeDAOOracleImpl();
            orderDao = new OrderDAOOracleImpl();
        } else {
            logger.debug("wrong orders saving method");
        }
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public CoffeeDAO getCoffeeDao() {
        return coffeeDao;
    }

    public OrderDAO getOrderDao() {
        return orderDao;
    }
}
