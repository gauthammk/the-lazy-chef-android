package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        // going to explore screen
        Button getStartedButton = findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explorePageOpener = new Intent(MainActivity.this, ExploreActivity.class);
                startActivity(explorePageOpener);
            }
        });
    }
}
