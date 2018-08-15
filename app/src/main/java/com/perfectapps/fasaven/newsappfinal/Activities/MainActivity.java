package com.perfectapps.fasaven.newsappfinal.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.perfectapps.fasaven.newsappfinal.R;

public class MainActivity extends AppCompatActivity {


    private static int TIME_OUT_SPLASH = 5500;
    public void fade(){

     //   imageView.animate().alpha(1f).setDuration(2000);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(MainActivity.this, MainScreenTabbedLayOut.class);
                startActivity(i);
                finish();



            }
        }, TIME_OUT_SPLASH);

        //TODO(1) make splash screen

    }
}
