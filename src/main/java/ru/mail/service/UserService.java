package ru.mail.service;

public interface UserService {
    boolean isValidUser(String userEmail, String password);
}
