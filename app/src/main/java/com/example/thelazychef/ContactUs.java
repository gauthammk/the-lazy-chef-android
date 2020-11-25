package com.example.thelazychef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ContactUs extends AppCompatActivity {

    RelativeLayout githubButton, mailButton, twitterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // open GitHub link
        githubButton = findViewById(R.id.githubButton);
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/gauthammk/the-lazy-chef-android"));
                startActivity(browserIntent);
            }
        });

        // open mail link
        mailButton = findViewById(R.id.mailButton);
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"gautham.is17@bmsce.ac.in", "aishwaryapatil.is17@bmsce.ac.in"});
                startActivity(emailIntent);
            }
        });

        // open twitter link
        twitterButton = findViewById(R.id.twitterButton);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String tweetUrl = "https://twitter.com/intent/tweet?text=";

                // add hash tag with URL encoding
                try {
                    tweetUrl += URLEncoder.encode("Try out The Lazy Chef app today and find your recipes, quicker! \n\nhttps://github.com/gauthammk/the-lazy-chef-android \n\n#thelazychef #recipesquicker \n\n@i_amgmk @AishPatil23", StandardCharsets.UTF_8.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
                startActivity(browserIntent);
            }
        });
    }

    // on click handler for back button
    public void backButtonClickHandler(View v) {
        finish();
    }
}