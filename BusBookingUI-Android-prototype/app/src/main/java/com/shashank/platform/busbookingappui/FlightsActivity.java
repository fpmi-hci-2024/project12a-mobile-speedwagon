package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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
        // Пример добавления маршрута на основе переданных данных
        for (int i = 0; i < 5; i++) { // Предположим, 5 маршрутов для примера
            // Используем переданные данные для создания информации о рейсах
            String time1 = "8:00";
            String price = "15 руб.";
            String freeSeats = String.valueOf(10 - i); // Пример свободных мест

            // Создаем контейнер для маршрута
            LinearLayout flightLayout = new LinearLayout(this);
            flightLayout.setOrientation(LinearLayout.VERTICAL);
            flightLayout.setPadding(16, 16, 16, 16);
            flightLayout.setBackgroundResource(R.drawable.round_rect_shape);
            flightLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Создаем текстовое представление для маршрута
            TextView flightInfo = new TextView(this);
            flightInfo.setText(String.format("%s\n%s %s\nЦена за 1 место: %s\nСвободно: %s",
                    from, time1, "→", to, price, freeSeats));
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
            orderButton.setOnClickListener(view -> {
                // Обработка нажатия кнопки заказа
                Intent orderIntent = new Intent(FlightsActivity.this, OrderActivity.class);
                orderIntent.putExtra("from", from);
                orderIntent.putExtra("to", to);
                orderIntent.putExtra("date", date);
                orderIntent.putExtra("time", time1); // Передаем время
                orderIntent.putExtra("price", price); // Передаем цену
                orderIntent.putExtra("passengers", passengers); // Передаем количество пассажиров
                startActivity(orderIntent);
            });

            // Добавляем элементы в контейнер
            flightLayout.addView(flightInfo);
            flightLayout.addView(orderButton);
            flightsLayout.addView(flightLayout);
        }
    }
}

