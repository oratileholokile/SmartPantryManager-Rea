package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PantryActivity extends Activity {

    DBHelper database;
    LinearLayout ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new DBHelper(this);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18, 20, 18, 18);
        root.setBackgroundColor(Color.rgb(255, 246, 250));

        Button backButton = new Button(this);
        backButton.setText("← Back");
        backButton.setAllCaps(false);
        root.addView(backButton);

        backButton.setOnClickListener(view -> finish());

        TextView title = new TextView(this);
        title.setText("My Pantry");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(135, 54, 91));
        title.setGravity(Gravity.CENTER_VERTICAL);

        root.addView(title,
                new LinearLayout.LayoutParams(-1, 60));

        Button addButton = new Button(this);
        addButton.setText("+ Add ingredient");
        addButton.setAllCaps(false);
        root.addView(addButton);

        ScrollView scrollView = new ScrollView(this);

        ingredientList = new LinearLayout(this);
        ingredientList.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(ingredientList);

        root.addView(scrollView,
                new LinearLayout.LayoutParams(-1, 0, 1));

        addButton.setOnClickListener(view -> showForm(null));

        setContentView(root);

        refreshIngredients();
    }

    private void refreshIngredients() {
        ingredientList.removeAllViews();

        ArrayList<Ingredient> items = database.getIngredients();

        if (items.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText(
                    "Your pantry is empty.\nAdd your first ingredient!"
            );
            empty.setTextSize(16);
            empty.setPadding(8, 28, 8, 28);
            ingredientList.addView(empty);
        }

        for (Ingredient item : items) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(16, 12, 16, 12);
            card.setBackgroundColor(Color.WHITE);

            LinearLayout.LayoutParams cardParams =
                    new LinearLayout.LayoutParams(-1, -2);
            cardParams.setMargins(0, 8, 0, 8);

            ingredientList.addView(card, cardParams);

            TextView name = new TextView(this);
            name.setText(item.name);
            name.setTextSize(19);
            name.setTextColor(Color.rgb(135, 54, 91));
            card.addView(name);

            TextView details = new TextView(this);
            details.setText(
                    "Quantity: " + safe(item.quantity) +
                            "\nCategory: " + safe(item.category) +
                            "\nExpiry: " + safe(item.expiry)
            );
            card.addView(details);

            LinearLayout actions = new LinearLayout(this);

            Button edit = new Button(this);
            edit.setText("Edit");
            edit.setAllCaps(false);

            Button delete = new Button(this);
            delete.setText("Delete");
            delete.setAllCaps(false);

            actions.addView(edit,
                    new LinearLayout.LayoutParams(0, -2, 1));

            actions.addView(delete,
                    new LinearLayout.LayoutParams(0, -2, 1));

            card.addView(actions);

            edit.setOnClickListener(view -> showForm(item));

            delete.setOnClickListener(view ->
                    new AlertDialog.Builder(this)
                            .setTitle("Delete ingredient")
                            .setMessage("Delete " + item.name + "?")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("Delete", (dialog, which) -> {
                                database.deleteIngredient(item.id);
                                refreshIngredients();

                                Toast.makeText(
                                        this,
                                        "Ingredient deleted",
                                        Toast.LENGTH_SHORT
                                ).show();
                            })
                            .show()
            );
        }
    }

    private String safe(String value) {
        return value == null || value.trim().isEmpty()
                ? "Not specified" : value;
    }

    private EditText createField(String hint) {
        EditText field = new EditText(this);
        field.setHint(hint);
        return field;
    }

    private void showForm(Ingredient oldItem) {
        LinearLayout form = new LinearLayout(this);
        form.setPadding(20, 8, 20, 0);
        form.setOrientation(LinearLayout.VERTICAL);

        EditText name = createField("Ingredient name");
        EditText quantity = createField("Quantity");
        EditText category = createField("Category");
        EditText expiry = createField("Expiry date (optional)");

        form.addView(name);
        form.addView(quantity);
        form.addView(category);
        form.addView(expiry);

        if (oldItem != null) {
            name.setText(oldItem.name);
            quantity.setText(oldItem.quantity);
            category.setText(oldItem.category);
            expiry.setText(oldItem.expiry);
        }

        new AlertDialog.Builder(this)
                .setTitle(oldItem == null
                        ? "Add ingredient" : "Edit ingredient")
                .setView(form)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {

                    String ingredientName =
                            name.getText().toString().trim();

                    if (ingredientName.isEmpty()) {
                        Toast.makeText(
                                this,
                                "Enter an ingredient name",
                                Toast.LENGTH_SHORT
                        ).show();
                        return;
                    }

                    boolean success;

                    if (oldItem == null) {
                        success = database.addIngredient(
                                ingredientName,
                                quantity.getText().toString().trim(),
                                category.getText().toString().trim(),
                                expiry.getText().toString().trim()
                        );
                    } else {
                        success = database.updateIngredient(
                                oldItem.id,
                                ingredientName,
                                quantity.getText().toString().trim(),
                                category.getText().toString().trim(),
                                expiry.getText().toString().trim()
                        );
                    }

                    Toast.makeText(
                            this,
                            success ? "Ingredient saved"
                                    : "Could not save ingredient",
                            Toast.LENGTH_SHORT
                    ).show();

                    refreshIngredients();
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        if (database != null) {
            database.close();
        }
        super.onDestroy();
    }
}