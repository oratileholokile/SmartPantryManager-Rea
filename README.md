# Smart Pantry Manager Rea

## 1. Introduction

Smart Pantry Manager Rea is an Android application developed using Java in Android Studio. The purpose of the application is to help users manage ingredients stored in their pantry and discover simple recipes using common ingredients.

The application has a simple, user-friendly interface with a blue colour theme.

## 2. Features

- **Home Screen:** Provides navigation to the main sections of the application.
- **My Pantry:** Allows users to add, view and delete ingredients.
- **Ingredient Information:** Stores ingredient names, quantities, categories and expiry dates.
- **Find Recipes:** Displays a selection of recipes and their ingredients.
- **Recipe Details:** Displays recipe ingredients and cooking instructions.
- **Settings:** Provides a separate settings screen.
- **Local Database:** Uses SQLite to store pantry information on the device.

## 3. Technologies Used

- Java
- Android Studio
- Android SDK
- SQLite Database
- XML layouts and Java-based user interface components
- Git and GitHub

## 4. Application Design

The application uses a light blue background, blue buttons and dark navy text. The interface is designed to be simple and easy to navigate.

The main screens are:

1. Main Activity
2. Pantry Activity
3. Recipes Activity
4. Recipe Detail Activity
5. Settings Activity

## 5. Database

The application uses a local SQLite database named `SmartPantryRea.db`.

The `ingredients` table stores the following information:

| Field | Description |
|---|---|
| id | Unique ingredient identifier |
| name | Name of the ingredient |
| quantity | Amount of the ingredient |
| category | Ingredient category |
| expiry | Expiry date |

## 6. Installation and Setup

1. Install Android Studio.
2. Clone or download this repository.
3. Open the project in Android Studio.
4. Allow Gradle to synchronise.
5. Select an Android emulator or connect an Android device.
6. Click **Run** to launch the application.

## 7. Repository

GitHub repository: https://github.com/oratileholokile/SmartPantryManager-Rea

## 8. Future Improvements

Possible future improvements include:

- Matching recipes automatically with ingredients in the pantry.
- Showing recipe match percentages and missing ingredients.
- Editing existing pantry items.
- Providing expiry-date reminders.
- Adding more recipes.
- Improving the settings screen.

## 9. Conclusion

Smart Pantry Manager Rea demonstrates how an Android application can use Java, Android Studio and SQLite to manage pantry information and display recipes. The project provides a foundation for further development of a more complete pantry management system.
