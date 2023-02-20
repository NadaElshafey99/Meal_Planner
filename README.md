# Meal_Planner
##Team member

 Nada Elshafey:https://github.com/NadaElshafey99
 
 Mohamed Essam:https://github.com/MohamedEssamAbdu-allah
 
## Introduction
A Meal Planner application built in Java, RxJava and Use Room for local storage and Firebase for authentication/backup and syncronization that can:

• User can view an arbitrary meal for inspiration.

• Show list of ingredients, categories and countries.

• User can choose between them.

• User can view popular meals in each category, country.

• User can view meals of selected ingredients.

• User can add a meal to favorite or remove one from it.

• User can search for meals by meal's name, ingredients, categories and countries.

• User can create weekly planner meals (breakfast , launch and dinner).

• User can show meals of the current week. (Plan meals)

• User can synchronize/backup his data to be able to view them again upon login.

• User can view his favorite meals and the plan for the current week if there is no network.

• User can choose a meal  to view:

    1. Name of the meal
		
    2. Image of the meal
		
    3. The origin country
		
    4. Ingredients (preferred to be by image as possible)
		
    5. Steps
		
    6. Video to be embedded in the application (not a URL)
		
    7. Button to add meal to favorite or remove meal from favorites


# Application Walk Through
The meal planner application has four options to choose from:

•Home screen.

•Search.

•Favourite meals.

•Weekly Planner.

## Home screen


# Other Information
The application saves the state of favorite and weekly planner. The user can  close the application and know that when the application is opened again the program will be in the same state it was before it was closed.

• Provide simple login and sign up(Google)

• Once the registered is successful he doesn’t need to login again. (Local SharedPreferences)

• User can choose to be a guest, and he can only view categories, use search and view meal of the day.
