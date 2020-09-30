package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SearchByIngredients extends AppCompatActivity {

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
        setContentView(R.layout.activity_search_by_ingredients);
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }
}