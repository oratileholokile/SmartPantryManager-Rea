package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pantryButton = findViewById(R.id.btnPantry);
        Button recipesButton = findViewById(R.id.btnRecipes);
        Button settingsButton = findViewById(R.id.btnSettings);

        pantryButton.setOnClickListener(view ->
                startActivity(new Intent(this, PantryActivity.class)));

        recipesButton.setOnClickListener(view ->
                startActivity(new Intent(this, RecipesActivity.class)));

        settingsButton.setOnClickListener(view ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }
}
