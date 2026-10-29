
package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.*;

public class RecipeDetailActivity extends Activity {

    private final int navy = Color.rgb(23, 54, 93);
    private final int blue = Color.rgb(57, 118, 197);
    private final int background = Color.rgb(240, 246, 255);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);
        layout.setBackgroundColor(background);

        scrollView.addView(layout);
        setContentView(scrollView);

        // Back button
        Button backButton = new Button(this);
        backButton.setText("← Back to Recipes");
        backButton.setTextColor(Color.WHITE);
        backButton.setAllCaps(false);
        backButton.setBackgroundTintList(
                ColorStateList.valueOf(navy)
        );
        backButton.setClickable(true);
        backButton.setEnabled(true);

        layout.addView(backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        RecipeDetailActivity.this,
                        "Going back to recipes",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });

        // Recipe name
        TextView title = new TextView(this);
        String recipeName = getIntent().getStringExtra("recipeName");

        title.setText(
                recipeName != null ? recipeName : "Recipe Details"
        );
        title.setTextSize(28);
        title.setTextColor(navy);
        title.setPadding(0, 20, 0, 20);
        layout.addView(title);

        // Ingredients heading
        TextView ingredientsTitle = new TextView(this);
        ingredientsTitle.setText("Ingredients");
        ingredientsTitle.setTextSize(21);
        ingredientsTitle.setTextColor(navy);
        layout.addView(ingredientsTitle);

        // Ingredients list
        TextView ingredients = new TextView(this);
        String ingredientText =
                getIntent().getStringExtra("recipeIngredients");

        if (ingredientText != null && !ingredientText.trim().isEmpty()) {
            ingredients.setText(
                    "• " + ingredientText.replace(", ", "\n• ")
            );
        } else {
            ingredients.setText("No ingredients available.");
        }

        ingredients.setTextSize(16);
        ingredients.setTextColor(navy);
        ingredients.setPadding(0, 10, 0, 20);
        layout.addView(ingredients);

        // Cooking instructions heading
        TextView instructionsTitle = new TextView(this);
        instructionsTitle.setText("Cooking Instructions");
        instructionsTitle.setTextSize(21);
        instructionsTitle.setTextColor(navy);
        layout.addView(instructionsTitle);

        // Cooking instructions
        TextView instructions = new TextView(this);
        String instructionText =
                getIntent().getStringExtra("recipeInstructions");

        instructions.setText(
                instructionText != null
                        ? instructionText
                        : "No instructions available."
        );
        instructions.setTextSize(16);
        instructions.setTextColor(navy);
        instructions.setPadding(0, 10, 0, 20);
        layout.addView(instructions);
    }
}
