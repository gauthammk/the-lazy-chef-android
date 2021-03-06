package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class NutritionQA extends AppCompatActivity {

    Button goButton;
    EditText askMeAnything;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_qa);

        askMeAnything = findViewById(R.id.askMeAnything);

        // move to answer page on pressing goButton
        goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if query is valid
                String nutritionQuery = askMeAnything.getText().toString().trim();
                if (nutritionQuery.length() > 0){
                    Intent nutritionQAResultsPageOpener = new Intent(NutritionQA.this, NutritionQAResults.class);

                    // pass the nutrition query to the results page
                    nutritionQAResultsPageOpener.putExtra("NUTRITION_QUERY", nutritionQuery);
                    startActivity(nutritionQAResultsPageOpener);
                } else {

                    // shake box if no query is entered
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(0)
                            .playOn(findViewById(R.id.askMeAnything));
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