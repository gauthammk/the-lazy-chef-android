package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class WinePairings extends AppCompatActivity {

    Button goButton;
    TextView enterWines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                    // shake box
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(0)
                            .playOn(findViewById(R.id.enterWines));
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
        finish();
    }
}