package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NutritionQAResults extends AppCompatActivity {

    TextView nutritionQAResults;

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
        setContentView(R.layout.activity_nutrition_qa_results);

        // get query from previous nutritionQA page
        final String nutritionQuery = getIntent().getStringExtra("NUTRITION_QUERY");

        // initialise the text view for displaying results
        nutritionQAResults = findViewById(R.id.nutritionQAResults);

        // initialise the HTTP client
        OkHttpClient client = new OkHttpClient();

        // define the API endpoint
        String endpoint = "https://api.spoonacular.com/recipes/quickAnswer?q=" + nutritionQuery + "&apiKey=fc6fef8c0bb04e27ad8da3843fef1602";

        // build a request object
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        // make the HTTP call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    NutritionQAResults.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                // parse json response
                                JSONObject json = new JSONObject(myResponse);
                                String finalResponse = json.getString("answer");
                                nutritionQAResults.setText(finalResponse);
                                System.out.println("RESPONSE for " + nutritionQuery + ": \n" + finalResponse);
                            } catch (JSONException e) {

                                // answer was not received from the API, display error to the user
                                nutritionQAResults.setText("Oops! That did not work :( \n Please try rephrasing your query.");
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

    }

    public void displayFunctionalityNotAdded(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        Intent nutritionQAPageOpener = new Intent(NutritionQAResults.this, NutritionQA.class);
        startActivity(nutritionQAPageOpener);
    }
}