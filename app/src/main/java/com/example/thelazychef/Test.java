package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

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

public class Test extends AppCompatActivity {

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
        setContentView(R.layout.test);
        System.out.println("ATTEMPTING API CALL!");
        final TextView result = findViewById(R.id.result);

        // make http call
        OkHttpClient client = new OkHttpClient();
        String url = "https://www.foaas.com/programmer/GMK";
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Test.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(myResponse);
                                String message = json.getString("message");
                                String subtitle = json.getString("subtitle");
                                String finalResponse = message + subtitle;
                                result.setText(finalResponse);
                                System.out.println("RESPONSE: \n" + finalResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Functionality not added", Toast.LENGTH_SHORT).show();
    }
}