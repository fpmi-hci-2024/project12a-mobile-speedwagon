package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FlightsActivity extends AppCompatActivity {
    LinearLayout flightsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        flightsLayout = findViewById(R.id.flightsLayout);

        // Здесь добавьте доступные рейсы динамически
        loadAvailableFlights();
    }

    private void loadAvailableFlights() {
        // Пример добавления маршрута
        for (int i = 0; i < 5; i++) { // Предположим, 5 маршрутов для примера
            String from = "Город " + (i + 1); // Например, Город 1, Город 2 и т.д.
            String to = "Город " + (i + 2); // Следующий город
            String time1 = "8:00";
            String time2 = "9:30";
            String price = "15 руб.";
            String freeSeats = "7";

            // Создаем контейнер для маршрута
            LinearLayout flightLayout = new LinearLayout(this);
            flightLayout.setOrientation(LinearLayout.VERTICAL);
            flightLayout.setPadding(16, 16, 16, 16);
            flightLayout.setBackgroundResource(R.drawable.round_rect_shape); // Добавьте свой фон, если нужно
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
            orderButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary)); // Синий цвет
            orderButton.setTextColor(getResources().getColor(android.R.color.white));
            orderButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            orderButton.setOnClickListener(view -> {
                // Обработка нажатия кнопки заказа
                Intent intent = new Intent(FlightsActivity.this, OrderActivity.class);
                startActivity(intent);
            });

            // Добавляем элементы в контейнер
            flightLayout.addView(flightInfo);
            flightLayout.addView(orderButton);
            flightsLayout.addView(flightLayout);
        }
    }
}

