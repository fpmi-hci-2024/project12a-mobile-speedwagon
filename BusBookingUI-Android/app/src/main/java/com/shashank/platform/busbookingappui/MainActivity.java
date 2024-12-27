package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText loginInput, passwordInput;
    Button loginButton, registerButton;

    // Хранение пользователей
    HashMap<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация пользователей
        initializeUsers();

        // Элементы интерфейса
        TextView title = findViewById(R.id.title);
        loginInput = findViewById(R.id.loginInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Обработка нажатия кнопки входа
        loginButton.setOnClickListener(view -> {
            String login = loginInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Проверка логина и пароля
            if (users.containsKey(login) && users.get(login).equals(password)) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                finish(); // Завершаем текущую активность
            } else {
                // Вывод сообщения об ошибке
                Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        });

        // Обработка нажатия кнопки регистрации
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.putExtra("users", users); // Передаем пользователей в RegisterActivity
            startActivity(intent);
        });
    }

    // Метод для инициализации пользователей
    private void initializeUsers() {
        users.put("user", "12345");
        users.put("ivanov", "1");
        users.put("petrov", "password2");
        users.put("sidorov", "password3");
        users.put("smirnov", "password4");
        users.put("kuznetsov", "password5");
        users.put("popov", "password6");
        users.put("voronov", "password7");
        users.put("nikolaev", "password8");
        users.put("fedorov", "password9");
        users.put("morozov", "password10");
    }
}




