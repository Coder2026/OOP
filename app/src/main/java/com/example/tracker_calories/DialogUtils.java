package com.example.tracker_calories;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogUtils {
    public interface AddEntryListener {
        void onAddEntry(String foodName, double calories);
    }

    public static void openAddEntryDialog(Context context, final AddEntryListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_add_entry, null);

        final EditText editTextFoodName = dialogView.findViewById(R.id.editTextFoodName);
        final EditText editTextCalories = dialogView.findViewById(R.id.editTextCalories);

        builder.setView(dialogView)
                .setTitle("Add Entry")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String foodName = editTextFoodName.getText().toString().trim();
                        String caloriesText = editTextCalories.getText().toString().trim();

                        if (TextUtils.isEmpty(foodName) || TextUtils.isEmpty(caloriesText)) {
                            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            double calories = Double.parseDouble(caloriesText);

                            if (listener != null) {
                                listener.onAddEntry(foodName, calories);
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        builder.create().show();
    }
}

