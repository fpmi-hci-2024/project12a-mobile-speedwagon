package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class OrderActivity extends AppCompatActivity {
    EditText dateField, timeField;
    Spinner seatsSpinner, paymentSpinner, boardingPointSpinner, dropOffPointSpinner;
    Button orderButton;
    private ImageButton searchButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Элементы интерфейса
        dateField = findViewById(R.id.dateField);
        timeField = findViewById(R.id.timeField);
        seatsSpinner = findViewById(R.id.seatsSpinner);
        boardingPointSpinner = findViewById(R.id.boardingPointSpinner);
        dropOffPointSpinner = findViewById(R.id.dropOffPointSpinner);
        paymentSpinner = findViewById(R.id.paymentSpinner);
        orderButton = findViewById(R.id.orderButton);
        searchButton = findViewById(R.id.SearchButton);
        profileButton = findViewById(R.id.profileButton);

        // Обработка нажатия кнопки профиля
        profileButton.setOnClickListener(view -> {
            Intent profileIntent = new Intent(OrderActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        });

        // Настройка адаптеров для Spinner
        ArrayAdapter<CharSequence> boardingAdapter = ArrayAdapter.createFromResource(this,
                R.array.boarding_points, android.R.layout.simple_spinner_item);
        boardingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardingPointSpinner.setAdapter(boardingAdapter);

        ArrayAdapter<CharSequence> dropOffAdapter = ArrayAdapter.createFromResource(this,
                R.array.drop_off_points, android.R.layout.simple_spinner_item);
        dropOffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropOffPointSpinner.setAdapter(dropOffAdapter);

        ArrayAdapter<CharSequence> seatsAdapter = ArrayAdapter.createFromResource(this,
                R.array.seats_array, android.R.layout.simple_spinner_item);
        seatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatsSpinner.setAdapter(seatsAdapter);

        ArrayAdapter<CharSequence> paymentAdapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods, android.R.layout.simple_spinner_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(paymentAdapter);

        // Получение данных из Intent
        String intentDate = getIntent().getStringExtra("date");
        String intentTime = getIntent().getStringExtra("time");
        String route = getIntent().getStringExtra("route");

        // Заполнение полей
        dateField.setText(intentDate);
        timeField.setText(intentTime);

        // Заполнение поля маршрута
        EditText routeField = findViewById(R.id.routeField);
        routeField.setText(route); // Заполнение поля маршрута

        // Обработка нажатия кнопки заказа
        orderButton.setOnClickListener(view -> {
            // Логика оформления заказа
            String orderDate = dateField.getText().toString();
            String orderTime = timeField.getText().toString();
            String seats = seatsSpinner.getSelectedItem().toString();
            String boardingPoint = boardingPointSpinner.getSelectedItem().toString();
            String dropOffPoint = dropOffPointSpinner.getSelectedItem().toString();
            String payment = paymentSpinner.getSelectedItem().toString();

            // Пример простого валидатора (можно расширить)
            if (orderDate.isEmpty() || orderTime.isEmpty()) {
                Toast.makeText(OrderActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // Формируем строку заказа
            String orderDetails = String.format("Дата: %s, Время: %s, Места: %s, Пункт посадки: %s, Пункт высадки: %s, Оплата: %s",
                    orderDate, orderTime, seats, boardingPoint, dropOffPoint, payment);

            // Сохранение заказа в SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("User  Orders", MODE_PRIVATE);
            Set<String> orders = sharedPreferences.getStringSet("orders", new HashSet<>());
            orders.add(orderDetails);
            sharedPreferences.edit().putStringSet("orders", orders).apply();

            // Уведомление об успешном размещении заказа
            Toast.makeText(OrderActivity.this, "Заказ размещен!", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем эту активность и возвращаемся к предыдущей
        });
    }
}


