package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText loginInput, passwordInput;
    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Элементы интерфейса
        TextView title = findViewById(R.id.title);
        loginInput = findViewById(R.id.loginInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Обработка нажатия кнопки входа
        loginButton.setOnClickListener(view -> {
            String login = loginInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Проверка логина и пароля
            if (login.equals("user") && password.equals("password")) {
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
            startActivity(intent);
        });
    }
}

