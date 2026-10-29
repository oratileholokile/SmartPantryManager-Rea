
package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.*;

public class SettingsActivity extends Activity {

    int navy = Color.rgb(23, 54, 93);
    int blue = Color.rgb(57, 118, 197);
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
        title.setText("Settings");
        title.setTextSize(28);
        title.setTextColor(navy);
        title.setPadding(0, 20, 0, 20);
        layout.addView(title);

        TextView description = new TextView(this);
        description.setText(
                "Smart Pantry Manager\n\n" +
                        "Manage your pantry and discover recipes using your ingredients.");
        description.setTextColor(navy);
        description.setTextSize(16);
        layout.addView(description);

        TextView version = new TextView(this);
        version.setText("Smart Pantry Manager Rea");
        version.setTextColor(blue);
        version.setPadding(0, 24, 0, 0);
        layout.addView(version);
    }
}
