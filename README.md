# Meal_Planner
## Team member

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




## Home screen


![meal recommendation](https://user-images.githubusercontent.com/121426534/220184685-1ce1d5af-4ef4-4c26-91c1-e99c323d980e.jpeg)



## Search.


![search](https://user-images.githubusercontent.com/121426534/220184858-2718158c-596e-4ada-af60-cd8a2d93e8bb.jpeg)




### search by ingredients



![failed to add ingredients](https://user-images.githubusercontent.com/121426534/220186849-a66f42f7-8d63-4eaa-b712-41973810e655.jpeg)




![search by ingredients](https://user-images.githubusercontent.com/121426534/220185012-d5436a98-7304-4315-8af7-b8110b35eba3.jpeg)




### search by categories



![list of categories](https://user-images.githubusercontent.com/121426534/220185110-837a27a2-ee94-4eda-8e41-b10a8fef639e.jpeg)




![search result](https://user-images.githubusercontent.com/121426534/220185308-2e02a214-9fa4-4dd4-ab5e-368d4d648392.jpeg)



### search by countries



![list of countries](https://user-images.githubusercontent.com/121426534/220185235-361be65a-6953-4077-bedd-aadc9bc68022.jpeg)





#### result of Egyptian country (show popular meals)




![meal result of egyptian](https://user-images.githubusercontent.com/121426534/220187085-4ff6d76a-805b-48fe-9d6a-c4c9d2c7be75.jpeg)






### search by meal's name



![search bar](https://user-images.githubusercontent.com/121426534/220185448-4a5556c2-4fea-4e8d-b4b4-56f0be316987.jpeg)






## Favourite meals.


![Fav](https://user-images.githubusercontent.com/121426534/220186090-f26ca585-3019-4c2d-9535-5b186a3c4c39.jpeg)






## Weekly Planner.


![meal add](https://user-images.githubusercontent.com/121426534/220186159-9dbb7e1a-56ff-4da2-bca5-0a9215a3496b.jpeg)




![meal planner](https://user-images.githubusercontent.com/121426534/220186197-377569f2-d22f-4a4c-9a70-4ad42c315f55.jpeg)







## Meal details



![meal details1](https://user-images.githubusercontent.com/121426534/220186352-4ef21126-1fe1-482b-8047-e1ba3db4e6e7.jpeg)




![meal details2](https://user-images.githubusercontent.com/121426534/220186370-1d8bf0c7-5418-4feb-8629-4a9d1ff4f0ea.jpeg)






## network is offline


### user can show favorite and weekly plan meals



![fav network off](https://user-images.githubusercontent.com/121426534/220187420-12859064-2398-4f76-96f9-acf5e481c449.jpeg)




![weekly plan network off](https://user-images.githubusercontent.com/121426534/220187889-c01fdb4c-b235-4f6a-95eb-a9f4c8dea48e.jpeg)





### user cannot show recommendation meal or search or add meal plan



![check network](https://user-images.githubusercontent.com/121426534/220188776-5e65c0ab-9949-4d23-9261-44a5e24cd335.jpeg)




![network](https://user-images.githubusercontent.com/121426534/220187541-45723963-ff5b-4d52-b72d-58f5b4fb4768.jpeg)





## user as a guest


•user can show recommendation meal ,meal details and list of ingredients, categoreis and countries





### user cannot add meal to favorite or to weekly plan



![guest](https://user-images.githubusercontent.com/121426534/220188195-642b7047-da02-4dc9-a0a9-4203c4300a0b.jpeg)



![you cannot add meal to fav](https://user-images.githubusercontent.com/121426534/220188948-4d3c96ef-5a86-4e42-8d69-2babbb9caef5.jpeg)




![you cannot add meal to plan](https://user-images.githubusercontent.com/121426534/220189076-ac089e88-e96e-4ee9-a602-6a5ce16e86ae.jpeg)






# Other Information
The application saves the state of favorite and weekly planner. The user can  close the application and know that when the application is opened again the program will be in the same state it was before it was closed.

• Provide simple login and sign up(Google)

• Once the registered is successful he doesn’t need to login again. (Local SharedPreferences)

• User can choose to be a guest, and he can only view categories, use search and view meal of the day.
