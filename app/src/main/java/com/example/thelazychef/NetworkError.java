package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NetworkError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide action bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){
            System.out.println("Textbox could not be concealed.");
        }
        setContentView(R.layout.activity_network_error);
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        Intent mainActivityPageOpener = new Intent(NetworkError.this, MainActivity.class);
        startActivity(mainActivityPageOpener);
    }
}