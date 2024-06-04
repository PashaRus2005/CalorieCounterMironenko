package com.example.caloriecounter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalorieEntryAdapter extends RecyclerView.Adapter<CalorieEntryAdapter.ViewHolder> {
    private final List<CalorieEntry> calorieEntries;

    public CalorieEntryAdapter(List<CalorieEntry> calorieEntries) {
        this.calorieEntries = calorieEntries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calorie_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalorieEntry entry = calorieEntries.get(position);
        holder.dateTextView.setText(formatDate(entry.getDate()));
        holder.caloriesTextView.setText(String.valueOf(entry.getCalories()));
    }

    @Override
    public int getItemCount() {
        return calorieEntries.size();
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return formatter.format(date);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView caloriesTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            caloriesTextView = itemView.findViewById(R.id.calories_text_view);
        }
    }
}
