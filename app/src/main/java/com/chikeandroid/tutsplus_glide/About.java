package com.chikeandroid.tutsplus_glide;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jrummyapps.android.animations.Technique;


public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView card = (ImageView)findViewById(R.id.cardview_image);
        Technique.BOUNCE.getComposer().duration(650).delay(0).playOn(card);
    }

    public void mapa (View view){

        try{

            // your intent here
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<45.250349>,<19.783630>?q=<45.250349>,<19.783630>(Welcome to Hell)"));
            startActivity(intent);

        } catch (ActivityNotFoundException e) {
            // show message to user
            Toast.makeText(this, "Nemate instalirane Google Mape.", Toast.LENGTH_SHORT).show();
        }


    }




}



