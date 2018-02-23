package ru.mail.service;

import ru.mail.service.model.Coffee;

import java.util.List;

public interface CoffeeDAO {
    List<Coffee> list();
}
