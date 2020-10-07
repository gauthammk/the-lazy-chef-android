package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){
            System.out.println("Textbox could not be concealed.");
        }
        setContentView(R.layout.activity_explore);

        // going to search by ingredients screen
        RelativeLayout findRecipes = findViewById(R.id.findRecipes);
        findRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findRecipesPageOpener = new Intent(ExploreActivity.this, FindRecipes.class);
                startActivity(findRecipesPageOpener);
            }
        });

        // going to search by ingredients screen
        RelativeLayout searchByIngredients = findViewById(R.id.searchByIngredients);
        searchByIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchByIngredientsPageOpener = new Intent(ExploreActivity.this, SearchByIngredients.class);
                startActivity(searchByIngredientsPageOpener);
            }
        });

        // going to search by ingredients screen
        RelativeLayout winePairings = findViewById(R.id.winePairings);
        winePairings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent winePairingsPageOpener = new Intent(ExploreActivity.this, WinePairings.class);
                startActivity(winePairingsPageOpener);
            }
        });

        // going to search by ingredients screen
        RelativeLayout nutritionQA = findViewById(R.id.nutritionQA);
        nutritionQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nutritionQAPageOpener = new Intent(ExploreActivity.this, NutritionQA.class);
                startActivity(nutritionQAPageOpener);
            }
        });

        Button recipeOfTheDay = findViewById(R.id.recipeOfTheDay);
        recipeOfTheDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testPageOpener = new Intent(ExploreActivity.this, Test.class);
                startActivity(testPageOpener);
            }
        });
    }
    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        Intent mainActivityPageOpener = new Intent(ExploreActivity.this, MainActivity.class);
        startActivity(mainActivityPageOpener);
    }
}