package com.example.caloriecounter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView caloriesConsumedTextView;
    private EditText caloriesEditText;

    private final List<CalorieEntry> calorieEntries = new ArrayList<>();
    private CalorieEntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        caloriesConsumedTextView = findViewById(R.id.calories_consumed_text_view);
        caloriesEditText = findViewById(R.id.calories_edit_text);
        Button addCaloriesButton = findViewById(R.id.add_calories_button);
        RecyclerView historyRecyclerView = findViewById(R.id.history_recycler_view);

        adapter = new CalorieEntryAdapter(calorieEntries);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(adapter);

        addCaloriesButton.setOnClickListener(v -> {
            int calories = Integer.parseInt(caloriesEditText.getText().toString());
            addCaloriesEntry(new CalorieEntry(new Date(), calories));
            caloriesEditText.setText("");
            updateCaloriesConsumed();
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void addCaloriesEntry(CalorieEntry entry) {
        calorieEntries.add(entry);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("DefaultLocale")
    private void updateCaloriesConsumed() {
        int totalCalories = calorieEntries.stream()
                .mapToInt(CalorieEntry::getCalories)
                .sum();
        caloriesConsumedTextView.setText(String.format("Потребляемые калории %d", totalCalories));
    }
}
