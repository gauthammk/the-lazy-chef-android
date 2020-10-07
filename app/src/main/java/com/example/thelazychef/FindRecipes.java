package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FindRecipes extends AppCompatActivity {

    RelativeLayout searchButton;
    TextView enterDish;

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
        setContentView(R.layout.activity_find_recipes);

        // initialise text view
        enterDish = findViewById(R.id.enterDish);

        // move to wine pairings results page on pressing goButton
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if query is valid
                String dishQuery = enterDish.getText().toString().trim();
                if (dishQuery.length() > 0){
                    Intent findRecipesResultsOpener = new Intent(FindRecipes.this, FindRecipesResults.class);

                    // pass the nutrition query to the results page
                    findRecipesResultsOpener.putExtra("DISH_QUERY", dishQuery);
                    startActivity(findRecipesResultsOpener);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid query!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        Intent explorePageOpener = new Intent(FindRecipes.this, ExploreActivity.class);
        startActivity(explorePageOpener);
    }
}