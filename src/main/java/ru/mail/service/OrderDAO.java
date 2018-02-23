package ru.mail.service;

import ru.mail.service.model.Order;

import java.util.List;


public interface OrderDAO {

    void update (Long id);

    List<Order> list();

    void save(Order order);
}
