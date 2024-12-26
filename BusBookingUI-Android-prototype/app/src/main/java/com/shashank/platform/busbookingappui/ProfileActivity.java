package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView userNameTextView, userEmailTextView;
    ListView ordersListView;
    Button logoutButton;
    private ImageButton searchButton, profileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Инициализация элементов интерфейса
        userNameTextView = findViewById(R.id.userNameTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        ordersListView = findViewById(R.id.ordersListView);
        logoutButton = findViewById(R.id.logoutButton);
        searchButton = findViewById(R.id.SearchButton);
// Обработка нажатия кнопки поиска
        searchButton.setOnClickListener(view -> {
            Intent searchIntent = new Intent(ProfileActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        });
        // Получение данных пользователя (например, из Intent или SharedPreferences)
        String userName = "Имя пользователя"; // Замените на реальные данные
        String userEmail = "email@example.com"; // Замените на реальные данные

        // Установка данных пользователя
        userNameTextView.setText(userName);
        userEmailTextView.setText(userEmail);

        // Получение списка заказов (можно заменить на реальные данные)
        ArrayList<String> orders = new ArrayList<>();
        orders.add("Заказ 1: 10.10.2023, 10:00, Место 1 - Место 2");
        orders.add("Заказ 2: 11.10.2023, 12:00, Место 3 - Место 4");
        orders.add("Заказ 3: 12.10.2023, 14:00, Место 5 - Место 6");

        // Настройка адаптера для списка заказов
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orders);
        ordersListView.setAdapter(adapter);

        // Обработка нажатия кнопки выхода
        logoutButton.setOnClickListener(view -> {
            // Логика выхода (например, очистка данных пользователя)
            Toast.makeText(ProfileActivity.this, "Вы вышли из системы", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем активность
        });
    }
}
