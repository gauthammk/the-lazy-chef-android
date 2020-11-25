package com.example.thelazychef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class SearchByIngredientsResults extends AppCompatActivity {

    ProgressBar loader;
    TextView recipeResults1, recipeResults2, recipeResults3, recipeResults4, recipeResults5, recipeResults6;
    TextView searchByIngredientsResultsSubheading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_ingredients_results);

        // get query from previous find recipes page
        final String ingredientsQuery = getIntent().getStringExtra("INGREDIENTS_QUERY");

        // initialise the loader
        loader = findViewById(R.id.loader);

        // initialise the text views for displaying results
        recipeResults1 = findViewById(R.id.recipeResult1);
        recipeResults2 = findViewById(R.id.recipeResult2);
        recipeResults3 = findViewById(R.id.recipeResult3);
        recipeResults4 = findViewById(R.id.recipeResult4);
        recipeResults5 = findViewById(R.id.recipeResult5);
        recipeResults6 = findViewById(R.id.recipeResult6);
        searchByIngredientsResultsSubheading = findViewById(R.id.searchByIngredientsResultsSubheading);

        // initialise the HTTP client
        OkHttpClient client = new OkHttpClient();

        // define the API endpoint
        String endpoint = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredientsQuery + "&number=6&limitLicense=true&ranking=1&ignorePantry=false&apiKey=fc6fef8c0bb04e27ad8da3843fef1602";

        // build a request object
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        // make the HTTP call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                // display network error page on request failure
                Intent networkErrorPageOpener = new Intent(SearchByIngredientsResults.this, NetworkError.class);
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
                    SearchByIngredientsResults.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                // parse json response
                                JSONArray results = new JSONArray(serverResponse);

                                if (results.length() > 0) {

                                    // remove the loader
                                    loader.setVisibility(View.GONE);

                                    // display the search query
                                    searchByIngredientsResultsSubheading.setText("Search results for " + ingredientsQuery);

                                    // make the text views visible and display the results(from the previously parsed json) for all 6 recipes

                                    // set recipe 1
                                    JSONObject result1 = results.getJSONObject(0);
                                    String recipe1 = result1.getString("title");

                                    // shorten long responses
                                    if (recipe1.length() > 90) recipe1 = recipe1.substring(0, 85) + "...";
                                    recipeResults1.setVisibility(View.VISIBLE);
                                    recipeResults1.setText(recipe1);

                                    // set recipe 2
                                    JSONObject result2 = results.getJSONObject(1);
                                    String recipe2 = result2.getString("title");

                                    // shorten long responses
                                    if (recipe2.length() > 90) recipe2 = recipe2.substring(0, 85) + "...";
                                    recipeResults2.setVisibility(View.VISIBLE);
                                    recipeResults2.setText(recipe2);

                                    // set recipe 3
                                    JSONObject result3 = results.getJSONObject(2);
                                    String recipe3 = result3.getString("title");

                                    // shorten long responses
                                    if (recipe3.length() > 90) recipe3 = recipe3.substring(0, 85) + "...";
                                    recipeResults3.setVisibility(View.VISIBLE);
                                    recipeResults3.setText(recipe3);

                                    // set recipe 4
                                    JSONObject result4 = results.getJSONObject(3);
                                    String recipe4 = result4.getString("title");

                                    // shorten long responses
                                    if (recipe4.length() > 90) recipe4 = recipe4.substring(0, 85) + "...";
                                    recipeResults4.setVisibility(View.VISIBLE);
                                    recipeResults4.setText(recipe4);

                                    // set recipe 5
                                    JSONObject result5 = results.getJSONObject(4);
                                    String recipe5 = result5.getString("title");

                                    // shorten long responses
                                    if (recipe5.length() > 90) recipe5 = recipe5.substring(0, 85) + "...";
                                    recipeResults5.setVisibility(View.VISIBLE);
                                    recipeResults5.setText(recipe5);

                                    // set recipe 6
                                    JSONObject result6 = results.getJSONObject(5);
                                    String recipe6 = result6.getString("title");

                                    // shorten long responses
                                    if (recipe6.length() > 90) recipe6 = recipe6.substring(0, 85) + "...";
                                    recipeResults6.setVisibility(View.VISIBLE);
                                    recipeResults6.setText(recipe6);
                                } else {

                                    // remove the loader
                                    loader.setVisibility(View.GONE);

                                    // answer was not received from the API, display error to the user
                                    searchByIngredientsResultsSubheading.setText("Oops! We couldn't find any results for " + ingredientsQuery + " :( \n Please try rephrasing your query.");
                                }

                            } catch (JSONException e) {

                                // remove the loader
                                loader.setVisibility(View.GONE);

                                // answer was not received from the API, display error to the user
                                searchByIngredientsResultsSubheading.setText("Oops! That did not work too well :( \n Please try rephrasing your query.");
                                e.printStackTrace();
                            }
                        }
                    });
                } else {

                    // remove the loader and set the error message
                    loader.setVisibility(View.GONE);
                    searchByIngredientsResultsSubheading.setText("An unkown error occurred. Please contact the administrator.");
                }
            }
        });
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        finish();
    }
}