package com.shashank.platform.busbookingappui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    EditText fromField, toField, dateField, passengersField;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Элементы интерфейса
        fromField = findViewById(R.id.fromField);
        toField = findViewById(R.id.toField);
        dateField = findViewById(R.id.dateField);
        passengersField = findViewById(R.id.passengersField);
        searchButton = findViewById(R.id.searchButton);

        // Обработка нажатия кнопки поиска
        searchButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchActivity.this, FlightsActivity.class);
            intent.putExtra("from", fromField.getText().toString());
            intent.putExtra("to", toField.getText().toString());
            intent.putExtra("date", dateField.getText().toString());
            intent.putExtra("passengers", passengersField.getText().toString());
            startActivity(intent);
        });
    }
}

