
package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class RecipesActivity extends Activity {

    private final int navy = Color.rgb(23, 54, 93);
    private final int blue = Color.rgb(57, 118, 197);
    private final int background = Color.rgb(240, 246, 255);

    private void goHome() {
        Intent intent = new Intent(RecipesActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);
        layout.setBackgroundColor(background);

        scrollView.addView(layout);
        setContentView(scrollView);

        Button backButton = new Button(this);
        backButton.setText("← BACK TO HOME");
        backButton.setTextColor(Color.WHITE);
        backButton.setTextSize(16);
        backButton.setAllCaps(false);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(navy));

        LinearLayout.LayoutParams backParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        backParams.bottomMargin = 20;
        layout.addView(backButton, backParams);

        backButton.setOnClickListener(v -> goHome());

        TextView title = new TextView(this);
        title.setText("Find Recipes");
        title.setTextSize(28);
        title.setTextColor(navy);
        title.setPadding(0, 10, 0, 15);
        layout.addView(title);

        String[] names = {
                "Omelette", "Pasta", "Vegetable Fried Rice",
                "Pancakes", "Chicken Sandwich", "Tomato Soup",
                "Potato Salad", "Fruit Salad", "Cheese Toast",
                "Vegetable Stir-Fry"
        };

        String[][] ingredients = {
                {"Eggs", "Salt", "Pepper"},
                {"Pasta", "Tomato", "Onion"},
                {"Rice", "Carrot", "Peas"},
                {"Flour", "Milk", "Eggs"},
                {"Bread", "Chicken", "Lettuce"},
                {"Tomato", "Onion", "Salt"},
                {"Potato", "Mayonnaise", "Salt"},
                {"Apple", "Banana", "Orange"},
                {"Bread", "Cheese", "Butter"},
                {"Carrot", "Broccoli", "Onion"}
        };

        String[] instructions = {
                "Beat eggs with salt and pepper. Cook in a lightly oiled pan until set.",
                "Cook pasta. Prepare tomato and onion sauce, then mix with pasta.",
                "Cook rice. Fry carrot and peas, add rice, and stir together.",
                "Mix flour, milk and eggs. Cook small portions in a lightly greased pan.",
                "Cook chicken thoroughly. Add chicken and lettuce to bread.",
                "Cook chopped tomatoes and onion until soft. Season with salt.",
                "Boil potatoes until tender. Cool and mix with mayonnaise and salt.",
                "Wash and chop the fruit. Mix together and serve chilled.",
                "Butter bread, add cheese, and toast until golden and melted.",
                "Chop vegetables and stir-fry in a little oil until tender."
        };

        for (int i = 0; i < names.length; i++) {
            final int position = i;

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(16, 16, 16, 16);
            card.setBackgroundColor(Color.rgb(220, 235, 255));

            TextView recipeName = new TextView(this);
            recipeName.setText(names[i]);
            recipeName.setTextSize(20);
            recipeName.setTextColor(navy);
            card.addView(recipeName);

            TextView recipeIngredients = new TextView(this);
            recipeIngredients.setText(
                    "Ingredients: " + String.join(", ", ingredients[i]));
            recipeIngredients.setTextColor(navy);
            recipeIngredients.setPadding(0, 8, 0, 8);
            card.addView(recipeIngredients);

            Button viewButton = new Button(this);
            viewButton.setText("View Recipe");
            viewButton.setTextColor(Color.WHITE);
            viewButton.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(blue));
            card.addView(viewButton);

            viewButton.setOnClickListener(v -> {
                Intent intent = new Intent(
                        RecipesActivity.this,
                        RecipeDetailActivity.class);

                intent.putExtra("recipeName", names[position]);
                intent.putExtra(
                        "recipeIngredients",
                        String.join(", ", ingredients[position]));
                intent.putExtra(
                        "recipeInstructions", instructions[position]);

                startActivity(intent);
            });

            LinearLayout.LayoutParams cardParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            cardParams.bottomMargin = 16;
            layout.addView(card, cardParams);
        }
    }

    @Override
    public void onBackPressed() {
        goHome();
    }
}
