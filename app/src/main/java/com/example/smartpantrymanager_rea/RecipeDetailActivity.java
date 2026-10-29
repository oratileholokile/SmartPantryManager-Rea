package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.*;

public class RecipeDetailActivity extends Activity {

    int navy = Color.rgb(23, 54, 93);
    int background = Color.rgb(240, 246, 255);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);
        layout.setBackgroundColor(background);

        setContentView(layout);

        Button backButton = new Button(this);
        backButton.setText("← Back");
        backButton.setTextColor(Color.WHITE);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(navy));
        layout.addView(backButton);

        backButton.setOnClickListener(v -> finish());

        TextView title = new TextView(this);
        title.setText(getIntent().getStringExtra("recipeName") != null
                ? getIntent().getStringExtra("recipeName")
                : "Recipe Details");
        title.setTextSize(28);
        title.setTextColor(navy);
        title.setPadding(0, 20, 0, 20);
        layout.addView(title);

        TextView details = new TextView(this);
        details.setText(
                "Ingredients and cooking instructions will appear here.");
        details.setTextColor(navy);
        details.setTextSize(16);
        layout.addView(details);
    }
}