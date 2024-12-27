package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FlightsActivity extends AppCompatActivity {
    private LinearLayout flightsLayout;
    private ImageButton searchButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        flightsLayout = findViewById(R.id.flightsLayout);
        searchButton = findViewById(R.id.SearchButton);
        profileButton = findViewById(R.id.profileButton);

        // Получение данных из Intent
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String date = intent.getStringExtra("date");
        String passengers = intent.getStringExtra("passengers");

        // Отображение информации о рейсах
        loadAvailableFlights(from, to, date, passengers);

        // Обработка нажатия кнопки поиска
        searchButton.setOnClickListener(view -> {
            Intent searchIntent = new Intent(FlightsActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        });

        // Обработка нажатия кнопки профиля
        profileButton.setOnClickListener(view -> {
            Intent profileIntent = new Intent(FlightsActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        });
    }

    private void loadAvailableFlights(String from, String to, String date, String passengers) {
        // Формируем ключ для маршрута
        String key = from + "-" + to;
        List<Database.Route> availableRoutes = Database.routes.get(key);

        if (availableRoutes == null || availableRoutes.isEmpty()) {
            // Если маршрутов нет
            Toast.makeText(this, "Маршруты не найдены", Toast.LENGTH_SHORT).show();
            return;
        }

        // Проходим по всем маршрутам и отображаем их
        for (Database.Route route : availableRoutes) {
            // Создаём контейнер для маршрута
            LinearLayout flightLayout = new LinearLayout(this);
            flightLayout.setOrientation(LinearLayout.VERTICAL);
            flightLayout.setPadding(16, 16, 16, 16);
            flightLayout.setBackgroundResource(R.drawable.round_rect_shape);
            flightLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Создаём текстовое представление маршрута
            TextView flightInfo = new TextView(this);
            flightInfo.setText(String.format("%s → %s\nОтправление: %s\nПрибытие: %s\nЦена за 1 место: %d руб.\nСвободно: %d",
                    from, to, route.departureTime, route.arrivalTime, route.price, route.availableSeats));
            flightInfo.setTextSize(18);
            flightInfo.setTextColor(getResources().getColor(android.R.color.black));

            // Кнопка заказа
            Button orderButton = new Button(this);
            orderButton.setText("Заказать");
            orderButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            orderButton.setTextColor(getResources().getColor(android.R.color.white));
            orderButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            // Обработка нажатия кнопки заказа
            orderButton.setOnClickListener(view -> {
                // Обработка нажатия кнопки заказа
                Intent orderIntent = new Intent(FlightsActivity.this, OrderActivity.class);
                orderIntent.putExtra("from", from);
                orderIntent.putExtra("to", to);
                orderIntent.putExtra("date", date);
                orderIntent.putExtra("departureTime", route.departureTime); // Время отправления
                orderIntent.putExtra("arrivalTime", route.arrivalTime); // Время прибытия
                orderIntent.putExtra("price", route.price); // Цена
                orderIntent.putExtra("passengers", passengers); // Количество пассажиров
                orderIntent.putExtra("route", from + " → " + to); // Добавлено: маршрут
                orderIntent.putExtra("time", route.departureTime); // Добавлено: время
                startActivity(orderIntent);
            });


            // Добавляем элементы в контейнер
            flightLayout.addView(flightInfo);
            flightLayout.addView(orderButton);
            flightsLayout.addView(flightLayout);
        }

    }
}

