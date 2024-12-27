package com.shashank.platform.busbookingappui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameInput, emailInput, passwordInput, confirmPasswordInput;
    private Button registerButton;
    private HashMap<String, String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Инициализация элементов интерфейса
        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        registerButton = findViewById(R.id.registerButton);

        // Получаем пользователей из Intent
        users = (HashMap<String, String>) getIntent().getSerializableExtra("users");

        // Обработка нажатия кнопки регистрации
        registerButton.setOnClickListener(view -> {
            // Получение данных из полей ввода
            String username = usernameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Проверка на пустые поля
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Проверка на корректность email
            if (!isValidEmail(email)) {
                Toast.makeText(RegisterActivity.this, "Некорректный адрес электронной почты.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Проверка на совпадение паролей
            if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                return;
            }

            // Сохранение пользователя (логин может не быть уникальным)
            users.put(username, password);

            // Сохранение логина и почты в SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("User  Prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("email", email);
            editor.apply();

            // Уведомление о успешной регистрации
            Toast.makeText(RegisterActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();

            // Закрываем активность регистрации
            finish();
        });

    }

    // Метод для проверки корректности email
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return Pattern.matches(emailPattern, email);
    }
}





