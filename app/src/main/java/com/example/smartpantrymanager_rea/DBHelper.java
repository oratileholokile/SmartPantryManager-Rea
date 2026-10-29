package com.example.smartpantrymanager_rea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SmartPantryRea.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE ingredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "quantity TEXT, " +
                        "category TEXT, " +
                        "expiry TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // Upgrade logic can be added when the database changes.
    }

    public boolean addIngredient(String name, String quantity,
                                 String category, String expiry) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        values.put("category", category);
        values.put("expiry", expiry);

        return getWritableDatabase().insert(
                "ingredients", null, values
        ) != -1;
    }

    public boolean updateIngredient(int id, String name,
                                    String quantity,
                                    String category, String expiry) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        values.put("category", category);
        values.put("expiry", expiry);

        return getWritableDatabase().update(
                "ingredients", values, "id=?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }

    public boolean deleteIngredient(int id) {
        return getWritableDatabase().delete(
                "ingredients", "id=?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }

    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT id, name, quantity, category, expiry " +
                        "FROM ingredients ORDER BY name COLLATE NOCASE",
                null
        );

        try {
            while (cursor.moveToNext()) {
                ingredients.add(new Ingredient(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            }
        } finally {
            cursor.close();
        }

        return ingredients;
    }
}