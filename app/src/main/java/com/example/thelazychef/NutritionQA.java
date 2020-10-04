package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NutritionQA extends AppCompatActivity {

    Button goButton;
    EditText askMeAnything;

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
                    Intent nutritionQAResultsOpener = new Intent(NutritionQA.this, NutritionQAResults.class);

                    // pass the nutrition query to the results page
                    nutritionQAResultsOpener.putExtra("NUTRITION_QUERY", nutritionQuery);
                    startActivity(nutritionQAResultsOpener);
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
}