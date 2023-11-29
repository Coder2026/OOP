package com.example.tracker_calories;

public class FoodEntry extends Entry {
    public FoodEntry(String foodName, double calories) {
        super(foodName, calories);
    }

    @Override
    public String getDetails() {
        return getFoodName() + " - " + getCalories() + " calories";
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
