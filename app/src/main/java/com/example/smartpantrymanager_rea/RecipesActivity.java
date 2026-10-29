package com.example.smartpantrymanager_rea;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class RecipesActivity extends Activity {

    DBHelper database;
    ArrayList<Recipe> recipes = new ArrayList<>();
    LinearLayout recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new DBHelper(this);
        loadRecipes();

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
        title.setText("Recipe Ideas");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(135, 54, 91));
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText(
                "Recipes that match ingredients in your pantry."
        );
        root.addView(subtitle);

        ScrollView scroll = new ScrollView(this);

        recipeList = new LinearLayout(this);
        recipeList.setOrientation(LinearLayout.VERTICAL);

        scroll.addView(recipeList);

        root.addView(scroll,
                new LinearLayout.LayoutParams(-1, 0, 1));

        setContentView(root);

        displayRecipes();
    }

    private void loadRecipes() {
        recipes.add(new Recipe(
                "Cheese Omelette",
                "eggs, cheese, salt, pepper",
                "1. Beat the eggs. 2. Add salt and pepper. " +
                        "3. Cook in a pan and add cheese before folding."
        ));

        recipes.add(new Recipe(
                "Tomato Pasta",
                "pasta, tomato, onion, garlic, salt",
                "1. Boil the pasta. 2. Cook the chopped onion, " +
                        "garlic and tomato. 3. Mix the sauce with pasta."
        ));

        recipes.add(new Recipe(
                "Banana Pancakes",
                "banana, eggs, flour, milk",
                "1. Mash the banana. 2. Mix with eggs, flour " +
                        "and milk. 3. Fry until golden."
        ));

        recipes.add(new Recipe(
                "Chicken Rice Bowl",
                "chicken, rice, onion, oil, salt",
                "1. Cook the rice. 2. Cook chicken and onion " +
                        "thoroughly. 3. Serve together."
        ));

        recipes.add(new Recipe(
                "Garden Salad",
                "lettuce, tomato, cucumber, carrot",
                "1. Wash and chop the vegetables. " +
                        "2. Mix in a bowl. 3. Add dressing."
        ));

        recipes.add(new Recipe(
                "Garlic Toast",
                "bread, butter, garlic",
                "1. Mix softened butter with crushed garlic. " +
                        "2. Spread on bread. 3. Toast until golden."
        ));

        recipes.add(new Recipe(
                "Creamy Oats",
                "oats, milk, banana, honey",
                "1. Simmer oats with milk. 2. Stir until soft. " +
                        "3. Add banana and honey."
        ));

        recipes.add(new Recipe(
                "Vegetable Fried Rice",
                "rice, carrot, peas, egg, soy sauce",
                "1. Scramble the egg. 2. Cook the vegetables. " +
                        "3. Add cooked rice and soy sauce."
        ));

        recipes.add(new Recipe(
                "Yoghurt Fruit Bowl",
                "yoghurt, banana, apple, honey",
                "1. Slice the fruit. 2. Add yoghurt. " +
                        "3. Drizzle with honey."
        ));

        recipes.add(new Recipe(
                "Cheese Sandwich",
                "bread, cheese, tomato, butter",
                "1. Butter the bread. 2. Add cheese and tomato. " +
                        "3. Toast or serve fresh."
        ));
    }

    private int matchCount(Recipe recipe) {
        int count = 0;

        ArrayList<Ingredient> pantry = database.getIngredients();

        for (String required : recipe.getIngredientList()) {
            String wanted = required.trim();

            for (Ingredient item : pantry) {
                if (item.name != null &&
                        (item.name.toLowerCase().contains(wanted)
                                || wanted.contains(item.name.toLowerCase()))) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    private void displayRecipes() {
        recipeList.removeAllViews();

        ArrayList<Recipe> sorted = new ArrayList<>(recipes);

        Collections.sort(sorted, (a, b) ->
                Integer.compare(matchCount(b), matchCount(a)));

        for (Recipe recipe : sorted) {
            Button button = new Button(this);
            button.setAllCaps(false);
            button.setGravity(
                    Gravity.START | Gravity.CENTER_VERTICAL
            );

            button.setText(
                    recipe.name + "\nMatched ingredients: " +
                            matchCount(recipe) + " / " +
                            recipe.getIngredientList().size()
            );

            recipeList.addView(button);

            button.setOnClickListener(view -> {
                Intent intent = new Intent(
                        this, RecipeDetailActivity.class
                );

                intent.putExtra("name", recipe.name);
                intent.putExtra("ingredients", recipe.ingredients);
                intent.putExtra("instructions", recipe.instructions);

                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (database != null && recipeList != null) {
            displayRecipes();
        }
    }

    @Override
    protected void onDestroy() {
        if (database != null) {
            database.close();
        }
        super.onDestroy();
    }
}