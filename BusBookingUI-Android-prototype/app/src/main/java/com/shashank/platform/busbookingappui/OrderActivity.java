package com.shashank.platform.busbookingappui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    EditText dateField, timeField, seatsField, paymentField;
    Spinner boardingPointSpinner, dropOffPointSpinner;
    Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Элементы интерфейса
//        dateField = findViewById(R.id.dateField);
//        timeField = findViewById(R.id.timeField);
//        seatsField = findViewById(R.id.seatsField);
//        boardingPointSpinner = findViewById(R.id.boardingPointSpinner);
//        dropOffPointSpinner = findViewById(R.id.dropOffPointSpinner);
//        paymentField = findViewById(R.id.paymentField);
//        orderButton = findViewById(R.id.orderButton);

        // Обработка нажатия кнопки заказа
        orderButton.setOnClickListener(view -> {
            // Логика оформления заказа
            String date = dateField.getText().toString();
            String time = timeField.getText().toString();
            String seats = seatsField.getText().toString();
            String boardingPoint = boardingPointSpinner.getSelectedItem().toString();
            String dropOffPoint = dropOffPointSpinner.getSelectedItem().toString();
            String payment = paymentField.getText().toString();

            // Пример простого валидатора (можно расширить)
            if (date.isEmpty() || time.isEmpty() || seats.isEmpty()) {
                Toast.makeText(OrderActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // Если все корректно, можно отправить на сервер или сохранить
            Toast.makeText(OrderActivity.this, "Заказ размещен!", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем эту активность
        });
    }
}

