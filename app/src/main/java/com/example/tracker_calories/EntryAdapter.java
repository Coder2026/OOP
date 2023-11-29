package com.example.tracker_calories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EntryAdapter extends ArrayAdapter<Entry> {
    private MainActivity mainActivity;

    public EntryAdapter(MainActivity mainActivity, ArrayList<Entry> entries) {
        super(mainActivity, 0, entries);
        this.mainActivity = mainActivity;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(mainActivity).inflate(
                    R.layout.list_item_entry, parent, false);
        }

        Entry currentEntry = getItem(position);

        TextView textViewFoodName = listItemView.findViewById(R.id.textViewFoodName);
        TextView textViewCalories = listItemView.findViewById(R.id.textViewCalories);

        if (currentEntry != null) {
            textViewFoodName.setText(currentEntry.getFoodName());
            textViewCalories.setText(String.valueOf(currentEntry.getCalories()) + " calories");
        }

        return listItemView;
    }
}
