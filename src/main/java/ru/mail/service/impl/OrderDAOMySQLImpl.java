package ru.mail.service.impl;

import org.apache.log4j.Logger;
import ru.mail.service.InitClasses.DataSource;
import ru.mail.service.OrderDAO;
import ru.mail.service.model.Coffee;
import ru.mail.service.model.Order;
import ru.mail.service.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Monich
 * this class realise all logic of work with orders in MySQL DB
 */
public class OrderDAOMySQLImpl implements OrderDAO {

    private static final Logger logger = Logger.getLogger(OrderDAOMySQLImpl.class);
    private static final String UPDATE_ORDER_QUERY = "UPDATE `order` SET status=1 WHERE id=?";
    private static final String GET_ALL_ORDERS_QUERY = "select id, customer_name, customer_address, phone, status, price  from `order`";
    private static final String INSERT_NEW_ORDER_QUERY = "INSERT INTO `order` (customer_name,customer_address,phone,status,price) values(?,?,?,?,?)";
    private static final String INSERT_NEW_ORDER_ITEM_QUERY = "INSERT INTO order_item (coffee,`order`,quantity) values(?,?,?)";
    private static final String GET_ALL_ORDER_ITEMS_BY_ORDER_ID = "select `order_item`.quantity,`coffee`.name from `order_item` inner join `coffee` on `order_item`.coffee=`coffee`.id where `order_item`.order=?";


    /**
     * changing the status
     * @param id of which status will changed
     */
    @Override
    public void update(Long id) {
        logger.debug("Update order with id: " + id);
        logger.debug("Query: " + UPDATE_ORDER_QUERY);

        try {
            Connection connection=DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);
            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(id)));
            int i = preparedStatement.executeUpdate();
            logger.debug("Insert finished with code: " + i);
            DataSource.returnConnection(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            logger.debug("DB connection error: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getting all orders
     * @return list of orders
     */
    @Override
    public List<Order> list() {
        logger.debug("Get all orders");
        logger.debug("Query: " + GET_ALL_ORDERS_QUERY);
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection=DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_QUERY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setUserName(rs.getString(2));
                order.setAddress(rs.getString(3));
                order.setPhoneNumber(rs.getString(4));
                order.setDelivery(rs.getInt(5));
                order.setPrice(rs.getBigDecimal(6));
                orders.add(order);
            }
            DataSource.returnConnection(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            logger.debug("DB connection error: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.debug("Get all orders items");
        logger.debug("Query: " + GET_ALL_ORDER_ITEMS_BY_ORDER_ID);
        for (Order order : orders) {
            List<OrderItem> orderItems = new ArrayList<>();
            try {
                Connection connection=DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDER_ITEMS_BY_ORDER_ID);
                preparedStatement.setLong(1, order.getId());
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(rs.getInt(1));
                    Coffee coffee = new Coffee();
                    coffee.setName(rs.getString(2));
                    orderItem.setCoffee(coffee);
                    orderItems.add(orderItem);
                }
                DataSource.returnConnection(connection);
                preparedStatement.close();
            } catch (SQLException e) {
                logger.debug("DB connection error: " + e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            order.setOrderItemList(orderItems);
        }
        return orders;
    }


    /**
     * saving ste order
     * @param order is order which will be saved
     */
    @Override
    public void save(Order order) {
        logger.debug("save order");
        logger.debug("Query: " + INSERT_NEW_ORDER_QUERY);
        try {
            Connection connection=DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getUserName());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, order.getPhoneNumber());
            preparedStatement.setInt(4, 0);
            preparedStatement.setBigDecimal(5, order.getPrice());
            int i = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                logger.debug("Generated order id value: " + generatedKeys.getLong(1));
                order.setId(generatedKeys.getLong(1));
            }
            DataSource.returnConnection(connection);
            preparedStatement.close();
            logger.debug("Insert finished with code: " + i);
        } catch (SQLException e) {
            logger.debug("DB connection error: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.debug("insert order items");
        logger.debug("Query: " + INSERT_NEW_ORDER_ITEM_QUERY);
        try {
            Connection connection=DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_ORDER_ITEM_QUERY);
            for (OrderItem orderItem : order.getOrderItemList()) {
                preparedStatement.setLong(1, orderItem.getCoffee().getId());
                preparedStatement.setLong(2, order.getId());
                preparedStatement.setInt(3, orderItem.getQuantity());
                int i = preparedStatement.executeUpdate();
                logger.debug("Insert finished with code: " + i);
            }
            DataSource.returnConnection(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            logger.debug("DB connection error: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
