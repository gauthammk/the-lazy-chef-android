package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_explore);
    }
    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }
}