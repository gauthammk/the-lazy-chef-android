package com.example.thelazychef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindRecipesResults extends AppCompatActivity {

    ProgressBar loader;
    TextView recipeResults1, recipeResults2, recipeResults3, recipeResults4, recipeResults5, recipeResults6;
    TextView findRecipesResultsSubheading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipes_results);

        // get query from previous find recipes page
        final String dishQuery = getIntent().getStringExtra("DISH_QUERY");

        // initialise the loader
        loader = findViewById(R.id.loader);

        // initialise the text views for displaying results
        recipeResults1 = findViewById(R.id.recipeResult1);
        recipeResults2 = findViewById(R.id.recipeResult2);
        recipeResults3 = findViewById(R.id.recipeResult3);
        recipeResults4 = findViewById(R.id.recipeResult4);
        recipeResults5 = findViewById(R.id.recipeResult5);
        recipeResults6 = findViewById(R.id.recipeResult6);
        findRecipesResultsSubheading = findViewById(R.id.findRecipesResultsSubheading);

        // initialise the HTTP client
        OkHttpClient client = new OkHttpClient();

        // define the API endpoint
        String endpoint = "https://api.spoonacular.com/recipes/search?query=" + dishQuery + "&number=6&instructionsRequired=true&apiKey=fc6fef8c0bb04e27ad8da3843fef1602";

        // build a request object
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        // make the HTTP call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                // display network error page on request failure
                Intent networkErrorPageOpener = new Intent(FindRecipesResults.this, NetworkError.class);
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
                    FindRecipesResults.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                // parse json response
                                JSONObject json = new JSONObject(serverResponse);
                                JSONArray results = json.getJSONArray("results");

                                if (results.length() > 0) {

                                    // remove the loader
                                    loader.setVisibility(View.GONE);

                                    // display the search query
                                    findRecipesResultsSubheading.setText("Search results for " + dishQuery);

                                    // MAIN DISPLAY : make the text views visible and display the results(from the previously parsed json) for all 6 recipes

                                    // set recipe 1
                                    JSONObject result1 = results.getJSONObject(0);
                                    String recipe1 = result1.getString("title");
                                    final String recipeLink1 = result1.getString("sourceUrl");

                                    // shorten long responses
                                    if (recipe1.length() > 90) recipe1 = recipe1.substring(0, 85) + "...";
                                    recipeResults1.setVisibility(View.VISIBLE);
                                    recipeResults1.setText(recipe1);

                                    // open recipe link on click
                                    recipeResults1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink1));
                                            startActivity(browserIntent);
                                        }
                                    });


                                    // set recipe 2
                                    JSONObject result2 = results.getJSONObject(1);
                                    String recipe2 = result2.getString("title");
                                    final String recipeLink2 = result2.getString("sourceUrl");

                                    // shorten long responses
                                    if (recipe2.length() > 90) recipe2 = recipe2.substring(0, 85) + "...";
                                    recipeResults2.setVisibility(View.VISIBLE);
                                    recipeResults2.setText(recipe2);

                                    // open recipe link on click
                                    recipeResults2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink2));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 3
                                    JSONObject result3 = results.getJSONObject(2);
                                    String recipe3 = result3.getString("title");
                                    final String recipeLink3 = result3.getString("sourceUrl");

                                    // shorten long responses
                                    if (recipe3.length() > 90) recipe3 = recipe3.substring(0, 85) + "...";
                                    recipeResults3.setVisibility(View.VISIBLE);
                                    recipeResults3.setText(recipe3);

                                    // open recipe link on click
                                    recipeResults3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink3));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 4
                                    JSONObject result4 = results.getJSONObject(3);
                                    String recipe4 = result4.getString("title");
                                    final String recipeLink4 = result4.getString("sourceUrl");

                                    // shorten long responses
                                    if (recipe4.length() > 90) recipe4 = recipe4.substring(0, 85) + "...";
                                    recipeResults4.setVisibility(View.VISIBLE);
                                    recipeResults4.setText(recipe4);

                                    // open recipe link on click
                                    recipeResults4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink4));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 5
                                    JSONObject result5 = results.getJSONObject(4);
                                    String recipe5 = result5.getString("title");
                                    final String recipeLink5 = result5.getString("sourceUrl");

                                    // shorten long responses
                                    if (recipe5.length() > 90) recipe5 = recipe5.substring(0, 85) + "...";
                                    recipeResults5.setVisibility(View.VISIBLE);
                                    recipeResults5.setText(recipe5);

                                    // open recipe link on click
                                    recipeResults5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink5));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 6
                                    JSONObject result6 = results.getJSONObject(5);
                                    String recipe6 = result6.getString("title");
                                    final String recipeLink6 = result6.getString("sourceUrl");

                                    // shorten long responses
                                    if (recipe6.length() > 90) recipe6 = recipe6.substring(0, 85) + "...";
                                    recipeResults6.setVisibility(View.VISIBLE);
                                    recipeResults6.setText(recipe6);

                                    // open recipe link on click
                                    recipeResults6.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeLink6));
                                            startActivity(browserIntent);
                                        }
                                    });
                                } else {

                                    // remove the loader
                                    loader.setVisibility(View.GONE);

                                    // answer was not received from the API, display error to the user
                                    findRecipesResultsSubheading.setText("Oops! We couldn't find any results for " + dishQuery + " :( \n Please try rephrasing your query.");
                                }

                            } catch (JSONException e) {

                                // remove the loader
                                loader.setVisibility(View.GONE);

                                // answer was not received from the API, display error to the user
                                findRecipesResultsSubheading.setText("Oops! That did not work :( \n Please try rephrasing your query.");
                                e.printStackTrace();
                            }
                        }
                    });
                } else {

                    // remove the loader and set the error message
                    loader.setVisibility(View.GONE);
                    findRecipesResultsSubheading.setText("An unkown error occurred. Please contact the administrator.");
                }
            }
        });


    }

    // on click handler for back button
    public void backButtonClickHandler(View v) { finish(); }
}