package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WinePairings extends AppCompatActivity {

    Button goButton;
    TextView enterWines;

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
        setContentView(R.layout.activity_wine_pairings);

        // initialise text view
        enterWines = findViewById(R.id.enterWines);

        // move to wine pairings results page on pressing goButton
        goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if query is valid
                String wineQuery = enterWines.getText().toString().trim();
                if (wineQuery.length() > 0){
                    Intent winePairingsResultsOpener = new Intent(WinePairings.this, WinePairingsResults.class);

                    // pass the nutrition query to the results page
                    winePairingsResultsOpener.putExtra("WINE_QUERY", wineQuery);
                    startActivity(winePairingsResultsOpener);
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
        Intent explorePageOpener = new Intent(WinePairings.this, ExploreActivity.class);
        startActivity(explorePageOpener);
    }
}