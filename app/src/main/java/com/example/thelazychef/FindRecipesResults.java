package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FindRecipesResults extends AppCompatActivity {

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
        setContentView(R.layout.activity_find_recipes_results);
    }
}