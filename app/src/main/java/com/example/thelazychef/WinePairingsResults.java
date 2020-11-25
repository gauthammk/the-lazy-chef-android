package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class WinePairingsResults extends AppCompatActivity {

    ProgressBar loader;
    TextView winePairingsResultsSubheading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_pairings_results);

        // get query from previous find recipes page
        final String wineQuery = getIntent().getStringExtra("WINE_QUERY");

        // initialise the loader
        loader = findViewById(R.id.loader);

        // initialise the text views for displaying results
        winePairingsResultsSubheading = findViewById(R.id.winePairingsResults);

        // initialise the HTTP client
        OkHttpClient client = new OkHttpClient();

        // define the API endpoint
        String endpoint = "https://api.spoonacular.com/food/wine/dishes?wine=" + wineQuery + "&apiKey=fc6fef8c0bb04e27ad8da3843fef1602";

        // build a request object
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        // make the HTTP call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                // display network error page on request failure
                Intent networkErrorPageOpener = new Intent(WinePairingsResults.this, NetworkError.class);
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

                WinePairingsResults.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            try {
                                // parse json response
                                JSONObject json = new JSONObject(serverResponse);
                                String text = json.getString("text");

                                // remove the loader
                                loader.setVisibility(View.GONE);
                                winePairingsResultsSubheading.setText(text);

                            } catch (JSONException e) {

                                // remove the loader
                                loader.setVisibility(View.GONE);

                                // answer was not received from the API, display error to the user
                                winePairingsResultsSubheading.setText("Oops! That did not work :( \n Please try rephrasing your query.");
                                e.printStackTrace();
                            }
                        } else if (response.code() >= HTTP_BAD_REQUEST) {

                            // remove the loader
                            loader.setVisibility(View.GONE);

                            // answer was not received from the API, display error to the user
                            System.out.println("DEBUG RESPONSE: " + "BAD REQUEST");
                            winePairingsResultsSubheading.setText("Oops! That did not work :( \n Please try rephrasing your query.");
                        }
                    }
                });
            }
        });
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        finish();
    }
}