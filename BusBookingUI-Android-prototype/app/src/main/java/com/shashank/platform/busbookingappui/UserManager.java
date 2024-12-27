package com.shashank.platform.busbookingappui;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, String> users = new HashMap<>(); // Хранение пользователей (логин, пароль)

    // Метод для регистрации пользователя
    public boolean registerUser (String username, String password) {
        if (users.containsKey(username)) {
            return false; // Пользователь уже существует
        }
        users.put(username, password); // Сохраняем пользователя
        return true;
    }

    // Метод для входа пользователя
    public boolean loginUser (String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}

