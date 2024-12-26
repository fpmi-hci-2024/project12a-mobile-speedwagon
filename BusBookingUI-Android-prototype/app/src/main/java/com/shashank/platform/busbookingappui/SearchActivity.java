package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private EditText fromField, toField, dateField, passengersField;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Инициализация элементов интерфейса
        fromField = findViewById(R.id.fromField);
        toField = findViewById(R.id.toField);
        dateField = findViewById(R.id.dateField);
        passengersField = findViewById(R.id.passengersField);
        searchButton = findViewById(R.id.searchButton);

        // Обработка нажатия кнопки поиска
        searchButton.setOnClickListener(view -> {
            String from = fromField.getText().toString().trim();
            String to = toField.getText().toString().trim();
            String date = dateField.getText().toString().trim();
            String passengers = passengersField.getText().toString().trim();

            // Проверка на пустые поля
            if (from.isEmpty() || to.isEmpty() || date.isEmpty() || passengers.isEmpty()) {
                Toast.makeText(SearchActivity.this, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Создание и запуск Intent для перехода на FlightsActivity
            Intent intent = new Intent(SearchActivity.this, FlightsActivity.class);
            intent.putExtra("from", from);
            intent.putExtra("to", to);
            intent.putExtra("date", date);
            intent.putExtra("passengers", passengers);
            startActivity(intent);
        });
    }
}


