package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    EditText dateField, timeField, seatsField, paymentField;
    Spinner boardingPointSpinner, dropOffPointSpinner;
    Button orderButton;
    private ImageButton searchButton, profileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Элементы интерфейса
        dateField = findViewById(R.id.dateField);
        timeField = findViewById(R.id.timeField);
        seatsField = findViewById(R.id.seatsField);
        boardingPointSpinner = findViewById(R.id.boardingPointSpinner);
        dropOffPointSpinner = findViewById(R.id.dropOffPointSpinner);
        paymentField = findViewById(R.id.paymentField);
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

        // Получение данных из Intent
        String intentDate = getIntent().getStringExtra("date");
        String intentTime = getIntent().getStringExtra("time");
        String passengers = getIntent().getStringExtra("passengers");

        // Заполнение полей
        dateField.setText(intentDate);
        timeField.setText(intentTime);
        seatsField.setText(passengers); // Устанавливаем количество мест

        // Обработка нажатия кнопки заказа
        orderButton.setOnClickListener(view -> {
            // Логика оформления заказа
            String orderDate = dateField.getText().toString();
            String orderTime = timeField.getText().toString();
            String seats = seatsField.getText().toString();
            String boardingPoint = boardingPointSpinner.getSelectedItem().toString();
            String dropOffPoint = dropOffPointSpinner.getSelectedItem().toString();
            String payment = paymentField.getText().toString();

            // Пример простого валидатора (можно расширить)
            if (orderDate.isEmpty() || orderTime.isEmpty() || seats.isEmpty()) {
                Toast.makeText(OrderActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // Если все корректно, можно отправить на сервер или сохранить
            Toast.makeText(OrderActivity.this, "Заказ размещен!", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем эту активность и возвращаемся к предыдущей
        });
    }

}


