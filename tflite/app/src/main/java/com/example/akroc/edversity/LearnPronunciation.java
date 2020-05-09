package com.example.akroc.edversity;

import android.content.Intent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

public class LearnPronunciation extends AppCompatActivity {

    Button buttonTest;
    LottieAnimationView pronun;
    private ViewPager mvp;
    private PronounceAdapter adapter;
    private TextToSpeech myTTS;
    int CurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_pronunciation);
        buttonTest=(Button) findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LearnPronunciation.this,PronunTest.class));
            }
        });

        pronun=(LottieAnimationView) findViewById(R.id.pronunx);
        pronun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pronun.playAnimation();
                pronun.setSpeed(3);
                String temp= adapter.slideHead[CurrentPage];
                String letter= ""+temp.toLowerCase().charAt(0);
                speak("This is Letter "+letter.toUpperCase());
            }
        });

        initializeTextToSpeech();
        mvp= (ViewPager) findViewById(R.id.vp3);
        adapter = new PronounceAdapter(this);
        mvp.setAdapter(adapter);
        mvp.addOnPageChangeListener(viewListener);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initializeTextToSpeech();
    }


    private void initializeTextToSpeech() {
        myTTS= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size()==0)
                {
                    Toast.makeText(getApplicationContext(),"There is no TTS Engine in your Device!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    myTTS.setLanguage(Locale.ENGLISH);
                    myTTS.setSpeechRate(0.9f);
                    myTTS.setPitch(1.2f);
                }
            }
        });
    }

    private void speak(String s) {
        myTTS.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
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
