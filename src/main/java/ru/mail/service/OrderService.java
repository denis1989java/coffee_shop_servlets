package ru.mail.service;

import ru.mail.service.model.Order;

import java.util.List;

public interface OrderService {
    Order updateOrder(Order order, String sendQuantity, String sendCoffeeId);
    Order putToBasket(Order order, String sendQuantity, String sendCoffeeId);
}
