package com.example.akroc.edversity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

public class LearnAnisounds extends AppCompatActivity {


    Button buttonTest;
    LottieAnimationView pronun;
    private ViewPager mvp;
    private AnimalAdapter adapter;
    int CurrentPage;
    MediaPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_anisounds);
        buttonTest=(Button) findViewById(R.id.buttonTest1);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LearnAnisounds.this,AnimalTest.class));
            }
        });
        sound= (MediaPlayer) MediaPlayer.create(getApplicationContext(),R.raw.cat);
        pronun=(LottieAnimationView) findViewById(R.id.speak);
        pronun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pronun.playAnimation();
                pronun.setSpeed(3);
                String temp= adapter.slideHead[CurrentPage];
                String animal= ""+temp.toLowerCase();
                int s=adapter.AnimalSound[CurrentPage];
                sound= (MediaPlayer) MediaPlayer.create(getApplicationContext(),s);
                sound.start();


            }
        });

        mvp= (ViewPager) findViewById(R.id.vp5);
        adapter = new AnimalAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            CurrentPage=position;
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
