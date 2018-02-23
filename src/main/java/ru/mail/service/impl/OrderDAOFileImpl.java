package ru.mail.service.impl;

import org.apache.log4j.Logger;
import ru.mail.service.OrderDAO;
import ru.mail.service.model.Order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Denis Monich
 * this class realise all logic of work with orders in files
 */
public class OrderDAOFileImpl implements OrderDAO {
    
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("root");
    private static final Logger logger = Logger.getLogger(OrderDAOFileImpl.class);

    /**
     * changing the status
     * @param id of which status will changed
     */
    @Override
    public void update(Long id) {
        List<Order> orders = list();
        Order newOrder = null;
        logger.debug("finding the order by Id");
        //finding the order by Id
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                newOrder = order;
            }
        }
        logger.debug("changing the delivery status");
        //changing the delivery status
        if (newOrder != null) {
            newOrder.setDelivery(1);
        }
        save(newOrder);
    }

    /**
     * getting all orders
     * @return list of orders
     */
    @Override
    public List<Order> list() {
        logger.debug("getting all orders from file");
        List<Order> orders = new ArrayList<>();
        Path path = Paths.get(resourceBundle.getString("orderRoot"));
        if (Files.exists(path)) {
            try {
                FileInputStream fis = new FileInputStream(resourceBundle.getString("orderRoot"));
                //Create new ObjectInputStream object to read object from file
                ObjectInputStream obj = new ObjectInputStream(fis);
                while (true) {
                    //Read object from file
                    orders = (List<Order>) obj.readObject();
                    if (fis.available() != -1) {
                        break;
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                logger.debug("ClassNotFoundException | IOException e" + e);
            }
        }
        logger.debug("Orders in file: " + orders.size());
        return orders;
    }

    /**
     * saving ste order
     * @param order is order which will be saved
     */
    @Override
    public void save(Order order) {
        logger.debug("saving order with coffee list and price");
        //get all orders
        List<Order> orders = list();
        List<Order> newOrders = new ArrayList<>();
        boolean newOrder = true;
        if (orders.size() == 0) {
            //if no orders - add order
            newOrders.add(order);
        } else {
            //check is order exist
            for (Order order1 : orders) {

                if (order1.getId().equals(order.getId())) {
                    order1 = order;
                    newOrder = false;
                }
                newOrders.add(order1);
            }
            //if not exist add order to list
            if (newOrder) {
                newOrders.add(order);
            }
        }
        try {
            //save orders list
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(resourceBundle.getString("orderRoot")));
            logger.debug("writing the file with orders");
            out.writeObject(newOrders);
            out.close();
        } catch (IOException e) {
            logger.debug("IOException e"+e);
        }
    }
}
