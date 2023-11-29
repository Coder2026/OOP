package com.example.tracker_calories;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Entry> entries;
    private EntryAdapter adapter;
    private ListView listView;
    private TextView totalCaloriesTextView;
    private Button btnAddEntry;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entries = new ArrayList<>();
        adapter = new EntryAdapter(this, entries);

        listView = findViewById(R.id.listView);
        totalCaloriesTextView = findViewById(R.id.totalCaloriesTextView);
        btnAddEntry = findViewById(R.id.btnAdd);

        listView.setAdapter(adapter);

        btnAddEntry.setOnClickListener(this);

        listView.setOnItemClickListener(this);
    }

    private void updateTotalCalories() {
        double totalCalories = 0.0;
        for (Entry entry : entries) {
            totalCalories += entry.getCalories();
        }
        totalCaloriesTextView.setText("Total Calories: " + totalCalories);
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
        adapter.notifyDataSetChanged();
        updateTotalCalories();
    }

    private void openAddEntryDialog() {
        DialogUtils.openAddEntryDialog(this, new DialogUtils.AddEntryListener() {
            @Override
            public void onAddEntry(String foodName, double calories) {
                FoodEntry newEntry = new FoodEntry(foodName, calories);
                addEntry(newEntry);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddEntry) {
            openAddEntryDialog();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Entry entry = entries.get(position);
        Intent intent;
        if (entry instanceof FoodEntry) {
            intent = new Intent(MainActivity.this, FoodEntry.class);
            intent.putExtra("food_entry_details", entry.getDetails());
        } else {
            intent = new Intent(MainActivity.this, Entry.class);
            intent.putExtra("entry_details", entry.getDetails());
        }
        startActivity(intent);
    }
}
