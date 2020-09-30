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
                Intent explorePageOpener = new Intent(ExploreActivity.this, FindRecipes.class);
                startActivity(explorePageOpener);
            }
        });

        // going to search by ingredients screen
        RelativeLayout searchByIngredients = findViewById(R.id.searchByIngredients);
        searchByIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explorePageOpener = new Intent(ExploreActivity.this, SearchByIngredients.class);
                startActivity(explorePageOpener);
            }
        });

        // going to search by ingredients screen
        RelativeLayout winePairings = findViewById(R.id.winePairings);
        winePairings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explorePageOpener = new Intent(ExploreActivity.this, WinePairings.class);
                startActivity(explorePageOpener);
            }
        });

        // going to search by ingredients screen
        RelativeLayout nutritionQA = findViewById(R.id.nutritionQA);
        nutritionQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explorePageOpener = new Intent(ExploreActivity.this, NutritionQA.class);
                startActivity(explorePageOpener);
            }
        });
    }
    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }
}