package com.example.thelazychef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

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

                // display network error page on request failure
                Intent networkErrorPageOpener = new Intent(NutritionQAResults.this, NetworkError.class);
                startActivity(networkErrorPageOpener);
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = Objects.requireNonNull(response.body()).string();
                    NutritionQAResults.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                System.out.println("DEBUG RESPONSE: " + myResponse);
                                // parse json response
                                JSONObject json = new JSONObject(myResponse);
                                String finalResponse = json.getString("answer");
                                nutritionQAResults.setText(finalResponse);
                            } catch (JSONException e) {

                                // answer was not received from the API, display error to the user
                                nutritionQAResults.setText("Oops! That did not work :( \n Please try rephrasing your query.");
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    nutritionQAResults.setText("An unkown error occurred. Please contact the administrator.");
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
        finish();
    }
}