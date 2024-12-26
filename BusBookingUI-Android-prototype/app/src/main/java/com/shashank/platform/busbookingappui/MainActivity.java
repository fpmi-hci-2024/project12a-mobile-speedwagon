package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
            // Здесь добавьте логику аутентификации
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            finish(); // Завершаем текущую активность
        });

        // Обработка нажатия кнопки регистрации
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}

