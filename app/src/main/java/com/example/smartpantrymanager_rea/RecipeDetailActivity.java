package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class RecipeDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(22, 24, 22, 22);
        root.setBackgroundColor(Color.rgb(255, 246, 250));

        Button backButton = new Button(this);
        backButton.setText("← Back to recipes");
        backButton.setAllCaps(false);
        root.addView(backButton);

        backButton.setOnClickListener(view -> finish());

        ScrollView scrollView = new ScrollView(this);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(this);
        title.setText(getIntent().getStringExtra("name"));
        title.setTextSize(27);
        title.setTextColor(Color.rgb(135, 54, 91));
        content.addView(title);

        TextView ingredientHeading = new TextView(this);
        ingredientHeading.setText("\nIngredients");
        ingredientHeading.setTextSize(20);
        ingredientHeading.setTextColor(Color.rgb(135, 54, 91));
        content.addView(ingredientHeading);

        TextView ingredients = new TextView(this);
        ingredients.setText(
                getIntent().getStringExtra("ingredients")
        );
        ingredients.setTextSize(17);
        content.addView(ingredients);

        TextView instructionHeading = new TextView(this);
        instructionHeading.setText("\nInstructions");
        instructionHeading.setTextSize(20);
        instructionHeading.setTextColor(Color.rgb(135, 54, 91));
        content.addView(instructionHeading);

        TextView instructions = new TextView(this);
        instructions.setText(
                getIntent().getStringExtra("instructions")
        );
        instructions.setTextSize(17);
        content.addView(instructions);

        scrollView.addView(content);

        root.addView(scrollView,
                new LinearLayout.LayoutParams(-1, 0, 1));

        setContentView(root);
    }
}