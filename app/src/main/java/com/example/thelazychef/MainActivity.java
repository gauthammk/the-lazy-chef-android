package com.example.thelazychef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button getStartedButton;
    ImageView contactUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // going to explore screen
        getStartedButton = findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check for internet connectivity
                if (isNetworkConnectionAvailable()) {
                    Intent explorePageOpener = new Intent(MainActivity.this, Explore.class);
                    startActivity(explorePageOpener);
                } else {

                    // display network error page if network connection is not available
                    Intent networkErrorPageOpener = new Intent(MainActivity.this, NetworkError.class);
                    startActivity(networkErrorPageOpener);
                }

            }
        });

        // going to contacts screen
        contactUsButton = findViewById(R.id.contactUsButton);
        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactUsPageOpener = new Intent(MainActivity.this, ContactUs.class);
                startActivity(contactUsPageOpener);
            }
        });
    }

    public boolean isNetworkConnectionAvailable () {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connected;
    }
}
