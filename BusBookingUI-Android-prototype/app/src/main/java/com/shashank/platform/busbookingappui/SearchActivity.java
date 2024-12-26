package com.shashank.platform.busbookingappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private Spinner fromSpinner, toSpinner, dateSpinner, passengersSpinner;
    private Button searchButton;
    private ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Инициализация элементов интерфейса
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        dateSpinner = findViewById(R.id.dateSpinner);
        passengersSpinner = findViewById(R.id.passengersSpinner);
        searchButton = findViewById(R.id.searchButton);
        profileButton = findViewById(R.id.profileButton); // Инициализация кнопки профиля

        // Настройка адаптеров для спиннеров
        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        ArrayAdapter<CharSequence> dateAdapter = ArrayAdapter.createFromResource(this,
                R.array.dates_array, android.R.layout.simple_spinner_item);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);

        ArrayAdapter<CharSequence> passengersAdapter = ArrayAdapter.createFromResource(this,
                R.array.passengers_array, android.R.layout.simple_spinner_item);
        passengersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        passengersSpinner.setAdapter(passengersAdapter);

        // Обработка нажатия кнопки поиска
        searchButton.setOnClickListener(view -> {
            String from = fromSpinner.getSelectedItem().toString();
            String to = toSpinner.getSelectedItem().toString();
            String date = dateSpinner.getSelectedItem().toString();
            String passengers = passengersSpinner.getSelectedItem().toString();

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

        // Обработка нажатия кнопки профиля
        profileButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}




