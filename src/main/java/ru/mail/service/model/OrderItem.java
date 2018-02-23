package ru.mail.service.model;

import java.io.Serializable;

public class OrderItem implements Serializable {


    private static final long serialVersionUID = -4340441494036748209L;
    private Long id;
    private Coffee coffee;
    private Order order;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Coffee coffee, Order order, Integer quantity) {
        this.coffee = coffee;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

