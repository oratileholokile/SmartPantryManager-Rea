
package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.*;
import android.text.InputType;

import java.util.ArrayList;

public class PantryActivity extends Activity {

    private DBHelper dbHelper;
    private LinearLayout ingredientsLayout;

    private final int navy = Color.rgb(23, 54, 93);
    private final int blue = Color.rgb(57, 118, 197);
    private final int background = Color.rgb(240, 246, 255);
    private final int cardBlue = Color.rgb(220, 235, 255);

    private EditText nameInput;
    private EditText quantityInput;
    private EditText categoryInput;
    private EditText expiryInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(24, 24, 24, 24);
        mainLayout.setBackgroundColor(background);

        scrollView.addView(mainLayout);
        setContentView(scrollView);

        Button backButton = new Button(this);
        backButton.setText("← Back");
        backButton.setTextColor(Color.WHITE);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(navy));
        mainLayout.addView(backButton);

        backButton.setOnClickListener(v -> finish());

        TextView title = new TextView(this);
        title.setText("My Pantry");
        title.setTextSize(28);
        title.setTextColor(navy);
        title.setPadding(0, 20, 0, 20);
        mainLayout.addView(title);

        nameInput = createInput("Ingredient name");
        mainLayout.addView(nameInput);

        quantityInput = createInput("Quantity (e.g. 2 cups)");
        mainLayout.addView(quantityInput);

        categoryInput = createInput("Category (e.g. Vegetables)");
        mainLayout.addView(categoryInput);

        expiryInput = createInput("Expiry date (e.g. 2026-11-15)");
        mainLayout.addView(expiryInput);

        Button addButton = new Button(this);
        addButton.setText("Add Ingredient");
        addButton.setTextColor(Color.WHITE);
        addButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(blue));
        mainLayout.addView(addButton);

        TextView listTitle = new TextView(this);
        listTitle.setText("Your Ingredients");
        listTitle.setTextSize(22);
        listTitle.setTextColor(navy);
        listTitle.setPadding(0, 24, 0, 12);
        mainLayout.addView(listTitle);

        ingredientsLayout = new LinearLayout(this);
        ingredientsLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(ingredientsLayout);

        addButton.setOnClickListener(v -> addIngredient());

        loadIngredients();
    }

    private EditText createInput(String hint) {
        EditText input = new EditText(this);
        input.setHint(hint);
        input.setTextColor(navy);
        input.setHintTextColor(Color.GRAY);
        input.setSingleLine(true);
        return input;
    }

    private void addIngredient() {
        String name = nameInput.getText().toString().trim();
        String quantity = quantityInput.getText().toString().trim();
        String category = categoryInput.getText().toString().trim();
        String expiry = expiryInput.getText().toString().trim();

        if (name.isEmpty()) {
            nameInput.setError("Enter an ingredient name");
            return;
        }

        if (quantity.isEmpty()) {
            quantityInput.setError("Enter a quantity");
            return;
        }

        if (category.isEmpty()) {
            category = "Other";
        }

        if (expiry.isEmpty()) {
            expiry = "Not specified";
        }

        boolean success = dbHelper.addIngredient(
                name, quantity, category, expiry);

        if (success) {
            Toast.makeText(this, "Ingredient added successfully",
                    Toast.LENGTH_SHORT).show();

            nameInput.setText("");
            quantityInput.setText("");
            categoryInput.setText("");
            expiryInput.setText("");

            loadIngredients();
        } else {
            Toast.makeText(this, "Could not add ingredient",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadIngredients() {
        ingredientsLayout.removeAllViews();

        ArrayList<Ingredient> ingredients = dbHelper.getIngredients();

        if (ingredients.isEmpty()) {
            TextView emptyText = new TextView(this);
            emptyText.setText("Your pantry is empty. Add your first ingredient!");
            emptyText.setTextSize(16);
            emptyText.setTextColor(navy);
            emptyText.setPadding(0, 12, 0, 12);
            ingredientsLayout.addView(emptyText);
            return;
        }

        for (Ingredient ingredient : ingredients) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(16, 12, 16, 12);
            card.setBackgroundColor(cardBlue);

            TextView details = new TextView(this);
            details.setText(
                    ingredient.name +
                            "\nQuantity: " + ingredient.quantity +
                            "\nCategory: " + ingredient.category +
                            "\nExpiry: " + ingredient.expiry
            );
            details.setTextSize(16);
            details.setTextColor(navy);
            card.addView(details);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete Ingredient");
            deleteButton.setTextColor(Color.WHITE);
            deleteButton.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(blue));
            card.addView(deleteButton);

            deleteButton.setOnClickListener(v -> {
                boolean deleted = dbHelper.deleteIngredient(ingredient.id);

                if (deleted) {
                    Toast.makeText(this, "Ingredient deleted",
                            Toast.LENGTH_SHORT).show();
                    loadIngredients();
                } else {
                    Toast.makeText(this, "Could not delete ingredient",
                            Toast.LENGTH_SHORT).show();
                }
            });

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(0, 8, 0, 8);
            ingredientsLayout.addView(card, params);
        }
    }
}
