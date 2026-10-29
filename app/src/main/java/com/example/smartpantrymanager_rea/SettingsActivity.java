package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 24, 24, 24);
        root.setBackgroundColor(Color.rgb(255, 246, 250));

        Button backButton = new Button(this);
        backButton.setText("← Back");
        backButton.setAllCaps(false);
        root.addView(backButton);

        backButton.setOnClickListener(view -> finish());

        TextView title = new TextView(this);
        title.setText("Settings");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(135, 54, 91));
        root.addView(title);

        TextView about = new TextView(this);
        about.setText(
                "\nSmart Pantry Manager\n\n" +
                        "Keep track of ingredients and discover recipes " +
                        "using what you already have.\n\n" +
                        "Your pantry data is saved locally on this device."
        );
        about.setTextSize(17);
        root.addView(about);

        Button clear = new Button(this);
        clear.setText("Clear all pantry ingredients");
        clear.setAllCaps(false);
        root.addView(clear);

        clear.setOnClickListener(view ->
                new AlertDialog.Builder(this)
                        .setTitle("Clear pantry?")
                        .setMessage(
                                "This removes all saved ingredients."
                        )
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Clear", (dialog, which) -> {

                            DBHelper dbHelper = new DBHelper(this);

                            dbHelper.getWritableDatabase()
                                    .delete("ingredients", null, null);

                            dbHelper.close();

                            Toast.makeText(
                                    this,
                                    "Pantry cleared",
                                    Toast.LENGTH_SHORT
                            ).show();
                        })
                        .show()
        );

        setContentView(root);
    }
}