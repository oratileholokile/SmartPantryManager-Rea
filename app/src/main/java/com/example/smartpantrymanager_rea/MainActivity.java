package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 36, 28, 28);
        root.setBackgroundColor(Color.rgb(255, 246, 250));

        TextView title = new TextView(this);
        title.setText("Smart Pantry");
        title.setTextSize(30);
        title.setTextColor(Color.rgb(135, 54, 91));
        title.setGravity(Gravity.CENTER);

        root.addView(title,
                new LinearLayout.LayoutParams(-1, 100));

        TextView subtitle = new TextView(this);
        subtitle.setText("Make something lovely with what you have.");
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);

        root.addView(subtitle,
                new LinearLayout.LayoutParams(-1, 60));

        addButton(root, "My Pantry", PantryActivity.class);
        addButton(root, "Find Recipes", RecipesActivity.class);
        addButton(root, "Settings", SettingsActivity.class);

        setContentView(root);
    }

    private void addButton(LinearLayout root, String text,
                           Class<?> destination) {
        Button button = new Button(this);
        button.setText(text);
        button.setAllCaps(false);
        button.setTextSize(17);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, 60);
        params.setMargins(0, 12, 0, 12);

        root.addView(button, params);

        button.setOnClickListener(view ->
                startActivity(new Intent(this, destination)));
    }
}