package com.shashank.platform.busbookingappui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProfileActivity extends AppCompatActivity {
    EditText firstNameEditText, lastNameEditText, middleNameEditText, phoneEditText, emailEditText;
    Button saveButton, logoutButton;
    ListView ordersListView;
    ArrayList<String> ordersList; // Список заказов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Инициализация элементов интерфейса
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        middleNameEditText = findViewById(R.id.middleNameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.saveButton);
        logoutButton = findViewById(R.id.logoutButton);
        ordersListView = findViewById(R.id.ordersListView);

        // Получение данных пользователя из SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("User  Prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", ""); // Получаем логин
        String email = sharedPreferences.getString("email", ""); // Получаем почту

        // Устанавливаем логин как имя
        firstNameEditText.setText(username);
        emailEditText.setText(email);

        // Инициализация списка заказов
        ordersList = new ArrayList<>();

        // Получение списка заказов из SharedPreferences
        SharedPreferences orderPreferences = getSharedPreferences("User  Orders", MODE_PRIVATE);
        Set<String> ordersSet = orderPreferences.getStringSet("orders", new HashSet<>());
        if (ordersSet != null) {
            ordersList.addAll(ordersSet);
        }

        // Настройка адаптера для списка заказов
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersList);
        ordersListView.setAdapter(adapter);

        // Обработка нажатия кнопки сохранения
        saveButton.setOnClickListener(view -> {
            // Логика сохранения данных (например, в SharedPreferences)
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String middleName = middleNameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String emailInput = emailEditText.getText().toString();

            // Здесь можно добавить проверку данных перед сохранением

            // Уведомление об успешном сохранении
            Toast.makeText(ProfileActivity.this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
        });

        // Обработка нажатия кнопки выхода
        logoutButton.setOnClickListener(view -> {
            // Логика выхода (например, очистка данных пользователя)
            Toast.makeText(ProfileActivity.this, "Вы вышли из системы", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем активность
        });
    }
}


