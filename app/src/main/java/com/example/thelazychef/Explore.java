package com.example.thelazychef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

public class Explore extends AppCompatActivity {

    ProgressBar triviaLoader;
    TextView trivia;
    RelativeLayout findRecipes, searchByIngredients, winePairings, nutritionQA;
    Button randomRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // initialise elements
        trivia = findViewById(R.id.trivia);
        triviaLoader = findViewById(R.id.triviaLoader);

        // TRIVIA FUNCTIONALITY

        // initialise the HTTP client
        final OkHttpClient client = new OkHttpClient();

        // define the API endpoint
        String triviaEndpoint = "https://api.spoonacular.com/food/trivia/random?apiKey=fc6fef8c0bb04e27ad8da3843fef1602";

        // build a request object
        Request request = new Request.Builder()
                .url(triviaEndpoint)
                .build();

        // make the HTTP call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                // display network error page on request failure
                Intent networkErrorPageOpener = new Intent(Explore.this, NetworkError.class);
                startActivity(networkErrorPageOpener);
                e.printStackTrace();
                finish();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                // log server response
                final String serverResponse = Objects.requireNonNull(response.body()).string();
                System.out.println("DEBUG RESPONSE: " + serverResponse);

                if (response.isSuccessful()) {
                    Explore.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                // parse json response
                                JSONObject json = new JSONObject(serverResponse);
                                String triviaResult = json.getString("text");

                                // remove the loader and show the result
                                triviaLoader.setVisibility(View.GONE);

                                // cut out large trivia responses and set a default value
                                if (triviaResult.length() < 192) {
                                    trivia.setText("Trivia: " + triviaResult);
                                } else {
                                    trivia.setText("Trivia: Nutmeg is a hallucinogen.");
                                }
                            } catch (JSONException e) {

                                // remove the loader
                                triviaLoader.setVisibility(View.GONE);

                                // answer was not received from the API, display error to the user
                                trivia.setText("Trivia not available at the moment :(");
                                e.printStackTrace();
                            }
                        }
                    });
                } else {

                    // remove the loader and set the error message
                    triviaLoader.setVisibility(View.GONE);
                    trivia.setText("An unkown error occurred. Please contact the administrator.");
                }
            }
        });

        // going to search by ingredients screen
        findRecipes = findViewById(R.id.findRecipes);
        findRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findRecipesPageOpener = new Intent(Explore.this, FindRecipes.class);
                startActivity(findRecipesPageOpener);
            }
        });

        // going to search by ingredients screen
        searchByIngredients = findViewById(R.id.searchByIngredients);
        searchByIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchByIngredientsPageOpener = new Intent(Explore.this, SearchByIngredients.class);
                startActivity(searchByIngredientsPageOpener);
            }
        });

        // going to search by ingredients screen
        winePairings = findViewById(R.id.winePairings);
        winePairings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent winePairingsPageOpener = new Intent(Explore.this, WinePairings.class);
                startActivity(winePairingsPageOpener);
            }
        });

        // going to search by ingredients screen
        nutritionQA = findViewById(R.id.nutritionQA);
        nutritionQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nutritionQAPageOpener = new Intent(Explore.this, NutritionQA.class);
                startActivity(nutritionQAPageOpener);
            }
        });

        randomRecipe = findViewById(R.id.randomRecipe);
        randomRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // make the HTTP call to get a random recipe
                String randomRecipeEndpoint = "https://api.spoonacular.com/recipes/random?limitLicense=false&number=1&apiKey=fc6fef8c0bb04e27ad8da3843fef1602";

                // build a request object
                Request request = new Request.Builder()
                        .url(randomRecipeEndpoint)
                        .build();

                // make the HTTP call
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                        // display network error page on request failure
                        Intent networkErrorPageOpener = new Intent(Explore.this, NetworkError.class);
                        startActivity(networkErrorPageOpener);
                        e.printStackTrace();
                        finish();
                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                        // log server response
                        final String serverResponse = Objects.requireNonNull(response.body()).string();
                        System.out.println("DEBUG RESPONSE: " + serverResponse);

                        if (response.isSuccessful()) {
                            Explore.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {

                                        // parse json response
                                        JSONObject json = new JSONObject(serverResponse);
                                        String randomRecipeUrl = json.getJSONArray("recipes").getJSONObject(0).getString("sourceUrl");

                                        // open the browser with the random recipe url
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(randomRecipeUrl));
                                        startActivity(browserIntent);

                                    } catch (JSONException e) {

                                        // answer was not received from the API, display error to the user
                                        randomRecipe.setText("Error :(");
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else {

                            // set the error message
                            randomRecipe.setText("Error :(");
                            System.out.println(Objects.requireNonNull(response.body()).string());
                        }
                    }
                });
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