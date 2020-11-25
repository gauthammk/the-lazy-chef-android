package com.example.thelazychef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class AdvancedSearchResults extends AppCompatActivity {

    ProgressBar loader;
    TextView advResults1, advResults2, advResults3, advResults4, advResults5, advResults6;
    TextView advancedSearchResultsSubheading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search_results);

        // get query from previous advanced search page
        final String dishQuery = getIntent().getStringExtra("DISH_QUERY");
        String cuisineQuery = getIntent().getStringExtra("CUISINE_QUERY");
        String dietQuery = getIntent().getStringExtra("DIET_QUERY");
        String excludeIngredientsQuery = getIntent().getStringExtra("EXCLUDE_INGREDIENTS_QUERY");
        String intolerancesQuery = getIntent().getStringExtra("INTOLERANCES_QUERY");

        // initialise the loader
        loader = findViewById(R.id.loader);

        // initialise the text views for displaying results
        advResults1 = findViewById(R.id.advResult1);
        advResults2 = findViewById(R.id.advResult2);
        advResults3 = findViewById(R.id.advResult3);
        advResults4 = findViewById(R.id.advResult4);
        advResults5 = findViewById(R.id.advResult5);
        advResults6 = findViewById(R.id.advResult6);
        advancedSearchResultsSubheading = findViewById(R.id.advancedSearchResultsSubheading);

        // initialise the HTTP client
        OkHttpClient client = new OkHttpClient();

        // define the API endpoint
        String endpoint = "https://api.spoonacular.com/recipes/search?query=" + dishQuery + "&cuisine=" + cuisineQuery + "&diet=" + dietQuery + "&excludeIngredients=" + excludeIngredientsQuery + "&intolerances=" + intolerancesQuery+ "&number=6&instructionsRequired=true&apiKey=fc6fef8c0bb04e27ad8da3843fef1602";
        System.out.println("ENDPOINT DEBUG RESPONSE: " + endpoint);

        // build a request object
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        // make the HTTP call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                // display network error page on request failure
                Intent networkErrorPageOpener = new Intent(AdvancedSearchResults.this, NetworkError.class);
                startActivity(networkErrorPageOpener);
                e.printStackTrace();
                finish();
            }
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                // log server response
                final String serverResponse = Objects.requireNonNull(response.body()).string();
                System.out.println("DEBUG RESPONSE: " + serverResponse);

                if (response.isSuccessful()) {
                    AdvancedSearchResults.this.runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
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
                                    advancedSearchResultsSubheading.setText("Search results for " + dishQuery);

                                    // MAIN DISPLAY : make the text views visible and display the results(from the previously parsed json) for all 6 Advsearch

                                    // set advSearch result 1
                                    JSONObject result1 = results.getJSONObject(0);
                                    String advSearch1 = result1.getString("title");
                                    final String advSearchLink1 = result1.getString("sourceUrl");

                                    // shorten long responses
                                    if (advSearch1.length() > 90) advSearch1 = advSearch1.substring(0, 85) + "...";
                                    advResults1.setVisibility(View.VISIBLE);
                                    advResults1.setText(advSearch1);

                                    // open recipe link on click
                                    advResults1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advSearchLink1));
                                            startActivity(browserIntent);
                                        }
                                    });


                                    // set recipe 2
                                    JSONObject result2 = results.getJSONObject(1);
                                    String advSearch2 = result2.getString("title");
                                    final String advSearchLink2 = result2.getString("sourceUrl");

                                    // shorten long responses
                                    if (advSearch2.length() > 90) advSearch2 = advSearch2.substring(0, 85) + "...";
                                    advResults2.setVisibility(View.VISIBLE);
                                    advResults2.setText(advSearch2);

                                    // open recipe link on click
                                    advResults2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advSearchLink2));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 3
                                    JSONObject result3 = results.getJSONObject(2);
                                    String advSearch3 = result3.getString("title");
                                    final String advSearchLink3 = result3.getString("sourceUrl");

                                    // shorten long responses
                                    if (advSearch3.length() > 90) advSearch3 = advSearch3.substring(0, 85) + "...";
                                    advResults3.setVisibility(View.VISIBLE);
                                    advResults3.setText(advSearch3);

                                    // open recipe link on click
                                    advResults3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advSearchLink3));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 4
                                    JSONObject result4 = results.getJSONObject(3);
                                    String advSearch4 = result4.getString("title");
                                    final String advSearchLink4 = result4.getString("sourceUrl");

                                    // shorten long responses
                                    if (advSearch4.length() > 90) advSearch4 = advSearch4.substring(0, 85) + "...";
                                    advResults4.setVisibility(View.VISIBLE);
                                    advResults4.setText(advSearch4);

                                    // open recipe link on click
                                    advResults4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advSearchLink4));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 5
                                    JSONObject result5 = results.getJSONObject(4);
                                    String advSearch5 = result5.getString("title");
                                    final String advSearchLink5 = result5.getString("sourceUrl");

                                    // shorten long responses
                                    if (advSearch5.length() > 90) advSearch5 = advSearch5.substring(0, 85) + "...";
                                    advResults5.setVisibility(View.VISIBLE);
                                    advResults5.setText(advSearch5);

                                    // open recipe link on click
                                    advResults5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advSearchLink5));
                                            startActivity(browserIntent);
                                        }
                                    });

                                    // set recipe 6
                                    JSONObject result6 = results.getJSONObject(5);
                                    String advSearch6 = result6.getString("title");
                                    final String advSearchLink6 = result6.getString("sourceUrl");

                                    // shorten long responses
                                    if (advSearch6.length() > 90) advSearch6 = advSearch6.substring(0, 85) + "...";
                                    advResults6.setVisibility(View.VISIBLE);
                                    advResults6.setText(advSearch6);

                                    // open recipe link on click
                                    advResults6.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advSearchLink6));
                                            startActivity(browserIntent);
                                        }
                                    });
                                } else {

                                    // remove the loader
                                    loader.setVisibility(View.GONE);

                                    // answer was not received from the API, display error to the user
                                    advancedSearchResultsSubheading.setText("Oops!We couldn't find any results :( \n Please try rephrasing your query.");
                                }

                            } catch (JSONException e) {

                                // remove the loader
                                loader.setVisibility(View.GONE);

                                // answer was not received from the API, display error to the user
                                advancedSearchResultsSubheading.setText("Oops! That did not work :( \n Please try rephrasing your query.");
                                e.printStackTrace();
                            }
                        }
                    });
                } else {

                    // remove the loader and set the error message
                    loader.setVisibility(View.GONE);
                    advancedSearchResultsSubheading.setText("An unknown error occurred. Please contact the administrator.");
                }
            }
        });


    }

    // on click handler for back button
    public void backButtonClickHandler(View v) { finish(); }
}
