package ru.mail.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Coffee implements Serializable {


    private static final long serialVersionUID = -585052033864304884L;
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Coffee() {
    }

    public Coffee(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
