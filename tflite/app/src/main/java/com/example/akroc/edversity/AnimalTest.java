package com.example.akroc.edversity;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class AnimalTest extends AppCompatActivity implements Animation.AnimationListener {

    LottieAnimationView playButton;
    LottieAnimationView visualization;
    Animation slideLeft;
    Animation slidecenter;
    private ViewPager mvp;
    private AnimalTestAdapter adapter;
    int CurrentPage;
    MediaPlayer sound;
    final String TAG="yowag";
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_test);
        playButton = (LottieAnimationView) findViewById(R.id.playsound);
        visualization= (LottieAnimationView) findViewById(R.id.synthes);
        slideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
        slideLeft.setAnimationListener(AnimalTest.this);
        score =(TextView) findViewById(R.id.score);
        sound= new MediaPlayer();


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playButton.getX()==461.0){
                playButton.startAnimation(slideLeft);
                sound= (MediaPlayer) MediaPlayer.create(getApplicationContext(),adapter.animalSound[adapter.av[CurrentPage][5]]);
                sound.start();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Log.i(TAG,"Hello");
                            slidecenter = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_center);
                            slidecenter.setAnimationListener(AnimalTest.this);
                            playButton.startAnimation(slidecenter);
                            visualization.setVisibility(LottieAnimationView.INVISIBLE);
                            sound.release();
                        }
                    }, sound.getDuration());

                }
            }
        });

        mvp= (ViewPager) findViewById(R.id.vp6);
        adapter = new AnimalTestAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);



    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation.toString().equals(slideLeft.toString()))
        {
            visualization.setVisibility(LottieAnimationView.VISIBLE);
        }
        else if(animation.toString().equals(slideLeft.toString()))
        {
            playButton.playAnimation();
        }
    }



    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            CurrentPage=position;
            Log.i(TAG,"Current Position: "+CurrentPage);
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
